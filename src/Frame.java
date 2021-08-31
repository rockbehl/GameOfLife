import javax.swing.*;

public class Frame extends JFrame {

    graphics gr = new graphics();

    public Frame(){

        //initializing frame
        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gr);
        setVisible(true);

    }

}
