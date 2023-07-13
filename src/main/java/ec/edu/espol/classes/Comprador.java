/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.util.ArrayList;

/**
 *
 * @author crisj
 */
public class Comprador extends Persona{
    private ArrayList<Oferta> ofertas;

    public Comprador(String nombres, String apellidos, String organizacion, String correo, String clave, ArrayList<Oferta> ofertas) {
        super(nombres, apellidos, organizacion, correo, clave);
        this.ofertas = ofertas;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
