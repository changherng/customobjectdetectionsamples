'''This python script allows the user to send the video feed of the custom object detection to a video streaming server with the help of flask. 
However, this uses a localhost so only devices connected to the local network will have access to it'''
from flask import flask
from flask import request
import serial
import time
arduino = serial.Serial(port = 'USER_PORT', timeout=0)
time.sleep(2)
while True:
  app = Flask(__name__)
  @app.route('/sum')
  def helloIndex():
    print(__name__)
    return 'Hello World from python Flask!'
  @app.route('/',methods=["POST"])
  def hello_sun():
    value1=(request.form['number1'])
    value2=(request.form['number2'])
    print(value1)
    if(value1 == '1'):
      arduino.write(str.encode('1'))
      print("LED TURNED ON")
      time.sleep(1)
    if(value1 == '0'):
      arduino.write(str.encode('0'))
      print("LED TURNED OFF")
      return(str(value1+value2))
    if __name__ == "__main__":
      app.run(host='0.0.0.0", port=8000)
