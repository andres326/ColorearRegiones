package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Mafe
 */
public class Grafo {

 
    private CoordenadasGrafo a[];
    private int[][] matriz;
    
    public Grafo() {
        matriz = new int[clik][clik];
    }
    
    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getClik() {
        return clik;
    }

    public void setClik(int clik) {
        this.clik = clik;
    }
    private int clik;

    
    
    public void guardarArreglo(CoordenadasGrafo a[]){        
        this.a = a;        
    }

    public void pintar(Graphics p) {
        
        for (int i = 0; i < clik; i++) {
            double x = (a[i].getX());
            double y = (a[i].getY());
            int x1=(int)x;
            int y1=(int)y;
            p.setColor(Color.red);
            p.fillOval((int)x1, (int)y1, 10, 10);
            p.setColor(Color.blue);
            p.drawString(String.valueOf(i+1), (int)x, (int)y);
            for (int j = 0; j < clik; j++) {
                if (matriz[i][j] == 1) {
                    double xGrafo = a[j].getX();
                    double yGrafo = a[j].getY();
                    int x2= (int)xGrafo;
                    int y2= (int)yGrafo;
                    p.setColor(Color.red);
                    p.fillOval((int)x2, (int)y2, 10, 10);
                    p.setColor(Color.black);
                    p.drawString(String.valueOf(i+1), (int)x, (int)y);
                    p.setColor(Color.black);
                    p.drawLine(x1+5, y1+5, x2+5, y2+5);
                }
            }
        }
    }
//    public void pintarVertices(Graphics p) {
//        for (int i = 0; i < clik; i++) {
//            double x = (a[i].getX());
//            double y = (a[i].getY());
//            int x1=(int)x;
//            int y1=(int)y;
//            p.setColor(Color.blue);
//            p.drawString(String.valueOf(i+1), (int)x, (int)y);
//            for (int j = 0; j < clik; j++) {
//                if (matriz[i][j] == 1) {
//                    double xGrafo = a[j].getX();
//                    double yGrafo = a[j].getY();
//                    int x2= (int)xGrafo;
//                    int y2= (int)yGrafo;
//                    p.setColor(Color.blue);
//                    p.drawString(String.valueOf(i+1), (int)x, (int)y);
//                }
//            }
//        }
//    }
    

   

}
