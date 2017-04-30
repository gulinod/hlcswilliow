#  Home LED Control System (HLCS)

####  Rationale
I came up with the idea for this project when I had gone to bed and forgot the shut off the LED strip behind my desk and monitor. This strip already had a remote control that came with it but I didn’t have it on me when I got into bed and it was also was limited by line of sight between the controller and the IR sensor.  After a minute of searching for the remote with no luck I thought to myself there has to be a better way. What was something that I always had on me when I went to bed? My phone! So I decided to create an app for my phone.

####     Anticipated Features
* Allow user to set color of desired LED strip
* Light strips on app sync to the lightstrop (allowing multiple users to have control) 
* An interface allowing users to connect to more than one Rasberry Pi
* Persist data through multiple sessions.
* Event scheduling

#### Architecture Overview
![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_overview.jpg "Logo Title Text 1")

Above is a rough sketch of the planned architecture that will allow the user to have control over the desired light strip. The app will connect to a Raspberry Pi over a Wi-Fi connection. The Raspberry Pi will control the light strip pins via its GPIO pins. To communicated between the Pi and the Android app the pi will have a Python webserver running on it with an API to allow it to set and get the current colors and relay/receive that data with the mobile app.

#### Screenshots
