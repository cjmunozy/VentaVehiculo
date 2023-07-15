
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
                String tipoCombustible, double precio, int vidrios, String transmision) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio);
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
    
    public void saveFileA(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio+"|"+this.vidrios+"|"+this.transmision); 

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFileA(ArrayList<Auto> autos, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Auto a: autos)
                pw.println(a.placa+"|"+a.marca+"|"+a.modelo+"|"+a.tipoMotor+"|"+a.año+"|"+a.recorrido+"|"+a.color+"|"+a.tipoCombustible+"|"+a.precio+"|"+a.vidrios+"|"+a.transmision);
                
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Auto> readFileA(String nomFile){
        ArrayList<Auto> autos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Auto a = new Auto(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]), Integer.parseInt(tokens[9]), tokens[10]);
                autos.add(a);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return autos;
    }
    
    public static Auto pedirDatosAuto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR UN NUEVO VEHICULO \n");
        Vehiculo v = Vehiculo.pedirDatosVehiculo();
        System.out.println("Ingrese el numero de vidrios: ");
        int vidriosU = sc.nextInt();
        System.out.println("Ingrese la transmision: ");
        sc.next();
        String transmisionU = sc.nextLine();
        return new Auto(v.placa, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio, vidriosU, transmisionU);
    }
    
    
}
