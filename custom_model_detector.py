import jetson.inference
import jetson.utils
import time
import cv2
import numpy as np
timeStamp=time.time()
fpsFilt=0
#Importing custom dataset and using ssd-mobilenet-v2 model
net=jetson.inference.detectNet('ssd-mobibilenet-v2',['--model=models/YOUR MODEL/ssd-mobilenet.onnx','--input-blob=input_0','--output-cvg=scores'
                                                     ,'--output-bbox=boxes','--labels=models/YOUR LABEL.txt'], threshold=0.5)
dispW=640
dispH=480
font=cv2.FONT_HERSHEY_SIMPLEX
#Set camera for video feed, might vary for different cameras such as /dev/video1 or csi://0
cam-cv2.VideoCapture('/dev/video0')
cam.set(cv2.CAP_PROP_FRAME_WIDTH, dispW)
cam.set(cv2.CAP_PROP_FRAME_HEIGHT, dispH)
while True:
  _,img = cam.read()
  height=dispH
  width=dispW
  frame=cv2.cvtColor(img,cv2.COLOR_BGR2RGBA).astype(np.float32)
  frame=jetson.utils.cudaFromNUmpy(frame)
  detections=net.Detect(frame, width, height)
  for detect in detections:
    ID=detect.ClassID
    top=detect.Top
    left=detect.Left
    bottom=detect.Bottom
    right=detect.Right
    item=net.GetClassDesc(ID)
    #Displays the object detected
    print(item)
  dt=time.time()-timeStamp
  timeStamp=time.time()
  fps=1/dt
  fpsFilt=0.9*fpsFilt + 0.1*fps
  cv2.putText(img, str(round(fpsFilt,1))+' fps',(0,30),font,1,(0,0,255),2)
  cv2.imshow('camdisplay',img)
  cv2.moveWindow('camdisplay',0,0)
  if cv2.waitKey(1)==ord('q'):
    break
cam.release()
cv2.destroyAllWindows()
