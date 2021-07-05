Object Detection
By using tensorRT and torchVision to perform object detection, we are able to collect custom datasets, train them and detect objects that we specified.
## Procedure
### Collecting our dataset
We captured 50-100 images of each class of object via camera, stock images. These images are in `jpg` format and stored in the same folder.
### Training our models
To train our custom datasets we used labelImg by tzutalin. We use this to annotate the images collected according to the classes specified:
        
    Houses
    Cars
    Faces
    ...
However, with labelImg it does not create the full structure of the `Pascal/VOC` format that jetson-inference uses to train the models. To fix this we have to 
arrange our Images, annotations and labels in the following Pascal/Voc format:
        
    -Annotations
    -ImageSets
        -test.txt
        -train.txt
        -trainval.txt
        -val.txt
    -JPEGImages
    -labels.txt

### Exporting the model to ONNX
We then export the annotated data of our images to onnx that will be used for object detection.

### Running the object detection program
Our object detection program can detect objects by using a custom dataset. Upon observation the average fps of the program is about `40fps` and confidence of detected object is at an average 80%. This can be increased with more images for training with the cost of storage space.





FlaskServer
The flask server has multiple functions in the program and one of it is to grant the user access to the camera view of the program via android app. The object detection program sends the video feed to a video streaming server such as `Flask`. The object detection program also notified the user when an object/hand gesture is detected and 
this can be seen via android app. 





Java(App)
The android app has 5 main functions:

    Camera
    Movement
    Temperature
    Activity log
    Settings
The camera function will allow the user to view the live camera feed of the object detection program.

The Movement function will allow the user to control the robot, a video feed will be available on the same app page for easy navigation of the robot.

The temperatue function will record all temperature readings taken by the robot.

The activity log will record all detected object/activity/gestures detected by the object detection program and kept for future inspection.

The settings function will allow the user to change some aspect of the app such as the background color of the app which will be either `black` or `white`. 
