package com.ulloalibros.vista;

import com.ulloalibros.modelo.Libro;
import com.ulloalibros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {

    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JTextField libroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciasTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio) {
        this.libroServicio = libroServicio;
        iniciarForma();

        agregarButton.addActionListener(e -> agregarLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarLibroSeleccionado();
            }
        });
        modificarButton.addActionListener(e -> modificarLibro());
        eliminarButton.addActionListener(e -> eliminarLibro());
    }

    private void iniciarForma() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth() / 2);
        int y =(tamanioPantalla.height - getHeight() / 2);
        setLocation(x,y);


    }

    private void agregarLibro() {
        //Leer los valores del formulario

        if(libroTexto.getText().equals("")) {
            motrarMensaje("Proporciona el nombre del libro");
            libroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());

        // Crear el objeto libro

        var libro = new Libro(null, nombreLibro,autor,precio,existencias);
//        libro.setNombreLibro(nombreLibro);
//        libro.setAutor(autor);
//        libro.setPrecio(precio);
//        libro.setExistencia(existencias);

        this.libroServicio.guardarLibro(libro);
        motrarMensaje("Se agrego el libro");

        limpiarFormulario();

        //Recargar la tabla
        listarLibros();

    }

    private void modificarLibro(){
        //Reutilizamos parte del codigo del metodo agregar

        if(libroTexto.getText().equals("")) {
            motrarMensaje("Por favor complete los datos");
        }else {
            if (libroTexto.getText().equals("")) {
                motrarMensaje("Debe proporcionar el nombre de un libro");
                libroTexto.requestFocusInWindow();
                return;

            }
        }
        //Se agregar el idLibro
        int idLibro = Integer.parseInt(idTexto.getText());
        var nombreLibro = libroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencias = Integer.parseInt(existenciasTexto.getText());

        var libro = new Libro(idLibro, nombreLibro,autor,precio,existencias);

        this.libroServicio.guardarLibro(libro);
        motrarMensaje("Datos modificados");

        limpiarFormulario();

        //Recargar la tabla
        listarLibros();

    }


    private void cargarLibroSeleccionado() {
        //Los indices de las columnas de nuestra tabla inician en cero

        var renglon = tablaLibros.getSelectedRow();
        if (renglon != - 1 ) {
            //Regresa -1 si no se selecciono ningun registro
            String idLibro =
                    tablaLibros.getModel().getValueAt(renglon, 0).toString();

            //Llenar elementos del formulario
            idTexto.setText(idLibro);

            //Recupera el nombre del libro
            String nombreLibro = tablaLibros.getModel().getValueAt(renglon, 1).toString();
            libroTexto.setText(nombreLibro);

            //Recupera el autor
            String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
            autorTexto.setText(autor);

            //Recupera el precio
            String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
            precioTexto.setText(precio);

            //Recupera la existencia
            String existencia = tablaLibros.getModel().getValueAt(renglon, 4).toString();
            existenciasTexto.setText(existencia);
        }
    }

    private void limpiarFormulario() {
        libroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciasTexto.setText("");
    }

    private void eliminarLibro() {
        var renglon = tablaLibros.getSelectedRow();
        if (renglon != - 1 ) {
            //Regresa -1 si no se selecciono ningun registro
            String idLibro =
                    tablaLibros.getModel().getValueAt(renglon, 0).toString();

            var libro = new Libro();
            libro.setIdLibro(Integer.parseInt(idTexto.getText()));
            libroServicio.eliminarLibro(libro);
            motrarMensaje("Libro " + idLibro + " ha sido eliminado");
            limpiarFormulario();

            //Recargar la tabla
            listarLibros();
        }else {
            motrarMensaje("No se ha seleccionado ningun libro a eliminar");
        }
        //Reutilizamos parte del codigo del metodo agregar

//        if(libroTexto.getText().equals("")) {
//            motrarMensaje("Por favor complete los datos");
//        }else {
//            if (libroTexto.getText().equals("")) {
//                motrarMensaje("Debe proporcionar el nombre de un libro");
//                libroTexto.requestFocusInWindow();
//                return;
//
//            }
//        }
//        //Se agregar el idLibro
//        int idLibro = Integer.parseInt(idTexto.getText());
//        var nombreLibro = libroTexto.getText();
//        var autor = autorTexto.getText();
//        var precio = Double.parseDouble(precioTexto.getText());
//        var existencias = Integer.parseInt(existenciasTexto.getText());
//
//        var libro = new Libro(idLibro, nombreLibro,autor,precio,existencias);
//
//        this.libroServicio.eliminarLibro(libro);
//
//        motrarMensaje("Libro Eliminado");
//
//        limpiarFormulario();
//
//        //Recargar la tabla
//        listarLibros();

    }

    private void motrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        // Crear el elemento idTexto oculto
        idTexto = new JTextField("");
        idTexto.setVisible(false);

        this.tablaModeloLibros = new DefaultTableModel(0,5);
        String[] cabeceros = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        tablaModeloLibros.setColumnIdentifiers(cabeceros);

        //Instanciar el objeto JTable
        this.tablaLibros = new JTable(tablaModeloLibros);

        listarLibros();
    }

    private void listarLibros() {
        //Limpiar tabla
        tablaModeloLibros.setRowCount(0);

        //Obtener todos los libros de la base de datos
        var libros = libroServicio.listarLibros();

        libros.forEach((libro) -> {
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNombreLibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencia()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        });
    }
}

