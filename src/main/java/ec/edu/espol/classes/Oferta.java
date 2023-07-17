
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Oferta {
    private String placa;
    private Vehiculo vehiculo;
    private String correo;
    private Comprador comprador;
    private double precio;

    public Oferta(Vehiculo tipoVehiculo, Comprador comprador, double precio) {
        this(tipoVehiculo.getPlaca(), comprador.getCorreo(), precio);
        this.vehiculo = tipoVehiculo;
        this.comprador = comprador;
    }

    public Oferta(String placa, String correo, double precio) {
        this.placa = placa;
        this.correo = correo;
        this.precio = precio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo tipoVehiculo) {
        this.vehiculo = tipoVehiculo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void saveFile(String nomFile, boolean append){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), append)))
        {
           pw.println(this.placa+"|"+this.correo+"|"+this.precio);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Oferta> ofertas, String nomFile){
        for (Oferta o: ofertas)
            o.saveFile(nomFile, false);
    }
    
    public static ArrayList<Oferta> cargarOfertas(String nomFile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                Oferta o;
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                o = new Oferta(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
                ofertas.add(o);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return ofertas;
    }
    
    public static ArrayList<Oferta> eliminarOfertas(ArrayList<Persona> usuarios, ArrayList<Oferta> ofertas, Vehiculo v) {
        for(int i = 0; i < usuarios.size(); i++){
            Persona p = usuarios.get(i);
            if(p instanceof Comprador){
                Comprador c = (Comprador) p;
                for(int j = 0; j < c.getOfertas().size(); j++){
                    Oferta o = c.getOfertas().get(i);
                    if(v.equals(o.getVehiculo()))
                        c.getOfertas().remove(o);
                }
            }
        }
        for(int i = 0; i < ofertas.size(); i++){
            if(v.equals(ofertas.get(i).getVehiculo())){
                ofertas.remove(i);
            }
        }
        return ofertas;
    }
}
