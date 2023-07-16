
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Camioneta extends Auto {
    private String traccion;

    public Camioneta(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, int vidrios, String transmision, String traccion) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    
    public void saveFileC(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio+"|"+this.getVidrios()+"|"+this.getTransmision()+"|"+this.traccion); 

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
  
    
    public static void saveFileC(ArrayList<Camioneta> camionetas, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Camioneta c: camionetas)
                pw.println(c.placa+"|"+c.marca+"|"+c.modelo+"|"+c.tipoMotor+"|"+c.año+"|"+c.recorrido+"|"+c.color+"|"+c.tipoCombustible+"|"+c.precio+"|"+c.getVidrios()+"|"+c.getTransmision()+"|"+c.traccion);
                
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Camioneta> readFileC(String nomFile){
        ArrayList<Camioneta> camionetas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Camioneta c = new Camioneta(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]), Integer.parseInt(tokens[9]), tokens[10], tokens[11]);
                camionetas.add(c);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return camionetas;
    }
    
    public static Camioneta pedirDatosCamioneta(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR UN NUEVO VEHICULO \n");
        Auto a = Auto.pedirDatosAuto();
        System.out.println("Ingrese la traccion: ");
        String traccionU = sc.nextLine();
        return new Camioneta(a.placa, a.marca, a.modelo, a.tipoMotor, a.año, a.recorrido, a.color, a.tipoCombustible, a.precio, a.getVidrios(), a.getTransmision(), traccionU);
    }  
}