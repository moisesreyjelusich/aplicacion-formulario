/**
 * Esta clase tiene todos los métodos relacionados con el ArrayList de alumnos y sus
 * diferentes funciones de agregar, borrar, buscar, modificar, limpiar tabla, rellenar las
 * dos tablas, junto con sus atributos y el constructor vacío.
 */
package com.alumnos;

import com.alumnos.Alumno;
import formularios.Formulario;
import static formularios.Formulario.*;
import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 * @author moises
 */
public class ListaAlumnos {

    //Atributos.
    public static ArrayList<Alumno> alumnos = new ArrayList<>();
    private Alumno aux;
    private DefaultTableModel modelo; //Se crea un modelo común para las dos tablas. 
    private TableColumnModel columnModel;

    //Constructor vacío.
    public ListaAlumnos() {
    }

    //Método agregarAlumnos.
    public void agregarAlumnos() throws IOException {
        String estudios = "", est = "", estadoCivil = "";
        int indice = 0, i;
        try {
            aux = new Alumno(); //Se instancia el objeto aux como alumno.

            //Se recorre todo el arrayList y se guarda la última posición del ArrayList.
            for (i = 0; i < alumnos.size(); i++) {
                indice = alumnos.get(i).getIdAlumno();
            }
            aux.setIdAlumno(indice + 1);  //Para que cada vez que se agregue un alumno se haga el
            //código autoincrementable, aunque se hayan borrado registros.

            //Se guarda el nombre.
            aux.setNombre(jTextField_nombre.getText()); //Se guarda el campo del JTextFieldNombre.

            // Patrón para validar el dni
            Pattern patronDni = Pattern.compile("\\d{8}[A-HJ-NP-TV-Z]");
            // El dni introducido a validar.
            String dni = jTextField_dni.getText();  //Se recoge el dni introducido en el JTextField.
            Matcher validarDni = patronDni.matcher(dni);
            //Se comprueba si se cumple el patrón de la expresión regular para validar el dni.
            if (validarDni.find() == true) {
                jTextField_dni.setBorder(null); //Para quitar el color del borde.
                aux.setDni(jTextField_dni.getText());
            } else {
                jTextField_dni.setBorder(new LineBorder(Color.RED));    //Se pone el borde en rojo en caso escribir algo erróneo.
                JOptionPane.showMessageDialog(null, "Error, introducir un dni valido", "ERROR", 0);
            }

            //Se guarda el telefono.
            aux.setTelefono(jTextField_telefono.getText());

            //Se guarda la dirección.
            aux.setDireccion(jTextField_direccion.getText());

            // Patrón para validar el email
            Pattern patronEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            // El email introducido a validar.
            String email = jTextField_email.getText();  //Se recoge el email introducido en el JTextField.
            Matcher validarEmail = patronEmail.matcher(email);
            //Se comprueba si se cumple el patrón de la expresión regular para validar el email.
            if (validarEmail.find() == true) {
                //Se guarda el email.
                jTextField_email.setBorder(null); //Para quitar el color del borde.
                aux.setEmail(email);
            } else {
                jTextField_email.setBorder(new LineBorder(Color.RED));  //Se pone el borde en rojo en caso escribir algo erróneo.
                JOptionPane.showMessageDialog(null, "Error, introducir un email valido", "ERROR", 0);
            }

            //Para comprobar que la edad sea mayor que 1 y menor a 120.
            int edad = (int)jSpinner_edad.getValue(); //Se convierte la cadena a entero.
            if (edad > 0 && edad < 120) {
                //Se guarda la edad.
                jSpinner_edad.setBorder(null); //Para quitar el color del borde.
                aux.setEdad((int) jSpinner_edad.getValue());
            } else {
                //jTextField_edad.setBorder(new LineBorder(Color.RED));   //Se pone el borde en rojo en caso escribir algo erróneo.
                throw new Exception();  //Se hace que salte la excepción.
            }

            //Se comprueba si en el JComboBox no está seleccionada la posición cero.
            aux.setProvincia((String) jComboBox_provincias.getSelectedItem()); //Se convierte a cadena el JComboBox.

            //Para formatear la fecha y guardarla en el ArrayList
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", new Locale("ES")); //Se elige el formato de la fecha.
            String fecha_formateada = sdf.format(jDateChooser_fecha.getCalendar().getTime());   //Se formatea la fecha recogida en el JDateChooser.
            aux.setFecha(fecha_formateada); //Se añade al ArrayList

            //Se comprueba el estado civil seleccionado.
            if (jRadioButton_soltero.isSelected()) {
                estadoCivil = "Solter@";
            }
            if (jRadioButton_casado.isSelected()) {
                estadoCivil = "Casad@";
            }
            
            aux.setEstadoCivil(estadoCivil);
            
            //Se comprueban los estudios marcados.
            if (jCheckBox_primaria.isSelected()) {
                est += jCheckBox_primaria.getText() + ", ";
            }
            if (jCheckBox_secundaria.isSelected()) {
                est += jCheckBox_secundaria.getText() + ", ";
            }
            if (jCheckBox_formacion.isSelected()) {
                est += jCheckBox_formacion.getText() + ", ";
            }
            if (jCheckBox_univer.isSelected()) {
                est += jCheckBox_univer.getText() + ", ";
            }
            estudios += est;    //Se concatena el valor de todos los estudios marcados.
            aux.setEstudios(estudios);
            //Se controlan las excepciones.
            if (jTextField_nombre.getText().isEmpty() || jTextField_dni.getText().isEmpty() || jTextField_telefono.getText().isEmpty()
                    || jTextField_direccion.getText().isEmpty() || jTextField_email.getText().isEmpty() 
                    || jComboBox_provincias.getSelectedIndex() == 0 || estadoCivil.equals("")) {
                throw new Exception();  //Se hace que salte la excepción.
            } else {
                aux.setEstadoCivil(estadoCivil);
                alumnos.add(aux);   //Se añaden todos los datos al ArrayList.
                //Método para añadir a la tabla.
                rellenarTabla();
                //Para quitar todos los bordes rojos de los campos.
                jTextField_nombre.setBorder(null); //Para quitar el color del borde.
                jTextField_dni.setBorder(null); //Para quitar el color del borde.
                jTextField_telefono.setBorder(null); //Para quitar el color del borde.
                jTextField_direccion.setBorder(null); //Para quitar el color del borde.
                jTextField_email.setBorder(null); //Para quitar el color del borde.
                jSpinner_edad.setBorder(null); //Para quitar el color del borde.
                jComboBox_provincias.setBorder(null); //Para quitar el color del borde.
                jDateChooser_fecha.setBorder(null); //Para quitar el color del borde.

                JOptionPane.showMessageDialog(null, "El alumno ha sido añadido con exito", "RESULTADO", 1);
            }
        } catch (Exception ex) {
            jTextField_nombre.setBorder(new LineBorder(Color.RED)); //Se pone el borde en rojo en caso escribir algo erróneo.
            jTextField_dni.setBorder(new LineBorder(Color.RED));    //Se pone el borde en rojo en caso escribir algo erróneo.
            jTextField_telefono.setBorder(new LineBorder(Color.RED));   //Se pone el borde en rojo en caso escribir algo erróneo.
            jTextField_direccion.setBorder(new LineBorder(Color.RED));  //Se pone el borde en rojo en caso escribir algo erróneo.
            jTextField_email.setBorder(new LineBorder(Color.RED));  //Se pone el borde en rojo en caso escribir algo erróneo.
            jSpinner_edad.setBorder(new LineBorder(Color.RED));   //Se pone el borde en rojo en caso escribir algo erróneo.
            jComboBox_provincias.setBorder(new LineBorder(Color.RED));  //Se pone el borde en rojo en caso escribir algo erróneo.
            jDateChooser_fecha.setBorder(new LineBorder(Color.RED));    //Se pone el borde en rojo en caso escribir algo erróneo.
            JOptionPane.showMessageDialog(null, "Error, introducir los datos correctamente y no dejar ningún campo vacio", "ERROR", 0);
        } finally {
            //Para guardar o no los cambios en el fichero antes de salir del menú.
            int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los datos en el fichero?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
            if (seleccion == JOptionPane.YES_OPTION) {
                Formulario fo = new Formulario();
                fo.guardarFicheroAlumnos(); //Para guardar en el fichero.
            }
        }
    }

    //Método para añadir a la tabla del menú princiapl.
    public void rellenarTabla() {
        modelo = (DefaultTableModel) Formulario.tabla.getModel();

        //Para definir el ancho de las columnas.
        columnModel = Formulario.tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(140);
        columnModel.getColumn(3).setPreferredWidth(140);
        columnModel.getColumn(4).setPreferredWidth(190);
        columnModel.getColumn(5).setPreferredWidth(200);
        columnModel.getColumn(6).setPreferredWidth(60);
        columnModel.getColumn(7).setPreferredWidth(140);
        columnModel.getColumn(8).setPreferredWidth(200);
        columnModel.getColumn(9).setPreferredWidth(130);
        columnModel.getColumn(10).setPreferredWidth(150);

        //Para limpiar las filas que ya hay en la tabla.
        int filas = modelo.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        //Para agregar las filas a la tabla.
        for (int i = 0; i < alumnos.size(); i++) {
            Object datos[] = new Object[11]; //Es un array de cadenas para las 11 columnas.
            datos[0] = alumnos.get(i).getIdAlumno();
            datos[1] = alumnos.get(i).getNombre();
            datos[2] = alumnos.get(i).getDni();
            datos[3] = alumnos.get(i).getTelefono();
            datos[4] = alumnos.get(i).getDireccion();
            datos[5] = alumnos.get(i).getEmail();
            datos[6] = alumnos.get(i).getEdad();
            datos[7] = alumnos.get(i).getProvincia();
            datos[8] = alumnos.get(i).getFecha();
            datos[9] = alumnos.get(i).getEstadoCivil();
            datos[10] = alumnos.get(i).getEstudios();
            modelo.addRow(datos);   //Se añaden los datos al modelo.
        }
    }

    //Método para añadir a la tabla del JDialog.
    public void rellenarTablaVentana() {
        //Se crea el modelo para la tabla.
        modelo = (DefaultTableModel) Tabla.tabla2.getModel();    //Se crea e instancia el modelo de la tabla.

        //Para definir el ancho de las columnas.
        columnModel = Tabla.tabla2.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(7).setPreferredWidth(120);
        columnModel.getColumn(8).setPreferredWidth(170);
        columnModel.getColumn(9).setPreferredWidth(120);
        columnModel.getColumn(10).setPreferredWidth(300);

        //Para limpiar las filas que ya hay en la tabla.
        int filas = modelo.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }

        //Para agregar las filas a la tabla.
        for (int i = 0; i < alumnos.size(); i++) {
            Object datos[] = new Object[11]; //Es un array de cadenas para las 11 columnas.
            datos[0] = alumnos.get(i).getIdAlumno();
            datos[1] = alumnos.get(i).getNombre();
            datos[2] = alumnos.get(i).getDni();
            datos[3] = alumnos.get(i).getTelefono();
            datos[4] = alumnos.get(i).getDireccion();
            datos[5] = alumnos.get(i).getEmail();
            datos[6] = alumnos.get(i).getEdad();
            datos[7] = alumnos.get(i).getProvincia();
            datos[8] = alumnos.get(i).getFecha();
            datos[9] = alumnos.get(i).getEstadoCivil();
            datos[10] = alumnos.get(i).getEstudios();
            modelo.addRow(datos);   //Se añaden los datos al modelo.
            //datos_Text_Area += datos[i]; //Para guardar los datos en una variable de tipo String para el fichero.
        }
    }

    //Método para limpiar las filas de la tabla por completo.
    public void limpiarTabla() {
        try {
            modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();  //Para recoger el total de filas.
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    //Método para limpiar las filas de la tabla de la ventana por completo.
    public void limpiarTablaVentana() {
        try {
            modelo = (DefaultTableModel) Tabla.tabla2.getModel();
            int filas = Tabla.tabla2.getRowCount();  //Para recoger el total de filas.
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de la ventana.");
        }
    }

    //Método modificar los campos de los alumnos.
    public void modificarAlumnos() throws IOException {
        int i, cont = 0, posicion = 0;
        String id = JOptionPane.showInputDialog(null, "Introduzca el id del alumno de entre los siguientes alumnos de la tabla:", "DATOS", 3);
        int identificador = Integer.parseInt(id);   //Para convertir la cadena a entero.
        for (i = 0; i < alumnos.size(); i++) {
            if (identificador == alumnos.get(i).getIdAlumno()) {
                posicion = i;   //Se guarda la posición del dni encontrado del ArrayList. 
                break;  //Para salir del bucle.
            } else {
                cont++; //Para controlar las veces que recorremos el arryList.
            }
        }
        //Para controlar que contador es igual al tamaño del ArrayList alumnos.
        if (cont == alumnos.size()) {
            JOptionPane.showMessageDialog(null, "El alumno no está en la tabla de datos", "ERROR", 0);
        }
        //Se recoge la opción seleccionada en el jComboBox.
        int opcion = MenuModificar.jComboBox_menu_modificar.getSelectedIndex();
        //Se crea el objeto md y se instancia para poder llamar al botón aceptar y a su acción del actionPerformed.
        MenuModificar md = new MenuModificar();
        MenuModificar.jButton_aceptar.getAction(); //Para recoger la acción del botón aceptar.

        switch (opcion) {
            case 1:
                String n = JOptionPane.showInputDialog(null, "Introduzca el nuevo nombre a modificar", "DATOS", 3);
                alumnos.get(posicion).setNombre(n);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el nombre por " + n, "RESULTADO", 1);
                break;
            case 2:
                String d = JOptionPane.showInputDialog(null, "Introduzca el nuevo dni a modificar", "DATOS", 3);
                alumnos.get(posicion).setDni(d);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el dni por " + d, "RESULTADO", 1);
                break;
            case 3:
                String t = JOptionPane.showInputDialog(null, "Introduzca el nuevo telefono a modificar", "DATOS", 3);
                alumnos.get(posicion).setTelefono(t);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el telefono por " + t, "RESULTADO", 1);
                break;
            case 4:
                String dir = JOptionPane.showInputDialog(null, "Introduzca la nueva direccion a modificar", "DATOS", 3);
                alumnos.get(posicion).setDireccion(dir);
                JOptionPane.showMessageDialog(null, "Se ha cambiado la direccion por " + dir, "RESULTADO", 1);
                break;
            case 5:
                String e = JOptionPane.showInputDialog(null, "Introduzca el nuevo email a modificar", "DATOS", 3);
                alumnos.get(posicion).setEmail(e);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el email por " + e, "RESULTADO", 1);
                break;
            case 6:
                String a = JOptionPane.showInputDialog(null, "Introduzca la nueva edad a modificar", "DATOS", 3);
                int anios = Integer.parseInt(a);
                alumnos.get(posicion).setEdad(anios);
                JOptionPane.showMessageDialog(null, "Se ha cambiado la edad por " + a, "RESULTADO", 1);
                break;
            case 7:
                String p = JOptionPane.showInputDialog(null, "Introduzca la nueva provincia a modificar", "DATOS", 3);
                alumnos.get(posicion).setProvincia(p);
                JOptionPane.showMessageDialog(null, "Se ha cambiado la provincia por " + p, "RESULTADO", 1);
                break;
            case 8:
                String f = JOptionPane.showInputDialog(null, "Introduzca la nueva fecha de matriculacion a modificar", "DATOS", 3);
                alumnos.get(posicion).setFecha(f);
                JOptionPane.showMessageDialog(null, "Se ha cambiado la fecha de matriculacion por " + f, "RESULTADO", 1);
                break;
            case 9:
                String estCiv = JOptionPane.showInputDialog(null, "Introduzca el nuevo estado civil a modificar", "DATOS", 3);
                alumnos.get(posicion).setEstadoCivil(estCiv);
                JOptionPane.showMessageDialog(null, "Se ha cambiado el estado civil por " + estCiv, "RESULTADO", 1);
                break;
            case 10:
                String est = JOptionPane.showInputDialog(null, "Introduzca los nuevos estudios a modificar", "DATOS", 3);
                alumnos.get(posicion).setEstudios(est);
                JOptionPane.showMessageDialog(null, "Se han cambiado los estudios por " + est, "RESULTADO", 1);
                break;
        }

        limpiarTabla();  //Se limpia la tabla después de realizar el cambio.
        rellenarTabla();    //Para rellenar la tabla otra vez.

        //Para guardar o no los cambios en el fichero antes de salir del menú.
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los datos en el fichero?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (seleccion == JOptionPane.YES_OPTION) {
            Formulario fo = new Formulario();
            fo.guardarFicheroAlumnos(); //Para guardar en el fichero.
        }
    }

    //Método modificar los campos de los alumnos.
    public void borrarAlumnos() throws IOException {
        int i, cont = 0;
        String id = JOptionPane.showInputDialog(null, "Introduzca el id del alumno de entre los siguientes alumnos de la tabla:", "DATOS", 3);
        int identificador = Integer.parseInt(id);   //Para convertir la cadena a entero.
        for (i = 0; i < alumnos.size(); i++) {
            if (identificador == alumnos.get(i).getIdAlumno()) {
                JOptionPane.showMessageDialog(null, "El alumno " + alumnos.get(i).getNombre() + " con id: " + identificador + " se ha borrado del fichero de la base de datos", "RESULTADO", 1);
                alumnos.remove(i);  //Para borrar el alumno seleccionado.
                break;  //Para salir del bucle.
            } else {
                cont++;
            }
        }

        limpiarTabla();  //Se limpia la tabla después de realizar el cambio.
        rellenarTabla();    //Para rellenar la tabla otra vez.

        //Para controlar que contador es igual al tamaño del ArrayList alumnos.
        if (cont == alumnos.size()) {
            JOptionPane.showMessageDialog(null, "El alumno no está en la tabla de datos", "ERROR", 0);
        }

        //Para guardar o no los cambios en el fichero antes de salir del menú.
        int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres actualizar los datos en el fichero?", "DATOS", JOptionPane.YES_NO_CANCEL_OPTION, 3);
        if (seleccion == JOptionPane.YES_OPTION) {
            Formulario fo = new Formulario();
            fo.guardarFicheroAlumnos();
        }
    }

    //Metodo mostrar el archivo de alumnos completo con los encabezados.
    public void mostrarAlumnos() {
        //Para mostrar los alumnos.
        int i;
        for (i = 0; i < alumnos.size(); i++) {
            jTextArea.append(String.valueOf(alumnos.get(i)));  //Se añaden los datos al JTextArea.
            System.out.println(alumnos.get(i));  //Se muestra por consola también.
            JOptionPane.showMessageDialog(null, alumnos.get(i), "ALUMNO " + (i + 1), 1); //Así llama al método toString por defecto.
        }
    }

    //Método buscar alumnos.
    public void buscarAlumnos() {
        int i, cont = 0;
        String id = JOptionPane.showInputDialog(null, "Introduzca el id del alumno a buscar", "DATOS", 3);
        int identificador = Integer.parseInt(id);   //Para convertir la cadena a entero.
        for (i = 0; i < alumnos.size(); i++) {
            //Se comprueba si existe el dni en el ArrayList alumnos.
            if (identificador == alumnos.get(i).getIdAlumno()) {
                JOptionPane.showMessageDialog(null, "El alumno " + alumnos.get(i).getNombre() + " con id: " + identificador + " y con el DNI: " + alumnos.get(i).getDni() + " se encuentra en la posicion " + (i + 1) + " del fichero de la base de datos", "RESULTADO", 1);
                break;  //Para salir del bucle.
            } else {
                cont++; //Se incrementa el contador cada vez que no se cumpla la condición.
            }
        }
        //Para controlar que contador es igual al tamaño del ArrayList alumnos.
        if (cont == alumnos.size()) {
            JOptionPane.showMessageDialog(null, "Error, el alumno no se encuentra en el fichero", "RESULTADO", 0);
        }
    }
}
