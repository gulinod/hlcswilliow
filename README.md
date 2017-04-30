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

![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_color_picker.png 
"Logo Title Text 1")

This is where the user will select the color of the LED strip. The user will be able to reach this dialog by clicking on the light strip you want to change.

![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_connection.png
"Logo Title Text 1")

This dialog allows the user to connect to the desired light strip by inputting the ip of the pi controlling the strip and the name/locaiton of the strip. Here the user can also set the default mode. 

![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_mode_selector.png 
"Logo Title Text 1")

This will allow the user to select the mode from the connection menu and in the edit menu after the strip has been added to the light strip list. 

![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_strip_list.png
"Logo Title Text 1")

This shows the screen after the user has added light strips. This view is the main view that the user will be interacting with. From this screen the user can add, edit, delete, and change the color of everylight strip. 

![alt text](http://web.cs.sunyit.edu/~gulinod/capstone/images/HLCS_strip_options.png
"Logo Title Text 1")

This shows the two options the user has when they long click on the strip. 

#### Future plans

* Add a layer of security to password protect a lightstrip
