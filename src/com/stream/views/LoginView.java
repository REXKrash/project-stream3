package com.stream.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;


public class LoginView extends AccountView {

    private GridBagConstraints constraints;

    public LoginView() {
        constraints = new GridBagConstraints();
    }

    /**
     * Populates contentPanel with imageLabel
     * The super class updates the rest of the view
     */
    public void updateView() {
        super.updateView();
        JLabel imageLabel = new JLabel();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("res/images/logoMedium.png");
            BufferedImage pic = ImageIO.read(is);
            imageLabel.setIcon(new ImageIcon(pic));

        } catch (Exception ex) {
            System.out.println(ex);
        }

        constraints.gridy = 0;
        contentPanel.add(imageLabel, constraints);

        constraints.ipadx = 300;
        constraints.ipady = 20;
        constraints.insets = new Insets(15,0,0,0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
    }

    /**
     * Adds signUpButton to contentPanel
     * @param al ActionListener for the button
     */
    public void addSignUpButton(ActionListener al) {
        JButton signUpButton = new JButton("Create Account");
        signUpButton.setFont(mediumFont);
        signUpButton.addActionListener(al);

        constraints.gridy = 6;
        contentPanel.add(signUpButton, constraints);
    }
}
