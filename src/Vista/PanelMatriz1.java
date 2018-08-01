/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Grafo;
import Modelo.Regiones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Estudiantes
 */
public class PanelMatriz1 extends JPanel implements ActionListener {

    
    private JButton btnRegiones;
    private JTextField[][] txtMatriz;
    private Ventana ventana;
    private Grafo grafo;
    private boolean pintar;
    private boolean regiones;
    private int m[][];
    private Regiones region;
    

    public PanelMatriz1(Ventana aThis) {

        ventana = aThis;
        this.setLayout(null);
        grafo = new Grafo();
        pintar = false;
        regiones = false;
        
        
        btnRegiones = new JButton("Pintar Regiones");
        btnRegiones.setBounds(30, 290, 200, 20);
        btnRegiones.addActionListener(this);
        add(btnRegiones);

        txtMatriz = new JTextField[ventana.getNClicks1()][ventana.getNClicks1()];
        
        crearMatriz();
        
    }

    public void crearMatriz() {
        for (int i = 0; i < txtMatriz[0].length; i++) {
            for (int j = 0; j < txtMatriz[0].length; j++) {
                txtMatriz[i][j] = new JTextField("0");
                txtMatriz[i][j].setBounds((i * 25) + 20, (j * 25) + 30, 20, 20);
                add(txtMatriz[i][j]);
            }
        }
    }

    public int[][] matriz() {

        m = new int[txtMatriz[0].length][txtMatriz[0].length];
        for (int i = 0; i < ventana.getNClicks1(); i++) {
            for (int j = 0; j < ventana.getNClicks1(); j++) {
                if (Integer.parseInt(txtMatriz[j][i].getText()) > 1) {
                    JOptionPane.showMessageDialog(null, "Sólo se permiten valores '0' y '1'", "ERROR", 0);
                    pintar = false;
                    ventana.getPanelGrafo1().repaint();
                }
                if (i == j) {
                    if (Integer.parseInt(txtMatriz[j][i].getText()) != 0) {
                        JOptionPane.showMessageDialog(null, "No sepermiten 1 en la diagonal", "ERROR", 0);
                        pintar = false;
                        ventana.getPanelGrafo1().repaint();
                    }
                }
                int n = Integer.parseInt(txtMatriz[j][i].getText());
                m[i][j] = n;
            }
        }
        region.matriz(txtMatriz);
        return m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnRegiones == e.getSource()){
            region = new Regiones(ventana.getNClicks1());
            region.matriz(txtMatriz);
//            if(region.examinarInterseccion(ventana.getPanelGrafo1().getAregloPosiciones())){
//                JOptionPane.showMessageDialog(null, "Se intersectan aristas");
//                pintar=true;
//                grafo.setMatriz(matriz());
//                grafo.setClik(ventana.getNClicks1());
//                ventana.getPanelGrafo1().repaint();
//            }else{
                //pintar = false;
                //ventana.getPanelGrafo1().setEvento(false);
                grafo.setMatriz(matriz());
                grafo.setClik(ventana.getNClicks1());
                regiones=true;
                region.colorearRegiones();
                ventana.getPanelGrafo1().repaint();
                //regiones=false;
            //}
        }
    }

    public boolean isPintar() {
        return pintar;
    }

    public boolean isRegiones() {
        return regiones;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public JTextField[][] getTxtMatriz() {
        return txtMatriz;
    }

    public Regiones getRegion() {
        return region;
    }

    public void setRegiones(boolean regiones) {
        this.regiones = regiones;
    }
    
    
}
