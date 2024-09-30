package Controlador;

import Modelo.tbBarbero;
import Vista.frmBarberia;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlBarberia implements MouseListener, KeyListener{
    
    frmBarberia vista;
    tbBarbero modelo;
    
    //lo amo ingeniero
    public ctrlBarberia (frmBarberia vista, tbBarbero modelo){
        
        this.vista = vista;
        this.modelo = modelo;
        
        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.jTBbarberia.addMouseListener(this);
        
        modelo.Mostrar(vista.jTBbarberia);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource() == vista.btnGuardar){
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
            JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    
                    modelo.setNombre_Barbero(vista.txtNombre.getText());
                    modelo.setEdad_Barbero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Barbero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreo_Barbero(vista.txtCorreo.getText());
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.jTBbarberia);
                    modelo.limpiar(vista);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
                    
            }             
        }
        
        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.jTBbarberia);
                modelo.Mostrar(vista.jTBbarberia);
                modelo.limpiar(vista);
            }
        }
        
        
        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setNombre_Barbero(vista.txtNombre.getText());
                    modelo.setEdad_Barbero(Integer.parseInt(vista.txtEdad.getText()));
                    modelo.setPeso_Barbero(Integer.parseInt(vista.txtPeso.getText()));
                    modelo.setCorreo_Barbero(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jTBbarberia);
                    modelo.Mostrar(vista.jTBbarberia);
                    modelo.limpiar(vista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vista.btnLimpiar){
            modelo.limpiar(vista);
        }
        
        if (e.getSource() == vista.jTBbarberia){
            modelo.CargarDatosTabla(vista);
        }   
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
