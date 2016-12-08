/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShotItDown;


import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class Main {

    public static void main(String[] args) throws AWTException {
        
        final TrayIcon trayIcon = new TrayIcon(new ImageIcon(Main.class.getResource("/icon.png")).getImage());
        trayIcon.setImageAutoSize(true);

        PopupMenu trayMenu = new PopupMenu();
        MenuItem run = new MenuItem("Grab a screenshot");
        MenuItem exit = new MenuItem("Exit ShotItDown!");

        run.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Thread.sleep(500);
                    Screenshot screenshot = new Screenshot();
                    FullscreenView display = new FullscreenView(screenshot.getImage());
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (AWTException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        

        SystemTray  tray = SystemTray.getSystemTray();

        
        trayMenu.add(run);
        trayMenu.add(exit);
        trayIcon.setPopupMenu(trayMenu);
        
        
        
        
        
        tray.add(trayIcon);

        
        


                        
   
    }
}
