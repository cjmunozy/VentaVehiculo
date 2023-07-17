
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Auto extends Vehiculo {
    private int vidrios;
    private String transmision;

    public Auto(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, TipoVehiculo tipoVehiculo, int vidrios, String transmision) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, tipoVehiculo);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public int getVidrios() {
        return vidrios;
    }

    public void setVidrios(int vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
    
    public static Auto pedirDatosAuto(Scanner sc){
        Vehiculo v = Vehiculo.pedirDatosVehiculo(sc);
        System.out.println("Ingrese el número de vidrios: ");
        int vidriosU = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el tipo de transmisión: ");
        String transmisionU = sc.nextLine();
        return new Auto(v.placa, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, TipoVehiculo.AUTO, vidriosU, transmisionU);
    }
}
