/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author ssaquisi
 */
public class Utilitaria {
    private Utilitaria(){
    }
     public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
 
        return hexString.toString();
    }
    
    public static String claveHash(String clave){
        String hashClave = "";
        try{
            hashClave = Utilitaria.toHexString(getSHA(clave));
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        
        return hashClave;
    }
    
    public static int MenuOpciones(Scanner sc){
        System.out.println("\n----- MENÚ DE OPCIONES -----");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.println("3. Salir");
        System.out.println("Digite el número de la sección a la que quiere acceder: ");
        int numEleccion = sc.nextInt();
        return numEleccion;
    }
    
    public static int OpcionesVendedor(ArrayList<Persona> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Scanner sc){
        int numEleccion;
        do{
            System.out.println("\n----- OPCIONES DEL VENDEDOR ----- \n");
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehículo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            System.out.println("\n Ingrese el número de la opción que desea escoger: ");
            numEleccion = sc.nextInt();
            sc.nextLine();
            switch(numEleccion){
                case 1:
                    numEleccion = Vendedor.registrarVendedor(usuarios, sc);
                    break;
                case 2:
                    if(Vendedor.comprobarCyC(usuarios, sc))
                        Vendedor.ingresarNuevoVehiculo(vehiculos, sc);
                    numEleccion = -1;
                    break;
                case 3:
                    if(Vendedor.comprobarCyC(usuarios, sc))
                        Vendedor.revisarOfertas(usuarios, ofertas, vehiculos, sc);
                    numEleccion = -1;
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no encontrada.");
                    System.out.println("");
                    numEleccion = -1;
            }
        }while(numEleccion < 1 || numEleccion > 4);
        System.out.println("");
        return -1;
    }
    
    public static int OpcionesComprador(ArrayList<Persona> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Scanner sc){
        int numEleccion;
        do{
            System.out.println("\n ----- OPCIONES DEL COMPRADOR ----- \n");
            System.out.println("1. Registrar un nuevo comprador");
            System.out.println("2. Ofertar por un vehículo");
            System.out.println("3. Regresar");
            System.out.println("\n Ingrese el número de la opción que desea escoger: ");
            numEleccion  = sc.nextInt();
            sc.nextLine();
            switch(numEleccion){
                case 1:
                    numEleccion = Comprador.registrarComprador(usuarios, sc);
                    break;
                case 2:
                    Persona p = Comprador.comprobarCyC(usuarios, sc);
                    if(!(p == null))
                        Comprador.ofertarPorVehiculo(vehiculos, ofertas, p, sc);
                    numEleccion = -1;
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opción no encontrada.");
                    numEleccion = -1;
            }
        }while(numEleccion < 1 || numEleccion > 3);
        return -1;
    }
    
    public static boolean validarCorreo(ArrayList<Persona> usuarios, String correo){
        for(Persona p : usuarios){
            if(correo.equals(p.correo)){
                System.out.println("NO PUEDE REGISTRAR ESTE CORREO PORQUE YA EXISTE EN LA BASE DE DATOS");
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo v : vehiculos){
            if(placa.equals(v.placa)){
                System.out.println("NO PUEDE REGISTRAR ESTE VEHICULO PORQUE LA PLACA YA EXISTE EN LA BASE DE DATOS");
                return false;
            }
        }
        return true;
    }
    
    public static boolean enRango(double valor, double minimo, double maximo){
        return valor >= minimo && valor <= maximo;
    }
    
    public static boolean enRango(int valor, int minimo, int maximo){
        return valor >= minimo && valor <= maximo;
    }
    
    public static void relacionar(ArrayList<Persona> usuarios, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas){
        for(Persona p : usuarios){
            if(p instanceof Comprador){
                for(Oferta o : ofertas){
                    if(o.getCorreo().equals(((Comprador) p).correo)){
                        o.setComprador((Comprador) p);
                        ((Comprador) p).agregarOferta(o);
                    }
                }
            }
        }
        for(Vehiculo v : vehiculos){
            for(Oferta o : ofertas){
                if(o.getPlaca().equals(v.placa)){
                    o.setVehiculo(v);
                    v.agregarOferta(o);
                }
            }
        }
    }
}