package Modelo;

import Vista.frmBarberia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.ClaseConexion;


public class tbBarbero {

    public String getUUID_Barbero() {
        return UUID_Barbero;
    }

    public void setUUID_Barbero(String UUID_Barbero) {
        this.UUID_Barbero = UUID_Barbero;
    }

    public String getNombre_Barbero() {
        return Nombre_Barbero;
    }

    public void setNombre_Barbero(String Nombre_Barbero) {
        this.Nombre_Barbero = Nombre_Barbero;
    }

    public int getEdad_Barbero() {
        return Edad_Barbero;
    }

    public void setEdad_Barbero(int Edad_Barbero) {
        this.Edad_Barbero = Edad_Barbero;
    }

    public int getPeso_Barbero() {
        return Peso_Barbero;
    }

    public void setPeso_Barbero(int Peso_Barbero) {
        this.Peso_Barbero = Peso_Barbero;
    }

    public String getCorreo_Barbero() {
        return Correo_Barbero;
    }

    public void setCorreo_Barbero(String Correo_Barbero) {
        this.Correo_Barbero = Correo_Barbero;
    }
    
    private String UUID_Barbero;
    private String Nombre_Barbero;
    private int Edad_Barbero;
    private int Peso_Barbero;
    private String Correo_Barbero;
    
    ////////////////////////3- Métodos 
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbBarbero (UUID_Barbero, Nombre_Barbero, Edad_Barbero, Peso_Barbero, Correo_Barbero) VALUES (?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_Barbero());
            pstmt.setInt(3, getEdad_Barbero());
            pstmt.setInt(4, getPeso_Barbero());
            pstmt.setString(5, getCorreo_Barbero());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Barbero", "Nombre_Barbero", "Edad_Barbero", "Peso_Barbero", "Correo_Barbero"});
        try {
            //Consulta a ejecutar
            String query = "SELECT * FROM tbBarbero";
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery(query);
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Barbero"), 
                    rs.getString("Nombre_Barbero"), 
                    rs.getInt("Edad_Barbero"), 
                    rs.getInt("Peso_Barbero"),
                    rs.getString("Correo_Barbero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbBarbero where UUID_Barbero = ?";
            PreparedStatement deleteBarbero = conexion.prepareStatement(sql);
            deleteBarbero.setString(1, miId);
            deleteBarbero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }

    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbBarbero set Nombre_Barbero= ?, Edad_Barbero = ?, Peso_Barbero = ?, Correo_Barbero = ? where UUID_Barbero = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getNombre_Barbero());
                updateUser.setInt(2, getEdad_Barbero());
                updateUser.setInt(3, getPeso_Barbero());
                updateUser.setString(4, getCorreo_Barbero());
                updateUser.setString(5, UUID);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

    public void Buscar(JTable tabla, JTextField miTextField) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Barbero", "Nombre_Barbero", "Edad_Barbero", "Peso_Barbero", "Correo_Barbero"});
        try {
            String sql = "SELECT * FROM tbBarbero WHERE nombre LIKE ? || '%'";
            PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
            deleteEstudiante.setString(1, miTextField.getText());
            ResultSet rs = deleteEstudiante.executeQuery();

            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_Barbero"), rs.getString("Nombre_Barbero"), rs.getInt("Edad_Barbero"), rs.getInt("Peso_Barbero"), rs.getString("Correo_Barbero")});
            }

            
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }

    public void limpiar(frmBarberia vista) {
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }

    public void CargarDatosTabla(frmBarberia vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jTBbarberia.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_BarberoDeTB = vista.jTBbarberia.getValueAt(filaSeleccionada, 0).toString();
            String Nombre_BarberoDeTB = vista.jTBbarberia.getValueAt(filaSeleccionada, 1).toString();
            String Edad_BarberoDeTB = vista.jTBbarberia.getValueAt(filaSeleccionada, 2).toString();
            String Peso_BarberoDeTB = vista.jTBbarberia.getValueAt(filaSeleccionada, 3).toString();
            String Correo_BarberoDeTB = vista.jTBbarberia.getValueAt(filaSeleccionada, 4).toString();
            

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(Nombre_BarberoDeTB);
            vista.txtEdad.setText(Edad_BarberoDeTB);
            vista.txtPeso.setText(Peso_BarberoDeTB);
            vista.txtCorreo.setText(Correo_BarberoDeTB);
        }
    }

}



