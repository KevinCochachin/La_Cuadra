
package Modelo;

import java.sql.Date;

public class Fecharecepcion {
    int idfecha;
    int idcliente;
    Date fechallegada;
    Date fechareserva;

    public Fecharecepcion(int idfecha, int idcliente, Date fechallegada, Date fechareserva) {
        this.idfecha = idfecha;
        this.idcliente = idcliente;
        this.fechallegada = fechallegada;
        this.fechareserva = fechareserva;
    }

    public Fecharecepcion() {
    }

    public int getIdfecha() {
        return idfecha;
    }

    public void setIdfecha(int idfecha) {
        this.idfecha = idfecha;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(Date fechallegada) {
        this.fechallegada = fechallegada;
    }

    public Date getFechareserva() {
        return fechareserva;
    }

    public void setFechareserva(Date fechareserva) {
        this.fechareserva = fechareserva;
    }
    
    
}
