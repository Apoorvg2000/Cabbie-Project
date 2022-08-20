# Cabbie -- Readme File

Good things happen when people can move, whether across town or toward their dreams. Opportunities appear, open up, become reality. We intend to start a way to tap a button to get a ride leading to billions of moments of human connection as people around the world go all kinds of places in all kinds of ways.

## About the app

1. The application is made using Android Studio. 
2. For all the backend work, we have used a high level programming language Java because of its ease of use. 
3. For storing the user data, we have used Firebase by Google.
4. For the user interface, XML was used.


## Functionalities

### 1. Get Started

![image](https://user-images.githubusercontent.com/54017314/185348660-15840f7a-e0a5-4ef8-b0da-e8c1c3f8187b.png) 

The app opens up with a well designed UI from which we can navigate to login screen.

### 2. Phone Number Authentication

Enter mobile number        |  OTP SMS
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/54017314/185348341-d9a21818-788b-42f1-878f-962eb5d98da4.png)  |  ![image](https://user-images.githubusercontent.com/54017314/185348398-21c53c2a-556a-4703-bb85-88e2d63c0000.png)

The users can sign up or login in our app using their phone numbers and this authentication information is stored in Firebase Authentication along with a unique UID assigned to them by the Firebase Authentication. After entering their mobile numbers, an OTP (One Time Password) is sent to the users via an SMS for a safe and secure login.

If the user already exists in the database, then he is safely logged in our app. Otherwise, user is signed up, and then logged in automatically.

### 3. Google Maps Screen

![image](https://user-images.githubusercontent.com/54017314/185349511-fd0a20ca-1fa4-425a-8278-1c1aa95f44a8.png)

After user has logged in successfully, he is redirected to the google maps page within the app where his current location is marked by a marker within the map. 

The current location of the user is shown by a red coloured marker on the map.

Now, user can search his destination on a search bar which also shows suggestions based on the location entered by the user as shown below.

![image](https://user-images.githubusercontent.com/54017314/185349682-64492457-6837-42b7-95ec-eebae3d46eb7.png)

When the user has successfully selected his destination, a path becomes visible between the user’s current location and the destination (Polyline).
The destination entered by the user is shown by a blue coloured marker on the map.

### 4. Book a cab

![image](https://user-images.githubusercontent.com/54017314/185349869-b0546d23-77d7-4d59-a596-66de0dfce4c9.png)

Now, the user can book a can from this current location to the entered destination. The request is recorded in the Firebase Realtime database when then user clicks on “ Book Cab “ button.

Once the user clicks on the “ Book Cab “ button, its text changes to 
“ Cancel Cab “. The user can cancel the cab by clicking on this button.

If the request for a cab is is successfully recorded in the database, a toast appears on the screen informing the user this his request has been recorded.

User can cancel the cab only until his request has been accepted by a driver nearby.

![image](https://user-images.githubusercontent.com/54017314/185350170-05e62a66-cf31-4f0b-b276-524807b88043.png)

### 5. Notifications

Once the user’s request has been accepted, he is informed by our app by getting a notification which mentions the arrival time of the driver at the user’s pickup location.

After the cab has reached the pickup location, user is notified by a notification by our app that that the driver has reached the pickup location.

Request Accepted           |  Pickup Notification
:-------------------------:|:-------------------------:
![image](https://user-images.githubusercontent.com/54017314/185351093-d6e0371f-4064-4272-9b27-4c5867773d34.png)  |  ![image](https://user-images.githubusercontent.com/54017314/185351118-08f85133-9bce-4137-8123-a1a29d02adc3.png)

Once the user is in the cab, he can click on the polyline which will redirect him to the official Google Maps app.

### 6. Payments

###### **This functionality is not yet complete :(**

### 7. Logout

The user can also logout of the app and login or signup using another mobile number

![image](https://user-images.githubusercontent.com/54017314/185351945-3b332f98-386d-446b-a8ac-704f482c3f14.png)






















