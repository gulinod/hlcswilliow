package hlcs.willow.com.homeledcontrolsystem;

/**
 * Created by dan on 2/19/17.
 */

public class  LightStrip {

    public LightStrip(String location, String mode,  String IP){

        this.setLocation(location);
        this.setMode(mode);
        this.setID(IP);
    }

    private String location;
    private String mode;
    private String IP;

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


}
