
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

    public Vehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Vehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
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
}
