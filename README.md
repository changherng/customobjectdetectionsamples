# Custom Object-Detection Using Jetson-Inference
This github repo focuses on implementing custom object detection on the jetson nano, by using Jetson-inference by HelloAI World, Dusty.
Huge thanks to Dusty and feel free to use anything available here as you wish.

By using this method it is easier to annotate custom datasets and obtain pre-trained datasets for your projects.

Here are other projects

* Detection
  * Face detection using haarcascades
  * Face detection using inference

* Recognition
  * Face recognition using haarcascades
  * Face recognition using inference

        
### These are the list of things that we will cover:
#### 1) Installation
To install jetson inference on your machine 

    pip3 install jetson-inference
#### 2) Obtaining datasets
#### 3) Training custom models
#### 4) Creating the onnx file
#### 5) Launching your own object detection program
#### 6) Writing your own custom object detection program in python
#### 7) References




## Annotations
#### LabelImg (For annotations of custom datasets)

Link here `INSERT LINK`

To get custom datasets online, [Open Images](https://storage.googleapis.com/openimages/web/visualizer/index.html?set=train&type=detection&c=%2Fm%2F0fp6w) has over [600](https://github.com/changherng/customobjectdetectionsamples/blob/main/openImages_classesLists.txt) 
different classes of objects for you to choose from.









Code for flask server object detection [here](https://github.com/changherng/customobjectdetectionsamples/blob/main/objectDetectorFlaskServer.py)



Other projects that you can work on a jetson nano are face detection or recognition using haarcascade for higher fps but sacrificing accuracy.
