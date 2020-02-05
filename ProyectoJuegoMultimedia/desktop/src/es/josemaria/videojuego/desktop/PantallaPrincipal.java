package es.josemaria.videojuego.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import es.josemaria.videojuego.Mijuego;

public class PantallaPrincipal extends JPanel {
    private Ventana ventana;
    private JToggleButton tglbtnCaballero;
    private JToggleButton tglbtnModoDeMovimiento;

    public PantallaPrincipal(Ventana v){
            setLayout(null);
            this.ventana=v;

        JLabel lblTrasArmaduraSagrada = new JLabel("Tras la Armadura Sagrada", SwingConstants.CENTER);
        lblTrasArmaduraSagrada.setFont(new Font("impact", Font.PLAIN, 46));
        lblTrasArmaduraSagrada.setForeground(Color.decode("#d54f43"));
        lblTrasArmaduraSagrada.setBounds(120, 50, 500, 80);
        add(lblTrasArmaduraSagrada);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setBounds(220, 425, 300, 55);
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title="Tras la Armadura Sagrada";
                config.addIcon("parteAndroid/app_icon.png", Files.FileType.Internal);
                config.width=800;
                config.height=800;
                config.resizable=false;
                ventana.setVisible(false);
                //Si es masculino no va a estar "selected" el toggle tglbtnGenero
                //Si es femenino, si va a estarlo.
                new LwjglApplication(new Mijuego(
                        tglbtnCaballero.isSelected()
                ), config);
            }
        });
        add(btnJugar);

        JButton botonOpciones = new JButton("Guardar seleccion Caballero");
        botonOpciones.setBounds(170, 607, 400, 50);
        botonOpciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw=new BufferedWriter(new FileWriter("./opciones.ddayvacp"));

                    if(tglbtnCaballero.isSelected()){
                        bw.write("Dragon");
                    }else{
                        bw.write("Pegaso");
                    }
                    bw.newLine();
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(ventana, "Configuracion guardada!","Configuracion guardada!",JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ventana, "Error en el guardado","Error en el guardado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(botonOpciones);

        tglbtnCaballero = new JToggleButton("Caballero Pegaso");
        tglbtnCaballero.setBounds(220, 341, 300, 40);
        tglbtnCaballero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tglbtnCaballero.isSelected()){
                    tglbtnCaballero.setText("Caballero Dragon");
                }else{
                    tglbtnCaballero.setText("Caballero Pegaso");
                }
            }
        });
        add(tglbtnCaballero);




        //Si el fichero de opciones existe, lo leo y marco lo que está escrito en él
        File opciones=new File("./opciones.ddayvacp");
        if (opciones.exists()){
            try {
                BufferedReader br=new BufferedReader(new FileReader(opciones));
                if(br.readLine().equals("dragon")){
                    tglbtnCaballero.doClick();
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("parteAndroid/portada2.jpg"));

            g.drawImage(img, 0, 0,800,800, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

