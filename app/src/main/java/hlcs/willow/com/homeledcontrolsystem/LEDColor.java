package hlcs.willow.com.homeledcontrolsystem;


/**
 * Created by dan on 3/5/17.
 */

public class LEDColor {
    private int red;
    private int green;
    private int blue;
    private String mode = "strobe";

    public LEDColor(int red, int green, int blue,String mode){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.mode = mode;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }


    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public String getMode() {return mode;}

    public void setMode(String mode) {this.mode = mode;}
}
