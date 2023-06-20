/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Adm_ListaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniel
 */
public class Controlador_RegistroProducto implements ActionListener{
    
    Adm_ListaProducto Adm_rp;
    
    public Controlador_RegistroProducto(Adm_ListaProducto Adm_rp){
        this.Adm_rp=Adm_rp;
        this.Adm_rp.btnagregar1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Adm_rp.btnagregar1){
            limpiarentradas();
            
        }
        
    }

    private void limpiarentradas() {
        Adm_rp.txtID.setText("");
        Adm_rp.txtProducto.setText("");
        Adm_rp.txtPrecio.setText("");
        Adm_rp.txtTiempo.setText("");
        Adm_rp.Combobox.setSelectedItem("");
    }

   

    
    
}
