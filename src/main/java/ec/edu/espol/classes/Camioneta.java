
package ec.edu.espol.classes;

public class Camioneta extends Auto {
    private String traccion;

    public Camioneta(String placa, String marca, String modelo, String tipoMotor, int año, double recorrido, String color,
                String tipoCombustible, double precio, int vidrios, String transmision, String traccion) {
        super(placa, marca, modelo, tipoMotor, año, recorrido, color, tipoCombustible, precio, vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    
    
}