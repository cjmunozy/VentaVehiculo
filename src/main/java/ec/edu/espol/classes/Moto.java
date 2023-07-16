
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Moto extends Vehiculo{

    public Moto(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio);
    }
    
    public void saveFileM(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio); 

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
  
    
    public static void saveFileM(ArrayList<Moto> motos, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Moto m: motos)
                pw.println(m.placa+"|"+m.marca+"|"+m.modelo+"|"+m.tipoMotor+"|"+m.año+"|"+m.recorrido+"|"+m.color+"|"+m.tipoCombustible+"|"+m.precio);
                
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Moto> readFileM(String nomFile){
        ArrayList<Moto> motos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Moto m = new Moto(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]));
                motos.add(m);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return motos;
    }
    
    public static Moto pedirDatosMoto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR UN NUEVO VEHICULO \n");
        Vehiculo v = Vehiculo.pedirDatosVehiculo();
        sc.next();
        return new Moto(v.placa, v.marca, v.modelo, v.tipoMotor, v.año, v.recorrido, v.color, v.tipoCombustible, v.precio);
    }
    
}  
