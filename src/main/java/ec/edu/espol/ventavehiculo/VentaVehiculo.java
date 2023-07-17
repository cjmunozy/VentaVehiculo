/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.ventavehiculo;
import ec.edu.espol.classes.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author User A1
 */
public class VentaVehiculo {

    public static void main(String[] args) {
        ArrayList<Persona> usuarios = Persona.cargarUsuarios("usuarios.txt");
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("vehiculos.txt");
        ArrayList<Oferta> ofertas = Oferta.cargarOfertas("ofertas.txt");
        if(!ofertas.isEmpty())
            Utilitaria.relacionar(usuarios, vehiculos, ofertas);
        System.out.println("Bienvenido a VentaVehiculo");
        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            opcion = Utilitaria.MenuOpciones(sc);
            switch(opcion){
                case 1 -> opcion = Utilitaria.OpcionesVendedor(usuarios, vehiculos, ofertas, sc);
                case 2 -> opcion = Utilitaria.OpcionesComprador(usuarios, vehiculos, ofertas, sc);
                case 3 -> System.out.println("\n ¡Gracias por utilizar nuestro sistema, esperamos verte en otra ocasión!");
                default -> {
                    System.out.println("Opción no encontrada.");
                    System.out.println("");
                    opcion = -1;
                }
            }
        }while(opcion < 1 || opcion > 3);
    }
}
