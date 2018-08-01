/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.CoordenadasGrafo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Estudiantes
 */
public class PanelGrafo1 extends JPanel implements MouseListener {

    private double posX;
    private double posY;

    private CoordenadasGrafo aregloPosiciones[];

    private int clicks = 0;
    int h = 0;
    private boolean evento = false;

    private Ventana ventana;

    PanelGrafo1(Ventana aThis) {
        addMouseListener(this);
        ventana = aThis;
        clicks = ventana.getNClicks1();
        aregloPosiciones = new CoordenadasGrafo[clicks];
        crearPosiciones();
    }


    public void paint(Graphics p) {
        super.paint(p);
        System.out.println(isEvento()+"evento");
        if (isEvento()) {
            for (int i = 0; i < aregloPosiciones.length; i++) {
                if (aregloPosiciones[i].getX() !=0 && aregloPosiciones[i].getY() !=0) {
                    double x = (aregloPosiciones[i].getX());
                    double y = (aregloPosiciones[i].getY());
                    p.setColor(Color.red);
                    p.fillOval((int)x, (int)y, 10,10);
                    p.setColor(Color.black);
                    p.drawString(String.valueOf(i+1), (int)x, (int)y);
                }
            }
        }
        if(ventana.getPanelMatriz1().isPintar()){
            ventana.getPanelMatriz1().getGrafo().pintar(p);
        }if(ventana.getPanelMatriz1().isRegiones()){
            ventana.getPanelMatriz1().getRegion().pintarRegion(p, aregloPosiciones);
            ventana.getPanelMatriz1().getGrafo().pintar(p);
            //ventana.getPanelMatriz1().setRegiones(false);
        }
    }

    public void getPosicionCursor(MouseEvent e) {
        posX = e.getPoint().getX();
        posY = e.getPoint().getY();
    }

    public void imprimir() {
        for (int i = 0; i < aregloPosiciones.length; i++) {
            System.out.println(aregloPosiciones[i].getX()+" "+aregloPosiciones[i].getY());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (h < clicks) {
            getPosicionCursor(e);
            evento = true;
            aregloPosiciones[h].setX( getPosX());
            aregloPosiciones[h].setY(getPosY());
            imprimir();
            System.out.println("---------------------");
            h++;
            repaint();
        } else {
            evento = false;
            JOptionPane.showMessageDialog(null, "No se permiten más clic","ERROR" , 0);
            
        }
        ventana.getPanelMatriz1().getGrafo().guardarArreglo(aregloPosiciones);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean isEvento() {
        return evento;
    }

    public CoordenadasGrafo[] getAregloPosiciones() {
        return aregloPosiciones;
    }

    private void crearPosiciones() {
        for(int i=0; i<aregloPosiciones.length; i++){
            aregloPosiciones[i]=new CoordenadasGrafo();
        }
    }

    public void setEvento(boolean evento) {
        this.evento = evento;
    }
    

}
