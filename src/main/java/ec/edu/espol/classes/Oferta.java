
package ec.edu.espol.classes;


public class Oferta {
    private Vehiculo tipoVehiculo;
    private Comprador comprador;
    private double precio;

    public Oferta(Vehiculo tipoVehiculo, Comprador comprador, double precio) {
        this.tipoVehiculo = tipoVehiculo;
        this.comprador = comprador;
        this.precio = precio;
    }
    
}
