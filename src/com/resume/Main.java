package com.resume;

import com.resume.ui.LoginPage;
import java.awt.Image;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        try {
            Image dockIcon = Toolkit.getDefaultToolkit().getImage("assets/app_icon.png");
            com.apple.eawt.Application.getApplication().setDockIconImage(dockIcon);
        } catch (Exception e) {
            System.out.println("Could not set Dock icon: " + e.getMessage());
        }

        new LoginPage();
    }
}
