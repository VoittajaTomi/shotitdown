/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShotItDown;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import java.awt.Point;

import java.awt.RenderingHints;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.MouseMotionAdapter;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class FullscreenView extends javax.swing.JFrame {

    BufferedImage screenImage;
    int width, height;

    int x1, x2, y1, y2;

    Graphics2D draw;

    Point startPoint, endPoint;

    private int strokeWidth = 5;

    /**
     * Creates new form Display
     */
    public FullscreenView(BufferedImage image) {

        this.setUndecorated(true);
        initComponents();

        // remove window frame  
        // window should be visible 
        this.setVisible(true);

        // switching to fullscreen mode 
        GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice().setFullScreenWindow(this);
        // getting display resolution: width and height 
        width = this.getWidth();
        height = this.getHeight();
        System.out.println("Display resolution: " + String.valueOf(width) + "x" + String.valueOf(height));

        screenImage = image;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                endPoint = startPoint;

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();

                //renderShape(screenImage.createGraphics());
                try {
                    doTheMagic();
                } catch (IOException ex) {
                    Logger.getLogger(FullscreenView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(FullscreenView.class.getName()).log(Level.SEVERE, null, ex);
                }

                repaint();

                endPoint = null;
                startPoint = null;

                // close the window
                dispose();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                //renderShape(screenImage.createGraphics());
                repaint();
            }

        });

    }

    public void doTheMagic() throws IOException, UnsupportedFlavorException {
        int width = 0, height = 0;
        int x = 0, y = 0;

        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y < 0))) {
            x = startPoint.x;
            y = startPoint.y;
            width = Math.abs(startPoint.x - endPoint.x);
            height = Math.abs(startPoint.y - endPoint.y);
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y < 0))) {
            x = endPoint.x;
            y = startPoint.y;
            width = Math.abs(startPoint.x - endPoint.x);
            height = Math.abs(startPoint.y - endPoint.y);
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y > 0))) {
            x = endPoint.x;
            y = endPoint.y;
            width = Math.abs(startPoint.x - endPoint.x);
            height = Math.abs(startPoint.y - endPoint.y);
        }

        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y > 0))) {
            x = startPoint.x;
            y = endPoint.y;
            width = Math.abs(startPoint.x - endPoint.x);
            height = Math.abs(startPoint.y - endPoint.y);
        }
        System.out.println("" + width + " " + height + " " + x + " " + y);

        BufferedImage img = screenImage.getSubimage(x, y, width, height);

        MenuView pic = new MenuView(img);

    }

    public void paint(Graphics g) {
        // Display.this.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(screenImage, null, 0, 0);

        if (startPoint != null && endPoint != null) {
            renderShape(g2D);
        }

    }

    public void drawRectangle(Graphics2D g2D) {
        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawRect(startPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y < 0))) {
            g2D.drawRect(endPoint.x, startPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) > 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawRect(endPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }

        if (((startPoint.x - endPoint.x) < 0)
                && ((startPoint.y - endPoint.y > 0))) {
            g2D.drawRect(startPoint.x, endPoint.y,
                    Math.abs(startPoint.x - endPoint.x),
                    Math.abs(startPoint.y - endPoint.y));
        }
    }

    public void renderShape(Graphics2D g2D) {
        if ((startPoint == null) || (endPoint == null)) {
            return;
        }

        g2D.setColor(Color.RED);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        g2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER));
        drawRectangle(g2D);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
