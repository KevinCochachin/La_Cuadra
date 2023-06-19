/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Adm_RegistroProducts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Daniel
 */
public class Controlador_RegistroProducto implements ActionListener{
    
    Adm_RegistroProducts Adm_rp;
    
    public Controlador_RegistroProducto(Adm_RegistroProducts Adm_rp){
        this.Adm_rp=Adm_rp;
        this.Adm_rp.btnagregar2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Adm_rp){
            System.out.println("OK");
        }
        
    }

   

    
    
}
