package es.josemaria.videojuego.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import clases.Usuario;
import es.josemaria.videojuego.Mijuego;

public class PantallaMenu extends JPanel {
    private Ventana ventana;
    private PantallaPrincipal pantallaPrincipal;
    protected Usuario jugador;

    public PantallaMenu(Ventana v) {
        setLayout(null);
        this.ventana = v;

        JLabel lblTrasArmaduraSagrada = new JLabel("Tras la Armadura Sagrada", SwingConstants.CENTER);
        lblTrasArmaduraSagrada.setFont(new Font("impact", Font.PLAIN, 46));
        lblTrasArmaduraSagrada.setForeground(Color.decode("#d54f43"));
        lblTrasArmaduraSagrada.setBounds(120, 50, 500, 80);
        add(lblTrasArmaduraSagrada);

        JLabel etiquetaUsuario=new JLabel("Usuario:");
        etiquetaUsuario.setFont(new Font("courier",Font.BOLD,24));
        etiquetaUsuario.setForeground(Color.BLACK);
        etiquetaUsuario.setBounds(20,150,300,80);
        add(etiquetaUsuario);

        final TextField campoUsuario=new TextField();
        campoUsuario.setFont(new Font("impact",Font.PLAIN,14));
        campoUsuario.setForeground(Color.BLACK);
        campoUsuario.setBounds(20,170,300,80);
        add(campoUsuario);

        JLabel etiquetaPassword=new JLabel("Password:");
        etiquetaPassword.setFont(new Font("courier",Font.BOLD,24));
        etiquetaPassword.setForeground(Color.BLACK);
        etiquetaPassword.setBounds(20,250,300,80);
        add(etiquetaPassword);

        final TextField campoPassword=new TextField();
        campoPassword.setFont(new Font("impact",Font.PLAIN,14));
        campoPassword.setForeground(Color.BLACK);
        campoPassword.setBounds(20,270,300,80);
        add(campoPassword);

        JButton botonRegistrar=new JButton("Registro");
        botonRegistrar.setFont(new Font("impact",Font.PLAIN,24));
        botonRegistrar.setBounds(20, 525, 300, 55);
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((campoUsuario.getText()!="")&&(campoPassword.getText()!="") ){
                    jugador = new Usuario(campoUsuario.getText(), campoPassword.getText());

                }

            }

        });
        add(botonRegistrar);

        JButton botonLogin=new JButton("Login");
        botonLogin.setBounds(20, 625, 300, 55);
        botonLogin.setFont(new Font("impact",Font.PLAIN,24));
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
