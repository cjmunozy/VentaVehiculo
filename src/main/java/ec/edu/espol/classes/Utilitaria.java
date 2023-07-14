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
    public Utilitaria(){
        MenuOpciones();
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
    
    Scanner sc = new Scanner(System.in);
    
    public void MenuOpciones(){
        System.out.println("----- MENÚ DE OPCIONES -----");
        System.out.println("1. Vendedor");
        System.out.println("2. Comprador");
        System.out.println("3. Salir");
        System.out.println("Digite el número de la sección a la que quiere acceder: ");
        int numEleccion = sc.nextInt();
        switch(numEleccion){
            case 1 -> OpcionesVendedor();
            case 2 -> OpcionesComprador();
            case 3 -> System.out.println("\n ¡Gracias por utilizar nuestro sistema, esperamos verte en otra ocasión!");
                
        }
               
    }
    
    public void OpcionesVendedor(){
        System.out.println("\n----- OPCIONES DEL VENDEDOR ----- \n");
        System.out.println("1. Registrar un nuevo vendedor");
        System.out.println("2. Registrar un nuevo vehículo");
        System.out.println("3. Aceptar oferta");
        System.out.println("4. Regresar");
        System.out.println("\n Ingrese el número de la opción que desea escoger: ");
        int numEleccion = sc.nextInt();
        switch(numEleccion){
            case 1:
                Vendedor.registrarVendedor();
                break;
            case 4:
                MenuOpciones();

        }
        
        
    }
    
    public void OpcionesComprador(){
        System.out.println("\n ----- OPCIONES DEL COMPRADOR ----- \n");
        System.out.println("1. Registrar un nuevo comprador");
        System.out.println("2. Ofertar por un vehículo");
        System.out.println("\n Ingrese el número de la opción que desea escoger: ");
        int numEleccion = sc.nextInt();
        switch(numEleccion){
            case 1:
        }
    }
}
