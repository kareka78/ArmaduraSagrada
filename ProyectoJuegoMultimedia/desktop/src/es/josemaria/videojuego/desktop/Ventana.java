package es.josemaria.videojuego.desktop;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Ventana extends JFrame {


    public Ventana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tras la Armadura Sagrada");
        this.setSize(800,800);
        //this.setContentPane(new JLabel(new ImageIcon("parteAndroid/fondoAct1.jpg")));
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setContentPane(new PantallaMenu(this));
        setVisible(true);
        try {
            setIconImage(ImageIO.read(new File("parteAndroid/app_icon.png")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

