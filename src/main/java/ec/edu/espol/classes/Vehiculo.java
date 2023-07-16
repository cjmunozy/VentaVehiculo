
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    protected ArrayList<Oferta> listaOfertas;

    public Vehiculo(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color, String tipoCombustible, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.listaOfertas = new ArrayList<>();
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
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio); 

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void saveFile(ArrayList<Vehiculo> vehiculos, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Vehiculo v: vehiculos)
                pw.println(v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+v.año+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Vehiculo> readFile(String nomFile){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vehiculo v = new Vehiculo(tokens[0], tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Double.parseDouble(tokens[5]), tokens[6], tokens[7], Double.parseDouble(tokens[8]));
                vehiculos.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return vehiculos;
    }
    
    public static Vehiculo pedirDatosVehiculo(){
        Scanner sc = new Scanner(System.in);
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
        System.out.println("Ingrese el color: ");
        sc.next();
        String colorU = sc.nextLine();
        System.out.println("Ingrese el tipo de combustible: ");
        String tipoCombustibleU = sc.nextLine();
        System.out.println("Ingrese el precio: ");
        double precioU = sc.nextDouble();
        
        return new Vehiculo(placaU, marcaU, modeloU, tipoMotorU, añoU, recorridoU, colorU, tipoCombustibleU, precioU);
    }
  
    public ArrayList<Vehiculo> busquedaDeVehiculo(ArrayList<Vehiculo> vehiculos){
        ArrayList<Vehiculo> vehiculosPorBusqueda = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese los criterios de búsqueda del vehiculo que desea:");
        System.out.print("Ingrese el tipo de vehículo (opcional) : ");
        String tipoVehiculo = scanner.nextLine();
        System.out.print("Ingrese el Recorrido mínimo (opcional) : ");
        String recoMinString = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el Recorrido máximo (opcional) : ");
        String recoMaxString = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el Año mínimo (opcional) : ");
        String añoMinString = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el Año máximo (opcional) : ");
        String añoMaxString = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el Precio mínimo (opcional) : ");
        String precMinString = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Ingrese el Precio máximo (opcional) : ");
        String precMaxString = scanner.nextLine();
        scanner.nextLine();
        
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

        for (Vehiculo vehiculo : vehiculos) {
        if (encontrarVehiculos(tipoVehiculo, recoMin, recoMax, añoMin, añoMax, precMin, precMax, vehiculos)) {
            vehiculosPorBusqueda.add(vehiculo);
        
        }
    }
    return vehiculosPorBusqueda;
}
    public boolean encontrarVehiculos(String tipoVehiculo,double recoMin,double recoMax,int añoMin,int añoMax,double precMin,double precMax, ArrayList<Vehiculo> vehiculos){
        boolean valor = false;
        for (Vehiculo vehiculo : vehiculos) {
        boolean reco = vehiculo.getRecorrido() >= recoMin && vehiculo.getRecorrido() <= recoMax;
        boolean años = vehiculo.getAño() >= añoMin && vehiculo.getAño() <= añoMax;
        boolean prec = vehiculo.getPrecio() >= precMin && vehiculo.getPrecio() <= precMax;
        boolean esTipoVehiculo = false;

         if (tipoVehiculo == null || tipoVehiculo.isEmpty()) {
        esTipoVehiculo = true;
        } else if (tipoVehiculo.equalsIgnoreCase("auto") && vehiculo instanceof Auto) {
        esTipoVehiculo = true;
        } else if (tipoVehiculo.equalsIgnoreCase("camioneta") && vehiculo instanceof Camioneta) {
        esTipoVehiculo = true;
        }
        valor = reco && años && prec && esTipoVehiculo;
        }
    return valor;

}    
    
    public void eliminarVehiculo(Vehiculo vehiculo) {
        if (vehiculo instanceof Auto) {
        Auto auto = (Auto) vehiculo;
        ArrayList<Auto> autos = Auto.readFileA("autos.txt");
        autos.remove(auto);
        Auto.saveFileA(autos, "autos.txt");
    } else if (vehiculo instanceof Camioneta) {
        Camioneta camioneta = (Camioneta) vehiculo;
        ArrayList<Camioneta> camionetas = Camioneta.readFileC("camionetas.txt");
        camionetas.remove(camioneta);
        Camioneta.saveFileC(camionetas, "camionetas.txt");
    } else if (vehiculo instanceof Moto) {
        Moto moto = (Moto) vehiculo;
        ArrayList<Moto> motos = Moto.readFileM("motos.txt");
        motos.remove(moto);
        Moto.saveFileM(motos, "motos.txt");
    }
}
    
    public void agregarOferta(Oferta oferta) {
        listaOfertas.add(oferta);
    }
    
     public void mostrarOferta(Vehiculo vehiculo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Se han realizado " + listaOfertas.size() + " ofertas para el vehículo " + vehiculo.marca + " " + vehiculo.modelo);
        for (int i = 0; i < listaOfertas.size(); i++) {
            Oferta oferta = listaOfertas.get(i);
            System.out.println("Oferta " + i+1);
            System.out.println("Correo: " + oferta.getComprador().getCorreo());
            System.out.println("Precio Ofertado: " + oferta.getPrecio());
            System.out.println("1. Siguiente Oferta");
            System.out.println("2. Anterior Oferta");
            System.out.println("3. Aceptar Oferta");
            int op = scanner.nextInt();
            switch (op) {
            case 1 -> i++;
            case 2 -> i--;
            case 3 -> {
                Vendedor.aceptarOferta(oferta);
                return;
                }
            default -> System.out.println("Opción no encontrada./nIngrese nuevamente su opción"); }
        }
    }
    
    public void mostrarDetallesVehiculo(Vehiculo vehiculo){
        System.out.println("Información del vehículo:");
        System.out.println("Placa: " + vehiculo.placa);
        System.out.println("Marca: " + vehiculo.marca);
        System.out.println("Tipo de Motor" +vehiculo.tipoMotor);
        System.out.println("Modelo: " + vehiculo.modelo);
        System.out.println("Año: " + vehiculo.año);
        System.out.println("Tipo de Combustible" +vehiculo.tipoCombustible);
        System.out.println("Color" +vehiculo.color);
        System.out.println("Recorrido: " + vehiculo.recorrido);
        System.out.println("Precio: " + vehiculo.precio);   
    }
}
