/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class cliente {
    private int ID;
    private String Nombre;
    private String Apellido;
    private int dni;
    private int telefono;
    private String fecha;

    public cliente(int ID, String Nombre, String Apellido, int dni, int telefono, String fecha) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.fecha=fecha;
    }

    public cliente() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
 
    
}
