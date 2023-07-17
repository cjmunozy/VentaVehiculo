
package ec.edu.espol.classes;
import ec.edu.espol.correo.Buzon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Vendedor extends Persona{
    
    public Vendedor(String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(nombres, apellidos, organizacion, correo, clave, TipoPersona.VENDEDOR);
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
    
    public static int registrarVendedor(ArrayList<Persona> usuarios, Scanner sc){
        boolean validacion = false;
        System.out.println("\nPOR FAVOR, COMPLETE LOS DATOS PARA REGISTRAR AL NUEVO VENDEDOR \n");
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
        System.out.println("Ingrese la clave:");
        String claveU = sc.nextLine();
        Persona pNuevo = new Vendedor(nombresU, apellidosU, organizacionU, correoU, Utilitaria.claveHash(claveU));
        usuarios.add(pNuevo);
        pNuevo.saveFile("usuarios.txt");
        System.out.println("Vendedor registrado con éxito");
        return -1;
    }
    
    public static void ingresarNuevoVehiculo(ArrayList<Vehiculo> vehiculos, Scanner sc){
        int numT;
        Vehiculo v;
        do{
            System.out.println("\n--- TIPO DE VEHICULOS DISPONIBLES PARA REGISTRAR ---");
            System.out.println("1. Motocicleta");
            System.out.println("2. Auto");
            System.out.println("3. Camioneta");
            System.out.println("Ingrese el número del tipo de vehículo que desea registrar: ");
            numT = sc.nextInt();
            sc.nextLine();
            switch(numT){
                case 1:
                    v = Vehiculo.pedirDatosVehiculo(sc);
                    if(Utilitaria.validarPlaca(vehiculos, v.placa)){
                        vehiculos.add(v);
                        v.saveFile("vehiculos.txt", true);
                    }else
                        numT = -1;
                    break;
                case 2:
                    v = Auto.pedirDatosAuto(sc);
                    if(Utilitaria.validarPlaca(vehiculos, v.placa)){
                        vehiculos.add(v);
                        v.saveFile("vehiculos.txt", true);
                    }else
                        numT = -1;
                    break;
                case 3:
                    v = Camioneta.pedirDatosCamioneta(sc);
                    if(Utilitaria.validarPlaca(vehiculos, v.placa)){
                        vehiculos.add(v);
                        v.saveFile("vehiculos.txt", true);
                    }else
                        numT = -1;
                    break;
            }
        }while(numT < 1 || numT > 3);
        System.out.println("Vehículo registrado con éxito");
    } 
    

    @Override
    public String toString(){
        return "Nombres:"+this.nombres+", Apellidos: "+this.apellidos+", Organización: "+this.organizacion+", Correo: "+this.correo+", Clave: "+this.clave;
    }
//    
    
    public static boolean comprobarCyC(ArrayList<Persona> usuarios, Scanner sc){
        System.out.println("\nPOR FAVOR, COMPROBEMOS SUS DATOS");
        System.out.println("Ingrese su correo electronico: ");
        String correoE = sc.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseñaE = sc.nextLine();
        for (Persona p: usuarios){
            if(p instanceof Vendedor && correoE.equals(p.correo) && Utilitaria.claveHash(contraseñaE).equals(p.clave)){
                System.out.println("Correo y clave válidos");
                System.out.println("");
                return true;
            }
        }
        System.out.println("Correo o clave incorrectos");
        System.out.println("");
        return false;
    }
    
    public Vehiculo buscarVehiculoPorPlaca(String placa, ArrayList<Vehiculo> vehiculos) {
    for (Vehiculo vehiculo : vehiculos) {
        if (vehiculo.getPlaca().equals(placa)) {
            return vehiculo;
        }
    }
    return null;
}
    public static void revisarOfertas(ArrayList<Persona> usuarios, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos, Scanner sc) {
        System.out.println("Ingrese la placa del vehículo deseado para consultar qué ofertas se han realizado: ");
        String placa = sc.nextLine();
        ArrayList<Vehiculo> copia = (ArrayList<Vehiculo>) vehiculos.clone();
        for (Vehiculo v : copia) {
            if (placa.equals(v.placa)) 
                v.mostrarOfertas(usuarios, ofertas, vehiculos, sc);
        }
    }

    public static void aceptarOferta(ArrayList<Persona> usuarios, ArrayList<Oferta> ofertas, ArrayList<Vehiculo> vehiculos, Oferta oferta) {
        Vehiculo vehiculo = oferta.getVehiculo();
        Comprador comprador = oferta.getComprador();
        Buzon b = new Buzon();
        b.enviarCorreo(oferta);
        ArrayList<Vehiculo> listaActualizadaVehiculos = Vehiculo.eliminarVehiculo(vehiculos, vehiculo);
        Vehiculo.saveFile(listaActualizadaVehiculos, "vehiculos.txt");
        ArrayList<Oferta> listaActualizadaOfertas = Oferta.eliminarOfertas(usuarios, ofertas, vehiculo);
        Oferta.saveFile(ofertas, "ofertas.txt");
    }
}
