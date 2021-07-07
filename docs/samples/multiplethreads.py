'''The following code allows the user to access multiple threads at once. So it is now possible to go to app.route("/")
and app.route("/cam") at the same time on the development server'''
import cv2
import time
import threading
import os
from flask import Response, Flask
from flask import request
# Image frame sent to the Flask object
global video_frame
video_frame = None
face_detector = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
# Use locks for thread-safe viewing of frames in multiple browsers
global thread_lock
thread_lock = threading.Lock()
# GStreamer Pipeline to access the Raspberry Pi camera
video_capture = cv2.VideoCapture(0)
# Create the Flask object for the application
app = Flask(__name__)
def captureFrames():
    global video_frame, thread_lock
    while True and video_capture.isOpened():
        return_key, frame = video_capture.read()
        if not return_key:
            break
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_detector.detectMultiScale(gray, 1.3, 5)
        for (x,y,w,h) in faces:
            cv2.rectangle(frame, (x,y), (x+w,y+h), (255,0,0), 2)
        # Create a copy of the frame and store it in the global variable,
        # with thread safe access
        with thread_lock:
            video_frame = frame.copy()
        cv2.imshow('image', frame)
        key = cv2.waitKey(30) & 0xff
        if key == 27:
            break
    video_capture.release()
def encodeFrame():
    global thread_lock
    while True:
        # Acquire thread_lock to access the global video_frame object
        with thread_lock:
            global video_frame
            if video_frame is None:
                continue
            return_key, encoded_image = cv2.imencode(".jpg", video_frame)
            if not return_key:
                continue
        # Output image as a byte array
        yield(b'--frame\r\n' b'Content-Type: image/jpeg\r\n\r\n' +
            bytearray(encoded_image) + b'\r\n')
@app.route('/',methods=["POST"])
def hello_sun():
    value1=(request.form['number1'])
    if(value1 == '1'):
        print("FORWARD")
    elif(value1 == '2'):
        print("REVERSE")
    elif(value1 == '3'):
        print("LEFT")
    elif(value1 == '4'):
        print("RIGHT")
    elif(value1 == '5'):
        print("STOP")
    return(str(value1))
@app.route("/cam")
def streamFrames():
    return Response(encodeFrame(), mimetype = "multipart/x-mixed-replace; boundary=frame")
# check to see if this is the main thread of execution
if __name__ == '__main__':
    # Create a thread and attach the method that captures the image frames, to it
    process_thread = threading.Thread(target=captureFrames)
    process_thread.daemon = True
    # Start the thread
    process_thread.start()
    # While it can be run on any feasible IP, IP = 0.0.0.0 renders the web app on
    # the host machine's localhost and is discoverable by other machines on the same network
    #By setting threaded to True, we can now access multiple routes at the same time
    app.run("0.0.0.0", port=8000,threaded=True)








