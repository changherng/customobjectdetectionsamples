import threading
import os
import jetson.inference
import jetson.utils
import time
import cv2
import numpy as np
from flask import Response, Flask
net=jetson.inference.detectNet('ssd-mobilenet-v2', threshold.5)
dispH=640
dispW=48-
font=cv2.FONT_HERSHEY_SIMPLEX
global video_frame
video_frame = None
global thread_lock
thread_lock = threading.lock()
video_capture = cv2.VideoCapture(0)
app = Flask(__name__)
def captureFrames():
  global video_frame, thread_lock
  while True and video_capture.isOpened():
    return_ket, frame = video_capture.read()
    if not return_key:
      break
      height=dispH
      width=dispW
      test=cv2.cv2Color(frame,cv2.COLOR_BGR2RGBA).astype(np.float32)
      test=jetson.utils.cudoFromNumpy(test)
      detections=net.Detect(test, width, height)
      for detect in detections:
        ID=detect.ClassID
        top=detect.Top
        left=detect.Left
        bottom=detect.Bottom
        right=detect.Right
        item=net.GetClassDesc(ID)
        print(item+" detected")
      with thread_lock:
        video_frame = frame.copy()
      cv2.imshow('image', frame)
      key = cv2.waitKey(30) & 0xff
      if key == 27:
        breal
    video_capture.release()
def encodeFrame():
  global thread_lock
  while True:
    with thread_lock:
      global video_frame
      if video_frame is None:
        continue
        return_key, encoded_image = cv2.imencode(".jpg", video_frame)
        if not return_key:
          continue
      yield(b'--frame\r\n' b'Content-Type: image/jpeg\r\n\r\n'+bytearray(encoded_image) + b'\r\n')
@app.route("/")
def streamFrames():
  return Response(encodeFrame(), mimetype = "multipart/x-mixed-replace; boundary=frame")
if __name__ == '__main__':
  process_thread = threading.Thread(target.captureFrames)
  process_thread.daemon = True
  process_thread.start()
  app.run("0.0.0.0", port=8000)
            
      
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
    
    
    
    
    
    
    
    
