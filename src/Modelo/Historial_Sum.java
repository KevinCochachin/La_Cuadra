
package Modelo;

import Vista.*;

/**
 *
 * @author Kevin
 */
public class Historial_Sum {
    
    private int idHistorial, idSuministro,cantidadA,cantidadE;
    private String nombre,fecha;

    public Historial_Sum(int idHistorial, int idSuministro, int cantidadA,int cantidadE , String nombre, String fecha) {
        this.idHistorial = idHistorial;
        this.idSuministro = idSuministro;
        this.cantidadA = cantidadA;
        this.cantidadE=cantidadE;
        this.nombre = nombre;
        this.fecha = fecha;
    }
    
    public Historial_Sum(){
        
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public int getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(int idSuministro) {
        this.idSuministro = idSuministro;
    }

    public int getCantidadA() {
        return cantidadA;
    }

    public void setCantidadA(int cantidadA) {
        this.cantidadA = cantidadA;
    }

    public int getCantidadE() {
        return cantidadE;
    }

    public void setCantidadE(int cantidadE) {
        this.cantidadE = cantidadE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
