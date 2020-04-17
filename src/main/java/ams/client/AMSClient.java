package ams.client;

import ams.client.draw.DrawManager;

import javax.swing.*;
import java.awt.*;

public class AMSClient {

    public static void start() {
        // 03026e0b-a16d-4f36-b667-849fe7fe6a18

        // aa809edf-b6b5-2b44-8860-a50304c2081c
        // aa809edfb6b52b448860a50304c2081c

        // fedf9663af33f2145ba55db6edfa2b51

        //UUID.fromString()

        JFrame jFrame = new JFrame();
        String name = "AMS - Anonymous Messaging Service";
        jFrame.setTitle(name);
        jFrame.setName(name);
        Dimension dimension = new Dimension(960, 720);
        jFrame.setMinimumSize(dimension);
        jFrame.setSize(dimension);
        jFrame.setVisible(true);
        jFrame.setFocusTraversalKeysEnabled(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        DrawManager drawManager = new DrawManager();
        jFrame.setContentPane(drawManager);
        jFrame.validate();
        jFrame.addKeyListener(drawManager.keyListener);

        //
    }

}
