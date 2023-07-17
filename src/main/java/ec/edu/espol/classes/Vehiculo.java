
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;
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
    protected TipoVehiculo tipoVehiculo;
    protected ArrayList<Oferta> listaOfertas;

    public Vehiculo(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio, TipoVehiculo tipoVehiculo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.tipoVehiculo = tipoVehiculo;
        listaOfertas = new ArrayList<>();
    }

    public Vehiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAño() {
        return año;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public double getPrecio() {
        return precio;
    }
    
    public ArrayList<Oferta> getListaOfertas() {
        return listaOfertas;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Vehiculo other = (Vehiculo) o;
        return Objects.equals(this.placa, other.placa);
    }
    
    public void saveFile(String nomFile, boolean append){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), append)))
        {
            pw.print(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio);
            if(this instanceof Camioneta)
                pw.println("|CAMIONETA|"+((Camioneta) this).getVidrios()+"|"+((Camioneta) this).getTransmision()+"|"+((Camioneta) this).getTraccion());
            else if(this instanceof Auto)
                pw.println("|AUTO|"+((Auto) this).getVidrios()+"|"+((Auto) this).getTransmision());
            else
                pw.println("|MOTOCICLETA");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Vehiculo> vehiculos, String nomFile){
        for (Vehiculo v: vehiculos)
            v.saveFile(nomFile, false);
    }
    
    public static ArrayList<Vehiculo> readFile(String nomFile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                Vehiculo v;
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                if(tokens[9].equals("AUTO"))
                    v = new Auto(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]), TipoVehiculo.valueOf(tokens[9]), Integer.parseInt(tokens[10]), tokens[11]);
                else if(tokens[9].equals("CAMIONETA"))
                    v = new Camioneta(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]), TipoVehiculo.valueOf(tokens[9]), Integer.parseInt(tokens[10]), tokens[11], tokens[12]);
                else
                    v = new Vehiculo(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]), TipoVehiculo.valueOf(tokens[9]));
                vehiculos.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return vehiculos;
    }
    
    public static Vehiculo pedirDatosVehiculo(Scanner sc){
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
        sc.nextLine();
        System.out.println("Ingrese el color: ");
        String colorU = sc.nextLine();
        System.out.println("Ingrese el tipo de combustible: ");
        String tipoCombustibleU = sc.nextLine();
        System.out.println("Ingrese el precio: ");
        double precioU = sc.nextDouble();
        sc.nextLine();
        return new Vehiculo(placaU, marcaU, modeloU, tipoMotorU, añoU, recorridoU, colorU, tipoCombustibleU, precioU, TipoVehiculo.MOTOCICLETA);
    }

    public static ArrayList<Vehiculo> eliminarVehiculo(ArrayList<Vehiculo> vehiculos, Vehiculo v) {
        for(int i = 0; i < vehiculos.size(); i++){
            if(vehiculos.get(i).equals(v)){
                vehiculos.remove(v);
            }
        }
        return vehiculos;
    }
    
    public void agregarOferta(Oferta oferta) {
        listaOfertas.add(oferta);
    }
    
    public void mostrarOfertas(ArrayList<Persona> usuarios, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos, Scanner sc) {
        System.out.println(this.marca + " " + this.modelo + " Precio: " + this.precio);
        System.out.println("Se han realizado " + listaOfertas.size() + " ofertas");
        Oferta oferta = null;
        ListIterator<Oferta> iterator = listaOfertas.listIterator();
        int contador = 0;
        int opc;
        while(iterator.hasNext()){
            if(!iterator.hasPrevious()){
                oferta = iterator.next();
                contador++;
                do{
                    System.out.println("Oferta " + contador);
                    System.out.println("Correo: " + oferta.getComprador().getCorreo());
                    System.out.println("Precio Ofertado: " + oferta.getPrecio());
                    System.out.println("1. Siguiente Oferta");
                    System.out.println("2. Aceptar Oferta");
                    opc = sc.nextInt();
                }while(opc < 1 || opc > 2);
                if(opc == 2)
                    break;
            }else{
                oferta = iterator.next();
                contador++;
                if(iterator.hasNext()){
                    do{
                        System.out.println("Oferta " + contador);
                        System.out.println("Correo: " + oferta.getComprador().getCorreo());
                        System.out.println("Precio Ofertado: " + oferta.getPrecio());
                        System.out.println("1. Siguiente Oferta");
                        System.out.println("2. Anterior Oferta");
                        System.out.println("3. Aceptar Oferta");
                        opc = sc.nextInt();
                    }while(opc < 1 || opc > 3);
                    if(opc == 2){
                        iterator.previous();
                        iterator.previous();
                        contador -= 2;
                    }else if(opc == 3)
                        break;
                }else{
                    do{
                        System.out.println("Oferta " + contador);
                        System.out.println("Correo: " + oferta.getComprador().getCorreo());
                        System.out.println("Precio Ofertado: " + oferta.getPrecio());
                        System.out.println("1. Anterior Oferta");
                        System.out.println("2. Aceptar Oferta");
                        opc = sc.nextInt();
                    }while(opc < 1 || opc > 2);
                    if(opc == 1){
                        iterator.previous();
                        iterator.previous();
                        contador -= 2;
                    }else
                        break;
                } 
            }
        }
        if(oferta != null)
            Vendedor.aceptarOferta(usuarios, ofertas, vehiculos, oferta);
    }
    
    public void mostrarDetallesVehiculo(){
        System.out.println("\nInformación del vehículo:");
        System.out.println("Placa: " + this.placa);
        System.out.println("Marca: " + this.marca);
        System.out.println("Tipo de Motor: " + this.tipoMotor);
        System.out.println("Modelo: " + this.modelo);
        System.out.println("Año: " + this.año);
        System.out.println("Tipo de Combustible: " +  this.tipoCombustible);
        System.out.println("Color: " + this.color);
        System.out.println("Recorrido: " + this.recorrido);
        System.out.println("Precio: " + this.precio);
        System.out.println("Tipo de Vehículo: " + this.tipoVehiculo);
        if(this instanceof Camioneta){
            System.out.println("Vidrios: " + ((Auto) this).getVidrios());
            System.out.println("Transmisión: " + ((Auto) this).getTransmision());
            System.out.println("Tracción: " + ((Camioneta) this).getTraccion());
        }else if(this instanceof Auto){
            System.out.println("Vidrios: " + ((Auto) this).getVidrios());
            System.out.println("Transmisión: " + ((Auto) this).getTransmision());
        }
    }
}
