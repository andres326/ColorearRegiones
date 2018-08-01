package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Estudiantes
 */
public class Ventana extends JFrame {

    private PanelGrafo1 panelGrafo1;
    private int NClicks1, NClicks2;
    private PanelMatriz1 panelMatriz1;

    public Ventana() {
        this.setLayout(null);
        this.setSize(600, 730);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        

        setNClicks1(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de nodos del grafo")));

        panelGrafo1 = new PanelGrafo1(this);
        panelGrafo1.setBounds(20, 10, 500, 300);
        panelGrafo1.setBorder(new TitledBorder("Grafo "));
        add(panelGrafo1);
        
        
        panelMatriz1 = new PanelMatriz1(this);
        panelMatriz1.setBounds(20, 320, 300, 330);
        panelMatriz1.setBorder(new TitledBorder("Matriz Adjunta "));
        add(panelMatriz1);
        
    }

    public PanelGrafo1 getPanelGrafo1() {
        return panelGrafo1;
    }

    public PanelMatriz1 getPanelMatriz1() {
        return panelMatriz1;
    }

    

    public int getNClicks1() {
        return NClicks1;
    }

    public void setNClicks1(int NClicks) {
        this.NClicks1 = NClicks;
    }
    
    
}
