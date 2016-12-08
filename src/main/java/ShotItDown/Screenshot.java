/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShotItDown;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author user
 */
public class Screenshot {

    private String fileName;
    private BufferedImage image;

    public String getFileName() {
        return fileName;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    
    public Screenshot() throws AWTException {
        

        
    //    Calendar now = Calendar.getInstance();
      //  this.fileName = directory +  formatter.format(now.getTime()) + ".png";
        
        Robot robot = new Robot();
        image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        //ImageIO.write(screenShot, "PNG", new File(this.fileName));
        //System.out.println(formatter.format(now.getTime()));
    }
    
    
    
}
