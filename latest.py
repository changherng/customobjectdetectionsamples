import threading
import os
import jetson.inference
import jetson.utils
import time
import cv2
import numpy as np 
from flask import Response, Flask
from flask import request
# Image frame sent to the Flask object
net=jetson.inference.detectNet('ssd-mobilenet-v2',['--model=models/real/ssd-mobilenet.onnx','--input-blob=input_0','--output-cvg=scores','--output-bbox=boxes','--labels=models/real/labels.txt'], threshold=.5)
dispW=640
dispH=480
font=cv2.FONT_HERSHEY_SIMPLEX
global video_frame
video_frame = None
global thread_lock
item =''
kimn ='random'
thread_lock = threading.Lock()
video_capture = cv2.VideoCapture(0)
app = Flask(__name__)
def captureFrames():
    global video_frame, thread_lock, item, kimn
    while True and video_capture.isOpened():
        return_key, frame = video_capture.read()
        if not return_key:
            break
        height=dispH
        width=dispW
        test=cv2.cvtColor(frame,cv2.COLOR_BGR2RGBA).astype(np.float32)
        test=jetson.utils.cudaFromNumpy(test)
        detections=net.Detect(test, width, height)
        for detect in detections:
            ID=detect.ClassID
            top=int(detect.Top)
            left=int(detect.Left)
            bottom=int(detect.Bottom)
            right=int(detect.Right)
            item=net.GetClassDesc(ID)
            cv2.rectangle(frame,(left,top),(right,bottom),(255,255,255),2)
            cv2.putText(frame, item,(left,top+20),font,.75,(0,0,255),1)
            if(item == 'Hi'):
                print(left,top,right,bottom)
        # Create a copy of the frame and store it in the global variable,
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
        yield(b'--frame\r\n' b'Content-Type: image/jpeg\r\n\r\n' +
            bytearray(encoded_image) + b'\r\n')
@app.route("/cam")
def streamFrames():
    return Response(encodeFrame(), mimetype = "multipart/x-mixed-replace; boundary=frame")
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
@app.route('/msg')
def hello_activity():
    global item, kimn
    if(item != kimn):
        kimn = item
        return item
if __name__ == '__main__':
    process_thread = threading.Thread(target=captureFrames)
    process_thread.daemon = True
    process_thread.start()
    # the host machine's localhost and is discoverable by other machines on the same network 
    app.run(host='0.0.0.0', port=8000, threaded=True)








