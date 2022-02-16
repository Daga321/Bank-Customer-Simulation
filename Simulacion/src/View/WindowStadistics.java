package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowStadistics extends JDialog {

    private JLabel txtModulOne;
    private TextArea areaModulOne;

    private JLabel txtModulTwo;
    private TextArea areaModulTwo;

    private JLabel txtModulThree;
    private TextArea areaModulThree;


    public WindowStadistics() {
        super();
        valuesConfig();
        initialComponents();
    }

    private void initialComponents() {
        txtModulOne = new JLabel("Modulo 1");

        txtModulTwo = new JLabel("Modulo 2");

        txtModulThree = new JLabel("Modulo 3");

        areaModulOne = new TextArea();
        areaModulTwo = new TextArea();
        areaModulThree = new TextArea();

        txtModulOne.setBounds(50, 10, 100, 25);
        txtModulTwo.setBounds(400, 10, 100, 25);
        txtModulThree.setBounds(700, 10, 100, 25);

        areaModulOne.setBounds(20, 40, 250,500);
        areaModulOne.setEditable(false);
        areaModulTwo.setBounds(320,40,250,500);
        areaModulTwo.setEditable(false);
        areaModulThree.setBounds(620,40,250,500);
        areaModulThree.setEditable(false);

        this.add(txtModulOne);
        this.add(txtModulTwo);
        this.add(txtModulThree);
        this.add(areaModulOne);
        this.add(areaModulTwo);
        this.add(areaModulThree);
    }

    private void valuesConfig() {
        this.setTitle("ESTADISTICAS");
        this.setSize(950,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);


    }


    public TextArea getAreaModulOne() {
        return areaModulOne;
    }

    public TextArea getAreaModulTwo() {
        return areaModulTwo;
    }

    public TextArea getAreaModulThree() {
        return areaModulThree;
    }
}
