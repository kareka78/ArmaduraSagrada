package es.josemaria.videojuego.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class PantallaMenu extends JPanel {
    private Ventana ventana;

    public PantallaMenu(Ventana v) {
        setLayout(null);
        this.ventana = v;

        JLabel lblTrasArmaduraSagrada = new JLabel("Tras la Armadura Sagrada", SwingConstants.CENTER);
        lblTrasArmaduraSagrada.setFont(new Font("impact", Font.PLAIN, 26));
        lblTrasArmaduraSagrada.setForeground(Color.decode("#d54f43"));
        lblTrasArmaduraSagrada.setBounds(120, 50, 500, 80);
        add(lblTrasArmaduraSagrada);

        JLabel etiquetaUsuario=new JLabel("Usuario:");
        etiquetaUsuario.setFont(new Font("courier",Font.BOLD,24));
        etiquetaUsuario.setForeground(Color.BLACK);
        etiquetaUsuario.setBounds(220,150,300,80);
        add(etiquetaUsuario);

        TextField campoUsuario=new TextField();
        campoUsuario.setFont(new Font("impact",Font.PLAIN,24));
        campoUsuario.setForeground(Color.BLACK);
        campoUsuario.setBounds(220,170,300,80);
        add(campoUsuario);

        JLabel etiquetaPassword=new JLabel("Password:");
        etiquetaPassword.setFont(new Font("courier",Font.BOLD,24));
        etiquetaPassword.setForeground(Color.BLACK);
        etiquetaPassword.setBounds(220,250,300,80);
        add(etiquetaPassword);

        TextField campoPassword=new TextField();
        campoPassword.setFont(new Font("impact",Font.PLAIN,24));
        campoPassword.setForeground(Color.BLACK);
        campoPassword.setBounds(220,270,300,80);
        add(campoPassword);

        JButton botonRegistrar=new JButton("Registro");
        botonRegistrar.setBounds(220, 525, 300, 55);
        add(botonRegistrar);

        JButton botonLogin=new JButton("Login");
        botonLogin.setBounds(220, 625, 300, 55);
        add(botonLogin);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("parteAndroid/portada1.jpg"));

            g.drawImage(img, 0, 0,800,800, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
