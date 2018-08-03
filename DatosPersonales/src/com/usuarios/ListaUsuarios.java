/*
 * Esta clase tiene el arrayList de Usuarios y sus métodos.
 */
package com.usuarios;

import formularios.Formulario;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 * @author moises
 */
public class ListaUsuarios {
    //Atributos.
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static Usuario aux;
    public static String nombre, clave;
    
    //Constructor vacío.
    public ListaUsuarios() {
    }

    //Método para agregar usuarios.
    public void agregarUsuario() throws IOException {
        int i, indice = 0;
        aux = new Usuario();
        nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del nuevo usuario", "DATOS", 3);
        clave = JOptionPane.showInputDialog(null, "Introduce la clave del nuevo usuario", "DATOS", 3);
        //Se recorre todo el arrayList y se guarda la última posición del ArrayList.
        for (i = 0; i < usuarios.size(); i++) {
            indice = usuarios.get(i).getIdUsuario();
        }
        //Se añaden los campos al arrayList.
        aux.setIdUsuario(indice + 1);  //Para que cada vez que se agregue un usuario se haga el
        //código autoincrementable, aunque se hayan borrado registros.
        aux.setNombre(nombre);
        aux.setClave(clave);
        
        //Se añade el aux al arrayList.
        usuarios.add(aux);   
        
        JOptionPane.showMessageDialog(null, "El usuario ha sido añadido con exito", "RESULTADO", 1);
        //Para guardar o no los cambios en el fichero antes de salir del menú.
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los usuarios en un fichero?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (seleccion == JOptionPane.YES_OPTION) {
            Formulario fo = new Formulario();
            fo.guardarFicheroUsuarios();
        }
    }
    
    //Método modificar los campos de los usuarios.
    public void modificarUsuarios() throws IOException {
        int i, cont = 0, posicion = 0;
        //Se muestran los datos del fichero de usuarios para poder modificar algún campo.
        String texto = String.valueOf(usuarios.toString());
        JOptionPane.showMessageDialog(null, "El fichero contiene los siguientes usuarios: \n" + texto,"DATOS",1);
        String id = JOptionPane.showInputDialog(null, "Introduzca el id del usuario de entre los siguientes:", "DATOS", 3);
        int identificador = Integer.parseInt(id);   //Para convertir la cadena a entero.
        for (i = 0; i < usuarios.size(); i++) {
            if (identificador == usuarios.get(i).getIdUsuario()) {
                posicion = i;   //Se guarda la posición del dni encontrado del ArrayList. 
                break;  //Para salir del bucle.
            } else {
                cont++; //Para controlar las veces que recorremos el arryList.
            }
        }
        //Para controlar que contador es igual al tamaño del ArrayList alumnos.
        if (cont == usuarios.size()) {
            JOptionPane.showMessageDialog(null, "El usuario no se encuentra en el fichero", "ERROR", 0);
        }
        //Se recoge la opción seleccionada en el jComboBox.
        int opcion = MenuModificarUsuario.jComboBox_menu_modificar_usuarios.getSelectedIndex();
        //Se llama a la acción del botón aceptar por medio de la clase.
        MenuModificarUsuario.jButton_aceptar_usuarios.getAction(); //Para recoger la acción del botón aceptar.

        switch (opcion) {
            case 1:
                String n = JOptionPane.showInputDialog(null, "Introduzca el nuevo nombre a modificar", "DATOS", 3);
                usuarios.get(posicion).setNombre(n);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el nombre por " + n, "RESULTADO", 1);
                break;
            case 2:
                String c = JOptionPane.showInputDialog(null, "Introduzca la nueva clave a modificar", "DATOS", 3);
                usuarios.get(posicion).setClave(c);
                JOptionPane.showMessageDialog(null, "Se ha cambiado la clave por " + c, "RESULTADO", 1);
                break;
        }

        //Para guardar o no los cambios en el fichero antes de salir del menú.
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los datos en el fichero de usuarios?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (seleccion == JOptionPane.YES_OPTION) {
            Formulario fo = new Formulario();
            fo.guardarFicheroUsuarios();//Para guardar en el fichero.
        }
    }
    
    //Método modificar los campos de los alumnos.
    public void borrarUsuarios() throws IOException {
        int i, cont = 0;
        //Se muestran los datos del fichero de usuarios para poder borrar algún usuario.
        String texto = String.valueOf(usuarios.toString());
        JOptionPane.showMessageDialog(null, "El fichero contiene los siguientes usuarios: \n" + texto,"DATOS",1);
        String id = JOptionPane.showInputDialog(null, "Introduzca el id del usuario de entre los siguientes:", "DATOS", 3);
        int identificador = Integer.parseInt(id);   //Para convertir la cadena a entero.
        for (i = 0; i < usuarios.size(); i++) {
            if (identificador == usuarios.get(i).getIdUsuario()) {
                JOptionPane.showMessageDialog(null, "El alumno " + usuarios.get(i).getNombre() + " con id: " + identificador + " se ha borrado del fichero de la base de datos", "RESULTADO", 1);
                usuarios.remove(i);  //Para borrar el alumno seleccionado.
                break;  //Para salir del bucle.
            } else {
                cont++;
            }
        }

        //Para controlar que contador es igual al tamaño del ArrayList usuarios.
        if (cont == usuarios.size()) {
            JOptionPane.showMessageDialog(null, "El usuario no existe en el fichero", "ERROR", 0);
        }

        //Para guardar o no los cambios en el fichero antes de salir del menú.
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los datos en el fichero de usuarios?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (seleccion == JOptionPane.YES_OPTION) {
            Formulario fo = new Formulario();
            fo.guardarFicheroUsuarios();
        }
    }
}
