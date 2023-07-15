
package ec.edu.espol.classes;
import ec.edu.espol.classes.Vehiculo;
import ec.edu.espol.classes.Utilitaria;
import ec.edu.espol.classes.Persona;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Vendedor extends Persona{
    private ArrayList<Vehiculo> vehiculos;
    
    public Vendedor(String nombres, String apellidos, String organizacion, String correo_electronico, String clave, ArrayList<Vehiculo> vehiculos) {
        super(nombres, apellidos, organizacion, correo_electronico, clave);
        this.vehiculos = vehiculos;
    }

    public Vendedor(String nombres, String apellidos, String organizacion, String correo_electronico, String clave) {
        super(nombres, apellidos, organizacion, correo_electronico, clave);
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
    

    public static void saveFile(ArrayList<Persona> vendedores, String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            for (Persona v: vendedores)
                pw.println(v.nombres+"|"+v.apellidos+"|"+v.organizacion+"|"+v.correo_electronico+"|"+v.clave); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Persona> readFile(String nomFile){
        ArrayList<Persona> vendedores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vendedor v = new Vendedor(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                vendedores.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return vendedores;
    }
    
    Scanner sc = new Scanner(System.in);

    
    public static void registrarVendedor(){
        ArrayList<Persona> usuarios = Vendedor.readFile("vendedores.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO VENDEDOR \n");
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
        
        System.out.println("Ingrese la clave:");
        String claveU = sc.nextLine();
        
        Vendedor pNuevo = new Vendedor(nombresU, apellidosU, organizacionU, correo, Utilitaria.claveHash(claveU));
        pNuevo.saveFile("vendedores.txt");
    }
    
    public static void ingresarNuevoVehiculo(){
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("motocicletas.txt");
        ArrayList<Auto> autos = Auto.readFileA("autos.txt");
        ArrayList<Camioneta> camionetas = Camioneta.readFileC("camionetas.txt");
        
        if (Vendedor.comprobarCyC()){
            Scanner sc = new Scanner(System.in);
            System.out.println("\n--- TIPO DE VEHICULOS DISPONIBLES PARA REGISTRAR ---");
            System.out.println("1. Motocicleta");
            System.out.println("2. Auto");
            System.out.println("3. Camioneta");
            System.out.println("Ingrese el número del tipo de vehículo que desea registrar: ");
            int numT = sc.nextInt();
            switch(numT){
                case 1:
                    Vehiculo moto = Vehiculo.pedirDatosVehiculo();
                    for (Vehiculo v: vehiculos){
                        if (moto.placa.equals(v.placa)){
                            System.out.println("NO PUEDE REGISTRAR ESTE VEHICULO PORQUE LA PLACA YA EXISTE EN LA BASE DE DATOS");
                            return;
                        }
                    }
                    moto.saveFile("motocicletas.txt");
                    break;
                case 2:
                    Auto auto = Auto.pedirDatosAuto();
                    for (Auto a: autos){
                        if (auto.placa.equals(a.placa)){
                            System.out.println("NO PUEDE REGISTRAR ESTE VEHICULO PORQUE LA PLACA YA EXISTE EN LA BASE DE DATOS");
                            return;
                        }
                    }
                    auto.saveFileA("autos.txt");
                    break;
                case 3:
                    Camioneta camioneta = Camioneta.pedirDatosCamioneta();
                    for (Camioneta c: camionetas){
                        if (camioneta.placa.equals(c.placa)){
                            System.out.println("NO PUEDE REGISTRAR ESTE VEHICULO PORQUE LA PLACA YA EXISTE EN LA BASE DE DATOS");
                            return;
                        }
                    }
                    camioneta.saveFileC("camionetas.txt");
                    break;
            }
            
        }
    } 
    

    @Override
    public String toString(){
        return "Nombres:"+this.nombres+", Apellidos: "+this.apellidos+", Organización: "+this.organizacion+", Correo: "+this.correo_electronico+", Clave: "+this.clave;
    }
//    
    
    public static boolean comprobarCyC(){
        Scanner sc = new Scanner(System.in);
        System.out.println("POR FAVOR, COMPROBEMOS SUS DATOS");
        System.out.println("Ingrese su correo electronico: ");
        String correoE = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseñaE = sc.nextLine();
        
        ArrayList<Persona> usuarios = Vendedor.readFile("vendedores.txt");
        for (Persona p: usuarios){
            if (correoE.equals(p.correo_electronico) && Utilitaria.claveHash(contraseñaE).equals(p.clave)){
                System.out.println("Correo y clave validada");
                return true;
            }
            
            else
                System.out.println("Correo o clave incorrectos");
        }
        
        return false;
    }
    

}
