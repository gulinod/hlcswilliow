package hlcs.willow.com.homeledcontrolsystem;

import android.media.Image;

/**
 * Created by dan on 2/19/17.
 */

public class  LightStrip {

    private String location;
    private String mode;
    private String IP;
    private LEDColor color;


    public LightStrip(String location, String mode,  String IP, LEDColor color){
        this.color = color;
        this.setLocation(location);
        this.setMode(mode);
        this.setID(IP);
    }


    public String getIP() {return IP;}

    public void setID(String IP) {
        this.IP = IP;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LEDColor getColor() {return color;}

    public void setColor(LEDColor color) {this.color = color;}
}
