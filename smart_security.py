# Open xampp and turn on mysql server
# If doesn't work turn of mysqlid in task manager
# ngrok http 80 in ngrok


# Import essential libraries
import requests
import cv2
import numpy as np 
import imutils
import cv2
import time
import datetime
from firebase import firebase
  
# Replace the below URL with your own. Make sure to add "/shot.jpg" at last.
url = "http://192.168.3.176:80/capture"


f = firebase.FirebaseApplication("https://smartcctv-3549b-default-rtdb.firebaseio.com/",None)


face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades +"haarcascade_frontalface_default.xml")
body_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + "haarcascade_fullbody.xml")

detection = False
detection_stopped_time = None
timer_started = False
SECONDS_TO_RECORD_AFTER_DETECTION = 5
frame_size = int(200), int(200)
fourcc = cv2.VideoWriter_fourcc(*"mp4v")

  
# While loop to continuously fetching data from the Url
while True:
    img_resp = requests.get(url)
    img_arr = np.array(bytearray(img_resp.content), dtype=np.uint8)
    frame = cv2.imdecode(img_arr, -1)
    # frame = imutils.resize(frame, width=1000, height=1800)

    gray = cv2.cvtColor(frame,cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    bodies = body_cascade.detectMultiScale(gray, 1.3, 5)

    if len(faces) + len(bodies) > 0:
        if detection:
            timer_started = False
        else:
            detection = True
            current_time = datetime.datetime.now().strftime("%d-%m-%Y-%H-%M-%S")
            out = cv2.VideoWriter(
                f"Videos\\{current_time}.mp4", fourcc, 20, frame_size)
            print("Started Recording!")
            img_path =f"{current_time}.png"
            cv2.imwrite(f"Images\\{img_path}", frame)
            cv2.imwrite(f"C:\\xampp\\htdocs\\img\\{img_path}",frame)
            result=cv2.VideoWriter(f"C:\\xampp\\htdocs\\videos\\{current_time}.mp4",fourcc,20,frame_size)
            f.put('Image',f'{current_time}',f'{current_time}')
            
    elif detection:
        if timer_started:
            if time.time() - detection_stopped_time >= SECONDS_TO_RECORD_AFTER_DETECTION:
                detection = False
                timer_started = False
                out.release()
                print('Stop Recording!')
               
        else:
            timer_started = True
            detection_stopped_time = time.time()

    if detection:
        out.write(frame)

    for (x, y, width, height) in faces:
       cv2.rectangle(frame, (x, y), (x + width, y + height), (255, 0, 0), 3)

    cv2.imshow("Android_cam", frame)
  
    # Press Esc key to exit
    if cv2.waitKey(1) == 27:
        break
  
cv2.destroyAllWindows()

