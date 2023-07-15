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
public class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoMotor;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;

    public Vehiculo(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio); 

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Vehiculo> vehiculos, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Vehiculo v: vehiculos)
                pw.println(v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+v.año+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Vehiculo> readFile(String nomFile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vehiculo v = new Vehiculo(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]));
                vehiculos.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return vehiculos;
    }
    
    public static Vehiculo pedirDatosVehiculo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR UN NUEVO VEHICULO \n");
        System.out.println("Ingrese la placa: ");
        String placaU = sc.nextLine();
        System.out.println("Ingrese la marca: ");
        String marcaU = sc.nextLine();
        System.out.println("Ingrese el modelo: ");
        String modeloU = sc.nextLine();
        System.out.println("Ingrese el tipo de motor: ");
        String tipoMotorU = sc.nextLine();
        System.out.println("Ingrese el año: ");
        int añoU = sc.nextInt();
        System.out.println("Ingrese el recorrido: ");
        double recorridoU = sc.nextDouble();
        System.out.println("Ingrese el color: ");
        sc.next();
        String colorU = sc.nextLine();
        System.out.println("Ingrese el tipo de combustible: ");
        String tipoCombustibleU = sc.nextLine();
        System.out.println("Ingrese el precio: ");
        double precioU = sc.nextDouble();
        
        return new Vehiculo(placaU, marcaU, modeloU, tipoMotorU, añoU, recorridoU, colorU, tipoCombustibleU, precioU);
    }
    
    
}
