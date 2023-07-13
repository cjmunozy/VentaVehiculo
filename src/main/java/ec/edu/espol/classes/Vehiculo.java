/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

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
    }
}
