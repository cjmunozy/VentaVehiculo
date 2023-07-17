
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public class Comprador extends Persona{
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(nombres, apellidos, organizacion, correo, clave, TipoPersona.COMPRADOR);
        this.ofertas = new ArrayList<>();
    }

    public Comprador(String nombres, String correo) {
        super(nombres, correo);
        this.ofertas = new ArrayList<>();
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    Scanner sc = new Scanner(System.in);

    
    public static int registrarComprador(ArrayList<Persona> usuarios, Scanner sc){
        boolean validacion = false;
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO COMPRADOR \n");
        System.out.println("Ingrese los nombres: ");
        String nombresU = sc.nextLine();
        System.out.println("Ingrese los apellidos: ");
        String apellidosU = sc.nextLine();
        System.out.println("Ingrese la organización: ");
        String organizacionU = sc.nextLine();
        String correoU;
        do{
            System.out.println("Ingrese el correo electrónico: ");
            correoU = sc.nextLine();
            validacion = Utilitaria.validarCorreo(usuarios, correoU);
        }while(validacion == false);
        System.out.println("Ingrese la clave: ");
        String claveU = sc.nextLine();
        Persona pNuevo = new Comprador(nombresU, apellidosU, organizacionU, correoU, Utilitaria.claveHash(claveU));
        usuarios.add(pNuevo);
        pNuevo.saveFile("usuarios.txt");
        System.out.println("Comprador registrado con éxito");
        return -1;
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
    
    public static ArrayList<Vehiculo> busquedaDeVehiculo(ArrayList<Vehiculo> vehiculos, Scanner sc){
        ArrayList<Vehiculo> vehiculosPorBusqueda = new ArrayList<>();
        System.out.println("Ingrese los criterios de búsqueda del vehiculo que desea:");
        System.out.println("Ingrese el tipo de vehículo : ");
        System.out.println("1. Motocicleta");
        System.out.println("2. Auto");
        System.out.println("3. Camioneta");
        System.out.println("4. Todos los tipos");
        int tipo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese el Recorrido mínimo (opcional) : ");
        String recoMinString = sc.nextLine();
        System.out.print("Ingrese el Recorrido máximo (opcional) : ");
        String recoMaxString = sc.nextLine();
        System.out.print("Ingrese el Año mínimo (opcional) : ");
        String añoMinString = sc.nextLine();
        System.out.print("Ingrese el Año máximo (opcional) : ");
        String añoMaxString = sc.nextLine();
        System.out.print("Ingrese el Precio mínimo (opcional) : ");
        String precMinString = sc.nextLine();
        System.out.print("Ingrese el Precio máximo (opcional) : ");
        String precMaxString = sc.nextLine();
        
        String tipoVehiculo = null;
        switch(tipo){
            case 1 -> tipoVehiculo = "MOTOCICLETA";
            case 2 -> tipoVehiculo = "CAMIONETA";
            case 3 -> tipoVehiculo = "AUTO";
        }
        
        double recoMin = 0;
        if (!recoMinString.equals("")) {
            recoMin = Double.parseDouble(recoMinString);
        }

        double recoMax = Double.MAX_VALUE;
        if (!recoMaxString.equals("")) {
            recoMax = Double.parseDouble(recoMaxString);
        }

        int añoMin = 1900;
        if (!añoMinString.equals("")) {
            añoMin = Integer.parseInt(añoMinString);
        }

        int añoMax = LocalDate.now().getYear() + 1;
        if (!añoMaxString.equals("")) {
            añoMax = Integer.parseInt(añoMaxString);
        }

        double precMin = 0;
        if (!precMinString.equals("")) {
            precMin = Double.parseDouble(precMinString);
        }

        double precMax = Double.MAX_VALUE;
        if (!precMaxString.equals("")) {
            precMax = Double.parseDouble(precMaxString);
        }
        
        for(Vehiculo v : vehiculos){
            if(tipoVehiculo == null && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                vehiculosPorBusqueda.add(v);
            else if(TipoVehiculo.CAMIONETA.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                vehiculosPorBusqueda.add(v);
            else if(TipoVehiculo.AUTO.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                vehiculosPorBusqueda.add(v);
            else if(TipoVehiculo.MOTOCICLETA.equals(TipoVehiculo.valueOf(tipoVehiculo)) && encontrarVehiculos(v, recoMin, recoMax, añoMin, añoMax, precMin, precMax))
                vehiculosPorBusqueda.add(v);
        }
    return vehiculosPorBusqueda;
    }

    public static boolean encontrarVehiculos(Vehiculo vehiculo,double recoMin,double recoMax,int añoMin,int añoMax,double precMin,double precMax){
        boolean recorrido = Utilitaria.enRango(vehiculo.recorrido, recoMin, recoMax);
        boolean años = Utilitaria.enRango(vehiculo.año, añoMin, añoMax);
        boolean precio = Utilitaria.enRango(vehiculo.precio, precMin, precMax);
        return recorrido && años && precio;
    }
    
    public static void ofertarPorVehiculo(ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, Persona p, Scanner sc) {
        ArrayList<Vehiculo> vehiculosEncontrados = busquedaDeVehiculo(vehiculos, sc);
        if(!vehiculosEncontrados.isEmpty()){
            Vehiculo vehiculo = null;
            ListIterator<Vehiculo> iterator = vehiculosEncontrados.listIterator();
            int contador = 0;
            int opc;
            while(iterator.hasNext()){
                if(!iterator.hasPrevious()){
                    vehiculo = iterator.next();
                    contador++;
                    if(!iterator.hasNext()){
                        vehiculo.mostrarDetallesVehiculo();
                        System.out.println("1. Hacer Oferta");
                        opc = sc.nextInt();
                        break;
                    }else{
                        do{
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Siguiente Vehículo");
                            System.out.println("2. Hacer Oferta");
                            opc = sc.nextInt();
                        }while(opc < 1 || opc > 2);
                        if(opc == 2)
                            break;
                    }
                }else{
                    vehiculo = iterator.next();
                    contador++;
                    if(iterator.hasNext()){
                        do{
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Siguiente Vehículo");
                            System.out.println("2. Anterior Vehículo");
                            System.out.println("3. Hacer Oferta");
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
                            vehiculo.mostrarDetallesVehiculo();
                            System.out.println("1. Anterior Vehículo");
                            System.out.println("2. Hacer Oferta");
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
            if(vehiculo != null){
                System.out.println("Ingrese el precio ofertado:");
                double precioOferta = sc.nextDouble();
                sc.nextLine();
                Oferta oferta = new Oferta(vehiculo, (Comprador) p, precioOferta);
                vehiculo.agregarOferta(oferta);
                ofertas.add(oferta);
                ((Comprador) p).agregarOferta(oferta);
                oferta.saveFile("ofertas.txt", true);
                System.out.println("Su oferta ha sido realizada correctamente!");
                System.out.println("");
            }
        }else
            System.out.println("Ningún vehículo coincide con sus criterios de búsqueda");
    }
    
    public static Persona comprobarCyC(ArrayList<Persona> usuarios, Scanner sc){
        System.out.println("\nPOR FAVOR, COMPROBEMOS SUS DATOS");
        System.out.println("Ingrese su correo electronico: ");
        String correoE = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseñaE = sc.nextLine();
        for (Persona p: usuarios){
            if (p instanceof Comprador && correoE.equals(p.correo) && Utilitaria.claveHash(contraseñaE).equals(p.clave)){
                System.out.println("Correo y clave válidos");
                System.out.println("");
                return p;
            }
        }
        System.out.println("Correo o clave incorrectos");
        System.out.println("");
        return null;
    }
    
    public void agregarOferta(Oferta o){
        this.ofertas.add(o);
    }
}
