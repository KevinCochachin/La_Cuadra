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
    private Date fecha;
    
    public cliente (int ID,String Nombre,String Apellido, int dni, Date fecha){
        this.ID=ID;
        this.Nombre=Nombre;
        this.Apellido=Apellido;
        this.dni=dni;
        this.fecha=fecha;
        
    }
    public cliente(){     
    }
    
    //getter
    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public int getDni() {
        return dni;
    }

    public Date getFecha() {
        return fecha;
    }
    
    
    //setter
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
