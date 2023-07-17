/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author crisj
 */
public abstract class Persona {
    protected String nombres;
    protected String apellidos;
    protected String organizacion;
    protected String correo;
    protected String clave;
    protected TipoPersona tipoPersona; 

    public Persona(String nombres, String apellidos, String organizacion, String correo, String clave, TipoPersona tipoPersona) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.tipoPersona = tipoPersona;
    }

    public Persona(String nombres, String correo) {
        this.nombres = nombres;
        this.correo = correo;
    }
    
    
//    public abstract boolean validarCorreo(String correo);

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
           pw.println(this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo+"|"+this.clave+"|"+this.tipoPersona); 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Persona other = (Persona) o;
        return Objects.equals(this.correo, other.correo);
    }
    
    public static ArrayList<Persona> cargarUsuarios(String nomFile){
        ArrayList<Persona> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine()){
                Persona p;
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                if(tokens[5].equals("VENDEDOR"))
                    p = new Vendedor(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                else
                    p = new Comprador(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
                usuarios.add(p);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return usuarios;
    }
}