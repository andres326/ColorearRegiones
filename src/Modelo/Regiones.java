/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author SYSTEM
 */
public class Regiones {
    private Color colores[];
    private ArrayList<Integer> comparables;
    private ArrayList<Integer> examinados;
    private ArrayList<Line2D> aristas;
    private int nodoInicial=0;
    private int nodoExaminado=0;
    private ArrayList<String> regiones;
    private int matriz[][];
    private int numRegiones;
    private int guardaNum1=0;
    private int guardaNum2=0;
    private boolean condicionParada=false;
    private boolean tamano=false;
    
    
    public Regiones(int numero) {
        colores = new Color[10];
        colores();        
        matriz = new int[numero][numero];
        comparables = new ArrayList<>();
        regiones = new ArrayList<>();
        examinados = new ArrayList<>();
        aristas = new ArrayList<>();
    }
    
    public void colorearRegiones(){
        int comparable1=0, comparable2=0, i=0;
        boolean num1=false, num2=false;
        String region="";
        //numRegiones=contarRegiones();
        while(nodoInicial<matriz.length-2){
            nodoInicial=i+1;
            System.out.println("ini "+ nodoInicial);
            iniciar(i);
            region=""+nodoInicial+""+nodoExaminado;
            if(comparables.size()>1){
                System.out.println("entro1");
                tamano=true;
                comparable1=comparables.get(0);
                comparable2=comparables.get(1);
                System.out.println("c2 "+comparable2);
                guardaNum1=comparable1;
                guardaNum2=comparable2;
                if(buscarMatriz(nodoExaminado, comparable1)){
                    regiones.add(region+""+comparable1);
                    System.out.println("entro2");
                    num1=true;
                }if(buscarMatriz(nodoExaminado, comparable2)){
                    regiones.add(region+""+comparable2);
                    System.out.println("entro3");
                    num2=true;
                }if(num1==false && num2==false){
                    System.out.println("entro4");
                    buscarRegiones(nodoExaminado, comparable1, region, guardaNum1);
                    buscarRegiones(nodoExaminado, comparable2, region, guardaNum2);
                }if(num1==false){
                    System.out.println("entro5");
                    buscarRegiones(nodoExaminado, comparable1, region, guardaNum1);
                }if(num2==false){
                    System.out.println("entro6");
                    buscarRegiones(nodoExaminado, comparable2, region, guardaNum1);
                }
            }else{
                tamano=false;
                System.out.println("entro7");
                if(comparables.size()!=0){
                    comparable1=comparables.get(0);
                    System.out.println("c "+comparable1);
                    guardaNum1=comparable1;
                    if(buscarMatriz(nodoExaminado, comparable1)){
                        System.out.println("entro8");
                        regiones.add(region+""+comparable1);
                        num1=true;
                    }if(num1==false){
                        System.out.println("entro9");
                        buscarRegiones(nodoExaminado, comparable1, region, guardaNum1);
                    }
                }
            } 
            if(tamano==false){
                i++;
                region="";
                comparables.clear();
            }else{
                if(buscarMatriz(guardaNum1, guardaNum2)){
                    System.out.println("entro8!");
                    regiones.add(nodoInicial+""+guardaNum1+""+guardaNum2);
                    num1=true;
                    region="";
                    comparables.clear();
                    i++;
                }if(num1==false){
                    System.out.println("entro9!");
                    buscarRegiones(comparable1, comparable2, region, guardaNum1);
                    i++;
                }
            }
            region="";
            num1=false;
        }
        imprimirRegiones();
    }
    
//    public boolean examinarInterseccion(CoordenadasGrafo a[]){
//        boolean interseccion=false;
//        guardarAristas(a);
//        System.out.println("size "+aristas.size());
//        for(int i=0; i<aristas.size();i++){
//            for(int j=i+1; j<aristas.size(); j++){
//                if(aristas.get(i).intersectsLine(aristas.get(j))){
//                    interseccion=true;
//                }
//            }
//        }
//        System.out.println("inter "+interseccion);
//        return interseccion;
//    }
    private void imprimirRegiones(){
        for (int i = 0; i < regiones.size(); i++) {
            System.out.println("region "+ regiones.get(i));
        }
    }
    public int[][] matriz(JTextField m[][]){
        for(int i=0; i<m.length;i++){
            for (int j = 0; j < m.length; j++) {
                matriz[i][j]=Integer.parseInt(m[i][j].getText());
            }
        }
        return matriz;
    }
    private void colores() {
        colores[0] = Color.BLUE;
        colores[1] = Color.GREEN;
        colores[2] = Color.ORANGE;
        colores[3] = Color.GRAY;
        colores[4] = Color.PINK;
        colores[5] = Color.YELLOW;
        colores[6] = Color.CYAN;
        colores[7] = Color.BLACK;
        colores[8] = Color.MAGENTA;
        colores[9] = Color.WHITE;
    }
    public void pintarRegion(Graphics p, CoordenadasGrafo a[]){
        int nodo[] = null;
        Polygon poligono=null;
        int vertice=0, contador=0, real=0;
        double x, y;
        for(int i=0; i<regiones.size();i++){
            String linea = regiones.get(i);
            nodo = new int[linea.length()];
            for(int j=0;j<linea.length();j++){
                real = (int)linea.charAt(j);
                nodo[j]= real-48;
            }
            poligono = new Polygon();
            for(int k=0;k<nodo.length;k++){
                vertice=nodo[k];
                x = a[vertice-1].getX();
                y = a[vertice-1].getY();
                poligono.addPoint((int)x+5, (int)y+5);
            }
            p.setColor(colores[contador]);
            p.fillPolygon(poligono);
            contador++;
        }
        regiones.clear();
        examinados.clear();
    }
    
    private void iniciar(int m) {
        for(int i=nodoInicial;i<matriz.length;i++){
            if(matriz[m][i]==1){
                nodoExaminado=i+1;
                break;
            }
        }
        for (int i=nodoExaminado;i<matriz.length;i++) {
            if(matriz[m][i]==1){
                comparables.add(i+1);
                condicionParada=true;
            }
        }
    }
    
    private void comparar(int m) {
       if(m==matriz.length-2){
           condicionParada=false;
       }
       examinados = new ArrayList<>();
        for (int i=m+1;i<matriz.length;i++) {
            if(matriz[m][i]==1){
                getExaminados().add(i+1);
                System.out.println("i "+getExaminados().get(0));
                condicionParada=true;
            }
        }
    }

    private void buscarRegiones(int nodoExam, int compar, String region, int guarda) {
        int comparable1=0, comparable2=0, nodoini=0;
        if (compar==0 || nodoExaminado==0){
            System.out.println("No hay region");
        }else if(buscarMatriz(guarda, compar)){
            region+=""+guarda;
            regiones.add(region);
        }else if(condicionParada==false){
            System.out.println("No hay mas");
        }
        else{
            nodoini=nodoExam; 
            getExaminados().clear();
            comparar(nodoini-1);
            if(getExaminados().size()>1){
                comparable1=getExaminados().get(0);
                comparable2=getExaminados().get(1);                
                buscarRegiones(comparable1, comparable1, region+""+comparable1, guarda);
                buscarRegiones(comparable2, comparable2, region+""+comparable2, guarda);
            }else if(getExaminados().size()==0){
                System.out.println("no hay relacion");
            }else{
                System.out.println("com "+getExaminados().get(0));
                comparable1=getExaminados().get(0);
                buscarRegiones(comparable1, comparable1, region+""+comparable1, guarda);
            }
        }
    }
    
    private boolean buscarMatriz(int nodoExaminado, int compar) {
        boolean entro=false;
        if(matriz[nodoExaminado-1][compar-1]==1){
            entro=true;
        }
        return entro;
    }

    private int contarRegiones() {
        int contador=0;
        int region;
        for(int i=0; i<(matriz.length)-1;i++){
            for(int j=(i+1);j<matriz.length;j++){
                if(matriz[i][j]==1){
                    contador++;
                }
            }
        }
        region=2-matriz.length+contador;
        return region;
    }

    public ArrayList<Integer> getComparables() {
        return comparables;
    }

    public ArrayList<Integer> getExaminados() {
        return examinados;
    }

//    private void guardarAristas(CoordenadasGrafo[] a) {
//        double x1, x2, y1, y2;
//        for(int i=0; i<a.length-1;i++){
//            x1=a[i].getX();
//            System.out.println("x1 "+x1);
//            y1=a[i].getY();
//            System.out.println("y1 "+y1);
//            for(int j=(i+1);j<a.length;j++){
//                System.out.println("ans "+matriz[i][j]);
//                if(matriz[i][j]==1){
//                    System.out.println("ENTRE!!!");
//                    x2=a[j].getX();
//                    y2=a[i].getY();
//                    agregarLinea(x1, y1, x2, y2);
//                }
//            }
//        }
//    }
//
//    private void agregarLinea(double x1, double y1, double x2, double y2) {
//		double a, b, c, d;
//		double ayx1 = 0, ayx2 = 0;
//		double sx1, sx2, sy1, sy2;
//		double m = (y2 - y1);
//		m /= (x2 - x1);
//		double b1 = (y1 - (m * x1));
//		// primer punto
//		a = 1 + (m * m);
//		b = 2 * ((m * b1) - x1 - (m * y1));
//		c = (x1 * x1) + (b1 * b1) - (2 * b1 * y1) - 400 + (y1 * y1);
//		d = (b * b) - (4 * a * c);
//		ayx1 = ((-1) * b) + Math.sqrt(d);
//		ayx1 = ayx1 / (2 * a);
//		ayx2 = ((-1) * b) - Math.sqrt(d);
//		ayx2 = ayx2 / (2 * a);
//		if (x1 > x2) {
//			if (ayx1 > ayx2) {
//				sx1 = ayx2;
//			} else {
//				sx1 = ayx1;
//			}
//		} else {
//			if (ayx1 > ayx2) {
//				sx1 = ayx1;
//			} else {
//				sx1 = ayx2;
//			}
//		}
//		sy1 = (m * sx1) + b1;
//
//		// segundo punto
//		a = 1 + (m * m);
//		b = 2 * ((m * b1) - x2 - (m * y2));
//		c = (x2 * x2) + (b1 * b1) - (2 * b1 * y2) - 400 + (y2 * y2);
//		d = (b * b) - (4 * a * c);
//		ayx1 = ((-1) * b) + Math.sqrt(d);
//		ayx1 /= (2 * a);
//		ayx2 = ((-1) * b) - Math.sqrt(d);
//		ayx2 /= (2 * a);
//		if (x1 > x2) {
//			if (ayx1 > ayx2) {
//				sx2 = ayx1;
//			} else {
//				sx2 = ayx2;
//			}
//		} else {
//			if (ayx1 > ayx2) {
//				sx2 = ayx2;
//			} else {
//				sx2 = ayx1;
//			}
//		}
//		sy2 = (m * sx2) + b1;
//		// crear linea
//		Line2D l = new Line2D.Double();
//		l.setLine(sx1, sy1, sx2, sy2);
//		aristas.add(l);
//	}
    
    
}
