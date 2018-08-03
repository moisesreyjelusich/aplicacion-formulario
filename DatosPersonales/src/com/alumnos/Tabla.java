/*
 * Esta clase es un JDialog que abre una ventana en la que aparece una tabla con los datos
 * cargados desde un fichero. Aparte tiene algunos botones para las diferentes funcionalidas
 * de la misma.
 */
package com.alumnos;

import com.alumnos.ListaAlumnos;
import formularios.Formulario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 * @author moises
 */

public class Tabla extends javax.swing.JDialog {
    //Atributos.
    private static String ruta = "ficheros/";  //Se crea la ruta hasta el directorio de ficheros.
    private static String ruta2 = "imagenes/";  //Se crea la ruta hasta el directorio de imágenes.
    private Dimension dim;
    private static String nomFichero;
    private static ListaAlumnos la = new ListaAlumnos();
    private MenuModificar md = new MenuModificar();
    private Formulario fr = new Formulario();
    
    //Constructor.
    public Tabla(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Tabla de alumnos matriculados");
        
        //Se crea el objeto la de la clase ListaAlumnos para poder llamar al método rellenarTablaVentana.
        la.rellenarTablaVentana();
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel_fondo_ventana, String.valueOf(ruta2 + "fondo.jpg"));
        dim = super.getToolkit().getScreenSize();   //Para poner el Jframe a pantalla completa en cualquier pantalla.
         
        //Para hacer transparente el JTable y el JScrollPanel.
        tabla2.setOpaque(false);
        tabla2.setBackground(new Color(0,0,0,0));
        tabla2.setForeground(Color.WHITE);
        tabla2.setFont(new Font("Tahoma", Font.BOLD, 12));
      
        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.getViewport().setOpaque(false);
        
        //Opciones de la ventana.
        setSize(1250,700);  //Se pone el mismo tamaño de ancho y alto que la foto.
        setResizable(false);    //Para no poder redimensionar la ventana.
        setLocationRelativeTo(this);    //Para centrar la ventana en cualquier pantalla.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jButton_cargar = new javax.swing.JButton();
        jButton_volver = new javax.swing.JButton();
        jButton_salir = new javax.swing.JButton();
        jButton_reiniciar = new javax.swing.JButton();
        jButton_buscar = new javax.swing.JButton();
        jButton_modificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton_borrar = new javax.swing.JButton();
        jButton_imprimir = new javax.swing.JButton();
        jLabel_fondo_ventana = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        tabla2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "DNI", "Telefono", "Direccion", "Email", "Edad", "Provincia", "Fecha Matriculacion", "Estado Civil", "Estudios"
            }
        ));
        tabla2.setOpaque(false);
        jScrollPane1.setViewportView(tabla2);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 60, 1170, 540);

        jButton_cargar.setText("CARGAR");
        jButton_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cargarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_cargar);
        jButton_cargar.setBounds(190, 620, 112, 36);

        jButton_volver.setText("VOLVER");
        jButton_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_volverActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_volver);
        jButton_volver.setBounds(1100, 620, 112, 36);

        jButton_salir.setText("SALIR");
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_salir);
        jButton_salir.setBounds(950, 620, 112, 36);

        jButton_reiniciar.setText("REINICIAR");
        jButton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_reiniciarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_reiniciar);
        jButton_reiniciar.setBounds(800, 620, 112, 36);

        jButton_buscar.setText("BUSCAR");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_buscar);
        jButton_buscar.setBounds(40, 620, 112, 36);

        jButton_modificar.setText("MODIFICAR");
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_modificar);
        jButton_modificar.setBounds(490, 620, 112, 36);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALUMNOS MATRICULADOS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(390, 10, 439, 36);

        jButton_borrar.setText("BORRAR");
        jButton_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_borrar);
        jButton_borrar.setBounds(340, 620, 112, 36);

        jButton_imprimir.setText("IMPRIMIR");
        jButton_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_imprimir);
        jButton_imprimir.setBounds(640, 620, 112, 36);
        getContentPane().add(jLabel_fondo_ventana);
        jLabel_fondo_ventana.setBounds(0, 0, 1250, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        System.exit(0);     //Para cerrar la aplicación.
    }//GEN-LAST:event_jButton_salirActionPerformed

    private void jButton_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_volverActionPerformed
        dispose();  //Para cerrar la ventana actual.
    }//GEN-LAST:event_jButton_volverActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        //Se crea el objeto del menú modificar y se instancia la clase.
        md.setResizable(false);
        md.setLocationRelativeTo(this);
        md.pack();
        md.setVisible(true);
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void jButton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reiniciarActionPerformed
        la.limpiarTablaVentana();   //Para limpiar las filas de la tabla por completo.
    }//GEN-LAST:event_jButton_reiniciarActionPerformed

    private void jButton_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cargarActionPerformed
        try {
            fr.cargarFichero();    //Se carga el fichero en el ArrayList.
            la.rellenarTablaVentana();  //Se rellena la tabla con los datos del ArrayList.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el fichero no ha podido cargarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_cargarActionPerformed

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        la.buscarAlumnos();  //Se busca si existe el alumno seleccionado.
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jButton_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrarActionPerformed
        try {         
            la.borrarAlumnos();  //Se borran los datos del ArrayList y se pregunat si se quiere guardar en el fichero.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el alumno no ha podido borrarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_borrarActionPerformed

    private void jButton_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirActionPerformed
        //Se crea el objeto JFileChooser y se instancia en la ruta actual.
        JFileChooser jfc = new JFileChooser(ruta);
        //Se le indica que sólo se puede seleccionar ficheros.
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Se crea el filtro de archivos con extensión *.txt
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
        //Se indica el filtro
        jfc.setFileFilter(filtro);
        //Se abre la ventana y se guarda la opcion seleccionada.
        int seleccion = jfc.showOpenDialog(this);

        //Si se pulsa en guardar.
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
            try {
                fr.imprimir();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error, no ha podido imprimirse el documento", "ERROR", 0);
            }
        }
    }//GEN-LAST:event_jButton_imprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton_borrar;
    public static javax.swing.JButton jButton_buscar;
    public static javax.swing.JButton jButton_cargar;
    public static javax.swing.JButton jButton_imprimir;
    public static javax.swing.JButton jButton_modificar;
    public static javax.swing.JButton jButton_reiniciar;
    public static javax.swing.JButton jButton_salir;
    public static javax.swing.JButton jButton_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_fondo_ventana;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla2;
    // End of variables declaration//GEN-END:variables
}
