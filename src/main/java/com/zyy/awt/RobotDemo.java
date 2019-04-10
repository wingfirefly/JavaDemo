package com.zyy.awt;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RobotDemo {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            // 屏幕宽度
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            System.out.println(d);
            Rectangle screenRect = new Rectangle(d);
            BufferedImage bufferedImage = robot.createScreenCapture(screenRect);

            System.out.println("daka");
            File file = new File("c:\\screenRect.png");
            ImageIO.write(bufferedImage, "png", file);

            robot.setAutoDelay(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }
}
