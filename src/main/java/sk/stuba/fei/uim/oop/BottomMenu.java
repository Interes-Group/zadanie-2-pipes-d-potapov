package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomMenu extends JPanel {
    public BottomMenu(GameLogic gameLogic){
        setBackground(Color.LIGHT_GRAY);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        });

        JButton buttonCheckWin = new JButton("CHECK WIN");
        buttonCheckWin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonCheckWin.setFocusable(false);

        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonRestart.setFocusable(false);

        setLayout(new GridLayout(2, 2));
        //add(gameLogic.getLabel());
        add(slider);
        add(buttonCheckWin);
        add(buttonRestart);
    }
}
