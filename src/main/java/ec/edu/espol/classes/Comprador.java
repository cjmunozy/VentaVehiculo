/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author crisj
 */
public class Comprador extends Persona{
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Oferta> ofertas) {
        super(nombres, apellidos, organizacion, correo, clave);
        this.ofertas = ofertas;
    }

    public Comprador(String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        super(nombres, apellidos, organizacion, correo_electronico, clave);
    }
    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
           pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    public static void saveFile(ArrayList<Persona> compradores, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Persona v: compradores)
                pw.println(v.nombres+"|"+v.apellidos+"|"+v.organizacion+"|"+v.correo_electronico+"|"+v.clave); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Persona> readFile(String nomFile){
        ArrayList<Persona> compradores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Comprador c = new Comprador(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                compradores.add(c);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return compradores;
    }
    
    Scanner sc = new Scanner(System.in);

    
    public static void registrarComprador(){
        ArrayList<Persona> usuarios = Comprador.readFile("compradores.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO COMPRADOR \n");
        System.out.println("Ingrese los nombres: ");
        String nombresU = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellidosU = sc.nextLine();
        System.out.println("Ingrese la organización: ");
        String organizacionU = sc.nextLine();
        System.out.println("Ingrese el correo electrónico: ");
        String correo = sc.nextLine();
        
        for (Persona p: usuarios){
            if (correo.equals(p.correo_electronico)){
                System.out.println("NO PUEDE REGISTRAR ESTE CORREO PORQUE YA EXISTE EN LA BASE DE DATOS");
                return;
            }
        }
        
        System.out.println("Ingrese la clave: ");
        String claveU = sc.nextLine();
        
        Comprador pNuevo = new Comprador(nombresU, apellidosU, organizacionU, correo, Utilitaria.claveHash(claveU));
        pNuevo.saveFile("compradores.txt");
    }


}
