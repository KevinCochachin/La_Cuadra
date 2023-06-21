/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Bryan
 */
public class registro_Producto {
    int ID;
    String Nombre;
    double tiempo;
    double precio;
    
    

    public registro_Producto(int ID, String Nombre, double tiempo, double precio) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.tiempo = tiempo;
        this.precio = precio;
        
        this.tiempo = tiempo;
    }

    public registro_Producto() {
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

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

   
}
    

