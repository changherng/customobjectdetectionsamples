# Custom Object-Detection Using Jetson-Inference
This github repo focuses on implementing custom object detection on the jetson nano, by using Jetson-inference by HelloAI World, Dusty.

By using this method it is easier to annotate custom datasets and obtain pre-trained datasets for your projects.

Here are other projects

* Detection
  * Face detection using haarcascades
  * Face detection using inference

* Recognition
  * Face recognition using haarcascades
  * Face recognition using inference

        
### These are the list of things that we will cover:
### 1) Installation
To install jetson inference on your machine 

    sudo apt-get update
    sudo apt-get install git cmake libpython3-dev python3-numpy
    cd ~/Downloads
    git clone --recursive https://github.com/dusty-nv/jetson-inference
    cd jetson inference
    mkdir build
    cd build 
    cmake ../
After the last step is done, there will be a pop-to of choices to choose from. From image segmentation, detection and classification all optional.
Here we will use the default choices chosen for us. (Imagenet, detectnet, googlenet etc...)

When the download is finished, it will prompt the you to install packages. Choose python3 and not python2.
    
    make -j$(nproc)
This process will take some time and once it is done
    
    sudo make install
    sudo ldconfig
    sudo apt-get install v4l-utils
To check if torch and torchvision is properly installed

    python3
    import torch
    import torchvision
If there are not errors then you are safe to proceed.

### 2) Obtaining datasets
To get custom datasets online, [Open Images](https://storage.googleapis.com/openimages/web/visualizer/index.html?set=train&type=detection&c=%2Fm%2F0fp6w) has over [600](https://github.com/changherng/customobjectdetectionsamples/blob/main/openImages_classesLists.txt) 
different classes of objects for you to choose from.
To download the datasets from [Open Images](https://storage.googleapis.com/openimages/web/visualizer/index.html?set=train&type=detection&c=%2Fm%2F0fp6w), you can select
the classes of your choice
   
    python3 open_images_downloader.py --class-names "House,Traffic Light" --data=data/YOURFOLDER
This will download `All` the datasets of House and Traffic Light. In some cases where you might want to limit the data used and prevent downloading too much, you can check
the amount of images of a class using:

    python3 open_images_downloader.py --stats-only --class-names "House,Traffic Light" --data=data/YOURFOLDER
And you can select the amount of images to download:

    python3 open_images_downloader.py --max-images=2500 --class-names "House,Traffic Light" --data=data/YOURFOLDER
`If you already have the images either from other websites or taken personally, you can create datasets using other methods. Refer to Annotations at the bottom)`
### 3) Training custom models
To train your dataset do to the detection directory

    cd jetson-inference/python/training/detection/ssd
    python3 train_ssd.py --data=data/<YOUR-DATASET> --model-dir=models/<YOUR-MODEL>
If you are using labelImg or cvat to train your dataset use

    python3 train_ssd.py --dataset-type=voc --data=data/<YOUR-DATASET> --model-dir=models/<YOUR-MODEL>
    
### 4) Creating the onnx file
After training your models, you will have to convert it to ONNX

    python3 onnx_export.py --model-dir=models/<YOUR-MODEL>
### 5) Launching your own object detection program
To launch the object detection with your custom trained model:
    
    NET=models/<YOUR-MODEL>
    detectnet --model=$NET/ssd-mobilenet.onnx --labels=$NET/labels.txt \
          --input-blob=input_0 --output-cvg=scores --output-bbox=boxes \
            /dev/video0
### 6) Writing your own custom object detection program in python
Now that we are done launching it from terminal, you might want to try and write a python script to add in more functionality.
Here is a script that uses a custom dataset and prints the object detected. This can be further pushed to work with apps or arduino: [customObjectDetector.py](https://github.com/changherng/customobjectdetectionsamples/blob/main/custom_model_detector.py)
### 7) References




## Annotations
#### LabelImg (For annotations of custom datasets)

Link here - [tzutalin/labelImg](https://github.com/tzutalin/labelImg)

#### Cvat (For annotations of custom datasets)
Link here - [Cvat](https://github.com/openvinotoolkit/cvat)











Code for flask server object detection [here](https://github.com/changherng/customobjectdetectionsamples/blob/main/objectDetectorFlaskServer.py)
Use `pip3 install flask` to install the flask module on your machine.



Other projects that you can work on a jetson nano are face detection or recognition using haarcascade for higher fps but sacrificing accuracy.
Face detection & Recognition with haarcascades = [Link]
Haarcascades is not really a good option but it has higher fps than other methods such as mtCNN, dlib and ssd. You can use this if all others don't work.

Hand Gesture recognition with contours = [Link]
An example will be given which uses contours. This has problems such as detecting backgroud objects as contours but can be fixed by using background subtraction with an opencv
function. Other methods such as mediapipe or tensorFlow has better accuracy but might not be supported on some machines.
