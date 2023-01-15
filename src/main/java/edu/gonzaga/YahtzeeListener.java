package edu.gonzaga;

import javax.swing.*;
import java.awt.event.*;

public class YahtzeeListener
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame("Do Not Click this button");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField tf = new JTextField();
        tf.setBounds(50, 50, 150, 20);

        JButton b = new JButton("Clicky Clicky Here");
        b.setBounds(50, 100, 200, 30);
        b.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        tf.setText("I was clicked");
                        b.setText("You did this");
                    }
                }
        );

        // Add components to the frame (window)
        f.add(b);
        f.add(tf);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
