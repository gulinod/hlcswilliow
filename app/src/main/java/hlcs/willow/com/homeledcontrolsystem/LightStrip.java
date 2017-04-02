package hlcs.willow.com.homeledcontrolsystem;

/**
 * Created by dan on 2/19/17.
 */

public class  LightStrip {

    public LightStrip(String location, String mode, String color, String ID){

        this.setLocation(location);
        this.setMode(mode);
        this.setColor(color);
        this.setID(ID);
    }

    private String location;
    private String mode;
    private String ID;
    private String color;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
