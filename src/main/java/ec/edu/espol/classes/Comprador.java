
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Comprador extends Persona{
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Oferta> ofertas) {
        super(nombres, apellidos, organizacion, correo, clave);
        this.ofertas = ofertas;
    }

    public Comprador(String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        super(nombres, apellidos, organizacion, correo_electronico, clave);
    }
    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
           pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo_electronico+"|"+this.clave); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    public static void saveFile(ArrayList<Persona> compradores, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Persona v: compradores)
                pw.println(v.nombres+"|"+v.apellidos+"|"+v.organizacion+"|"+v.correo_electronico+"|"+v.clave); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Persona> readFile(String nomFile){
        ArrayList<Persona> compradores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Comprador c = new Comprador(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                compradores.add(c);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return compradores;
    }
    
    Scanner sc = new Scanner(System.in);

    
    public static void registrarComprador(){
        ArrayList<Persona> usuarios = Comprador.readFile("compradores.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO COMPRADOR \n");
        System.out.println("Ingrese los nombres: ");
        String nombresU = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellidosU = sc.nextLine();
        System.out.println("Ingrese la organización: ");
        String organizacionU = sc.nextLine();
        System.out.println("Ingrese el correo electrónico: ");
        String correo = sc.nextLine();
        
        for (Persona p: usuarios){
            if (correo.equals(p.correo_electronico)){
                System.out.println("NO PUEDE REGISTRAR ESTE CORREO PORQUE YA EXISTE EN LA BASE DE DATOS");
                return;
            }
        }
        
        System.out.println("Ingrese la clave: ");
        String claveU = sc.nextLine();
        
        Comprador pNuevo = new Comprador(nombresU, apellidosU, organizacionU, correo, Utilitaria.claveHash(claveU));
        pNuevo.saveFile("compradores.txt");
    }
    public static Comprador obtenerComprador() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese su información de comprador:");
    
    System.out.print("Nombres: ");
    String nombres = sc.nextLine();
    
    System.out.print("Apellidos: ");
    String apellidos = sc.nextLine();
    
    System.out.print("Organización: ");
    String organizacion = sc.nextLine();
    
    System.out.print("Correo Electrónico: ");
    String correo = sc.nextLine();
    
    System.out.print("Clave: ");
    String clave = sc.nextLine();
    
    return new Comprador(nombres, apellidos, organizacion, correo, clave);
}
    
    public static ArrayList<Vehiculo> busquedaDeVehiculo(){
        ArrayList<Vehiculo> vehiculosPorBusqueda = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los criterios de búsqueda del vehiculo que desea:");
        System.out.print("Ingrese el tipo de vehículo (opcional) : ");
        String tipoVehiculo = scanner.nextLine();
        System.out.print("Ingrese el Recorrido mínimo (opcional) : ");
        String recoMinString = scanner.nextLine();
        System.out.print("Ingrese el Recorrido máximo (opcional) : ");
        String recoMaxString = scanner.nextLine();
        System.out.print("Ingrese el Año mínimo (opcional) : ");
        String añoMinString = scanner.nextLine();
        System.out.print("Ingrese el Año máximo (opcional) : ");
        String añoMaxString = scanner.nextLine();
        System.out.print("Ingrese el Precio mínimo (opcional) : ");
        String precMinString = scanner.nextLine();
        System.out.print("Ingrese el Precio máximo (opcional) : ");
        String precMaxString = scanner.nextLine();
        
        double recoMin = Double.MIN_VALUE;
        if (!recoMinString.isEmpty()) {
            recoMin = Double.parseDouble(recoMinString);
        }

        double recoMax = Double.MAX_VALUE;
        if (!recoMaxString.isEmpty()) {
            recoMax = Double.parseDouble(recoMaxString);
        }

        int añoMin = Integer.MIN_VALUE;
        if (!añoMinString.isEmpty()) {
            añoMin = Integer.parseInt(añoMinString);
        }

        int añoMax = Integer.MAX_VALUE;
        if (!añoMaxString.isEmpty()) {
            añoMax = Integer.parseInt(añoMaxString);
        }

        double precMin = Double.MIN_VALUE;
        if (!precMinString.isEmpty()) {
            precMin = Double.parseDouble(precMinString);
        }

        double precMax = Double.MAX_VALUE;
        if (!precMaxString.isEmpty()) {
            precMax = Double.parseDouble(precMaxString);
        }
        
        ArrayList<Auto> autos = Auto.readFileA("autos.txt");
        for (Auto auto : autos) {
            if (encontrarVehiculos(tipoVehiculo, recoMin, recoMax, añoMin, añoMax, precMin, precMax, autos)) {
                vehiculosPorBusqueda.add(auto);
            }
        }

        ArrayList<Camioneta> camionetas = Camioneta.readFileC("camionetas.txt");
        for (Camioneta camioneta : camionetas) {
            if (encontrarVehiculos(tipoVehiculo, recoMin, recoMax, añoMin, añoMax, precMin, precMax, camionetas)) {
                vehiculosPorBusqueda.add(camioneta);
            }
        }

        ArrayList<Moto> motos = Moto.readFileM("motos.txt");
        for (Moto moto : motos) {
            if (encontrarVehiculos(tipoVehiculo, recoMin, recoMax, añoMin, añoMax, precMin, precMax, motos)) {
                vehiculosPorBusqueda.add(moto);
            }
        }
    return vehiculosPorBusqueda;
    }

    public static boolean encontrarVehiculos(String tipoVehiculo,double recoMin,double recoMax,int añoMin,int añoMax,double precMin,double precMax, ArrayList<? extends Vehiculo> vehiculos){
        boolean valor = false;
        for (Vehiculo vehiculo : vehiculos) {
        boolean reco = vehiculo.getRecorrido() >= recoMin && vehiculo.getRecorrido() <= recoMax;
        boolean años = vehiculo.getAño() >= añoMin && vehiculo.getAño() <= añoMax;
        boolean prec = vehiculo.getPrecio() >= precMin && vehiculo.getPrecio() <= precMax;
        boolean esTipoVehiculo = tipoVehiculo == null || tipoVehiculo.isEmpty()
                || (tipoVehiculo.equalsIgnoreCase("auto") && vehiculo instanceof Auto)
                || (tipoVehiculo.equalsIgnoreCase("camioneta") && vehiculo instanceof Camioneta)
                || (tipoVehiculo.equalsIgnoreCase("moto") && vehiculo instanceof Moto);

        valor = reco && años && prec && esTipoVehiculo;
        }
    return valor;

}    
    
    public static void ofertarPorVehiculo() {
    ArrayList<Vehiculo> vehiculosEncontrados = busquedaDeVehiculo();
    Scanner sc = new Scanner(System.in);
    for (Vehiculo v : vehiculosEncontrados) {
        v.mostrarDetallesVehiculo(v);
        System.out.println("Desea realizar una oferta por este vehículo? (S/N):");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Ingrese el precio ofertado:");
            double precioOferta = sc.nextDouble();
            sc.nextLine();
            Comprador comprador = obtenerComprador();
            Oferta oferta = new Oferta(v,comprador, precioOferta);
            v.agregarOferta(oferta);
            System.out.println("Su oferta ha sido realizada correctamente!");
        } else if (respuesta.equalsIgnoreCase("N")) {
            System.out.println("No se realizará una oferta por este vehículo.");
        } else {
            System.out.println("Respuesta no aceptada.");
        }
    }    
 }
}
