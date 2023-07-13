
package ec.edu.espol.classes;

import java.util.ArrayList;


public class Vendedor extends Persona{
    private ArrayList<Vehiculo> vehiculos;

    public Vendedor(ArrayList<Vehiculo> vehiculos, String nombres, String apellidos, String organizacion, String correo, String clave) {
        super(nombres, apellidos, organizacion, correo, clave);
        this.vehiculos = vehiculos;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
