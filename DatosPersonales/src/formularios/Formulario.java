/**
 * Esta es clase dónde está el JFrame principal, osea de dónde derivan el resto de clase.
 * En esta clase están todos los campos a cubrir, los botones, los menús, junto con una tabla y
 * un textarea dónde mostrar los resultados.
 */
package formularios;

import com.alumnos.MenuModificar;
import com.usuarios.MenuModificarUsuario;
import com.usuarios.Usuario;
import com.usuarios.ListaUsuarios;
import com.alumnos.Tabla;
import com.alumnos.ListaAlumnos;
import com.alumnos.Alumno;
import com.toedter.calendar.JCalendar;
import static com.alumnos.ListaAlumnos.*;
import static com.usuarios.ListaUsuarios.usuarios;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 *
 * @author moises
 */
public class Formulario extends javax.swing.JFrame {

    //Atributos
    public static File archivo, imagenes, archivoUsuarios;
    private static File ayuda = new File("ficheros/leeme.txt");
    private static String ruta = "ficheros/";  //Se crea la ruta hasta el directorio de ficheros.
    private static String ruta2 = "imagenes/";  //Se crea la ruta hasta el directorio de imágenes.
    private static String nomFichero;
    public static Boolean bandera = true;
    private static ListaAlumnos lista = new ListaAlumnos();
    private static ListaUsuarios lista2 = new ListaUsuarios();
    private Dimension dim;  //Para crear la dimensión para la pantalla.
    private JPopupMenu popup = new JPopupMenu();    //Para crear un PopupMenu.

    //Constructor vacío.
    public Formulario() {
        initComponents();
        setTitle("Programa de inscripción de alumnos");

        jDateChooser_fecha.setDate(new JCalendar().getDate()); //Para poner el valor de la fecha a la fecha actual por defecto.
        tabla.setEnabled(false);    //Para hacer la tabla no editable.

        //Para hacer transparente el JTextArea y el JScrollPanel.
        jTextArea.setOpaque(false);
        jTextArea.setBackground(new Color(0, 0, 0, 0));
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setFont(new Font("Tahoma", Font.BOLD, 12));
        jScrollPane1.setBackground(new Color(0, 0, 0, 0));
        jScrollPane1.getViewport().setOpaque(false);

        //Para hacer transparente el JTextArea y el JScrollPanel.
        jLabel_foto.setOpaque(false);
        jLabel_foto.setBackground(new Color(0, 0, 0, 0));
        jLabel_foto.setForeground(Color.WHITE);
        jScrollPane2.setBackground(new Color(0, 0, 0, 0));
        jScrollPane2.getViewport().setOpaque(false);

        //Para hacer transparente el JTable y el JScrollPanel.
        tabla.setOpaque(false);
        tabla.setBackground(new Color(0, 0, 0, 0));
        tabla.setForeground(Color.WHITE);
        tabla.setFont(new Font("Tahoma", Font.BOLD, 12));
        jScrollPane2.setBackground(new Color(0, 0, 0, 0));
        jScrollPane2.getViewport().setOpaque(false);

        //Para hacer un PopupMenu.
        jMenuItem_abrir_menu = new JMenuItem("Abrir Fichero");
        jMenuItem_guardar_menu = new JMenuItem("Guardar Fichero");
        jMenuItem_modificar_menu = new JMenuItem("Modificar Alumnos");
        jMenuItem_agregar_alumnos = new JMenuItem("Agregar Alumnos");
        jMenuItem_borrar_menu = new JMenuItem("Borrar Fichero");
        jMenuItem_salir_menu = new JMenuItem("Salir Aplicacion");
        
        //Se añaden los jMenuItem al popup.
        popup.add(jMenuItem_abrir_menu);
        popup.add(jMenuItem_guardar_menu);
        popup.add(jMenuItem_modificar_menu);
        popup.add(jMenuItem_agregar_alumnos);
        popup.add(jMenuItem_borrar_menu);
        popup.add(jMenuItem_salir_menu);
        jLabel_fondo.setComponentPopupMenu(popup);  //Se añade el PopupMenu al la imagen de fondo.

        //Para abrir el fichero desde el PopupMenu.
        jMenuItem_abrir_menu.addActionListener((ActionEvent e) -> {
            Formulario fr = new Formulario();
            try {
                fr.abrirFichero();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el fichero", "ERROR", 0);
            }
        });
        //Para guardar el fichero desde el PopupMenu.
        jMenuItem_guardar_menu.addActionListener((ActionEvent e) -> {
            Formulario fr = new Formulario();
            try {
                fr.guardarFicheroAlumnos();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el fichero", "ERROR", 0);
            }
        });
        //Para modificar el fichero desde el PopupMenu.
        jMenuItem_modificar_menu.addActionListener((ActionEvent e) -> {
            //Se crea el objeto del menú modificar y se instancia la clase.
            MenuModificar md = new MenuModificar();
            md.setResizable(false);
            md.setLocationRelativeTo(null);
            md.pack();
            md.setVisible(true);
        });
        //Para agregar un alumno al arrayList y al fichero desde el PopupMenu.
        jMenuItem_agregar_alumnos.addActionListener((ActionEvent e) -> {
            try {
                lista.agregarAlumnos(); //Para agregar datos al ArrayList de alumnos.
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error, no ha podido agregarse alumnos al fichero", "ERROR", 0);
            }
        });
        //Para borrar el fichero desde el PopupMenu.
        jMenuItem_borrar_menu.addActionListener((ActionEvent e) -> {
            Formulario fr = new Formulario();
            fr.borrarFicheroAlumnos(); //Para borrar el fichero por completo.
        });
        //Para cerrar la aplicación.
        jMenuItem_salir_menu.addActionListener((ActionEvent e) -> {
            System.exit(0);     //Para cerrar la aplicación.
        });

        //Opciones ventana.
        //setExtendedState(MAXIMIZED_BOTH);   //Para que se ponga el Jframe maximizado.
        //Con esta librería se escala una imagen automáticamente al contenedor que la contenga.
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel_fondo, String.valueOf(ruta2 + "imagen.jpg"));

        dim = super.getToolkit().getScreenSize();   //Para poner el Jframe a pantalla completa en cualquier pantalla.
        jLabel_fondo.setSize(dim);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Para poder cerrar la ventana por defecto.
        setSize(dim);   //Se le da el tamaño.
        setLocationRelativeTo(this);    //Para centrar la ventana.
        setResizable(false);    //Para que no se pueda redimensionar la ventana.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_grupo = new javax.swing.ButtonGroup();
        jLabel_titulo_principal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel_edad = new javax.swing.JLabel();
        jLabel_dni = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_email = new javax.swing.JLabel();
        jLabel_provincia = new javax.swing.JLabel();
        jComboBox_provincias = new javax.swing.JComboBox<>();
        jTextField_telefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser_fecha = new com.toedter.calendar.JDateChooser();
        jButton_agregar_foto = new javax.swing.JButton();
        jTextField_email = new javax.swing.JTextField();
        jLabel_nombre1 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jTextField_dni = new javax.swing.JTextField();
        jLabel_direccion = new javax.swing.JLabel();
        jTextField_direccion = new javax.swing.JTextField();
        jSpinner_edad = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton_soltero = new javax.swing.JRadioButton();
        jRadioButton_casado = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox_primaria = new javax.swing.JCheckBox();
        jCheckBox_secundaria = new javax.swing.JCheckBox();
        jCheckBox_formacion = new javax.swing.JCheckBox();
        jCheckBox_univer = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jButton_modificar = new javax.swing.JButton();
        jButton_imprimir = new javax.swing.JButton();
        jButton_reiniciar = new javax.swing.JButton();
        jButton_tabla = new javax.swing.JButton();
        jButton_salir = new javax.swing.JButton();
        jButton_borrar = new javax.swing.JButton();
        jButton_buscar = new javax.swing.JButton();
        jButton_agregar = new javax.swing.JButton();
        jButton_ver2 = new javax.swing.JButton();
        jButton_cargar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel_foto = new javax.swing.JLabel();
        jLabel_fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_opciones = new javax.swing.JMenu();
        jMenuItem_abrir_menu = new javax.swing.JMenuItem();
        jMenuItem_guardar_menu = new javax.swing.JMenuItem();
        jMenuItem_modificar_menu = new javax.swing.JMenuItem();
        jMenuItem_borrar_menu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_cambiar_sesion = new javax.swing.JMenuItem();
        jMenuItem_salir_menu = new javax.swing.JMenuItem();
        jMenu_alumnos = new javax.swing.JMenu();
        jMenuItem_cargar_alumnos = new javax.swing.JMenuItem();
        jMenuItem_agregar_alumnos = new javax.swing.JMenuItem();
        jMenu_usuarios = new javax.swing.JMenu();
        jMenuItem_crear_usuario = new javax.swing.JMenuItem();
        jMenuItem_cargar_usuario = new javax.swing.JMenuItem();
        jMenuItem_modificar_usuario = new javax.swing.JMenuItem();
        jMenuItem_borrar_usuario = new javax.swing.JMenuItem();
        jMenu_ayuda = new javax.swing.JMenu();
        jMenuItem_javadoc = new javax.swing.JMenuItem();
        jMenuItem_ayuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel_titulo_principal.setFont(new java.awt.Font("Tahoma", 2, 30)); // NOI18N
        jLabel_titulo_principal.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_titulo_principal.setText("Datos Personales");
        getContentPane().add(jLabel_titulo_principal);
        jLabel_titulo_principal.setBounds(10, 0, 250, 60);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel_edad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_edad.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_edad.setText("Edad");

        jLabel_dni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_dni.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_dni.setText("DNI");

        jLabel_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_telefono.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_telefono.setText("Telefono");

        jLabel_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_email.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_email.setText("Email");

        jLabel_provincia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_provincia.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_provincia.setText("Provincia");

        jComboBox_provincias.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox_provincias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Provincia", "La Coruña", "Lugo", "Orense", "Pontevedra" }));
        jComboBox_provincias.setToolTipText("Elige una provincia valida");

        jTextField_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_telefono.setToolTipText("Introducir un telefono valido");
        jTextField_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_telefonoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha Matriculacion");

        jDateChooser_fecha.setToolTipText("Elige una fecha de matriculacion valida");

        jButton_agregar_foto.setText("AGREGAR FOTO");
        jButton_agregar_foto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_agregar_foto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_agregar_fotoActionPerformed(evt);
            }
        });

        jTextField_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_email.setToolTipText("Introducir un email valido");

        jLabel_nombre1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_nombre1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_nombre1.setText("Nombre");

        jTextField_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_nombre.setToolTipText("Introducir un nombre valido");
        jTextField_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_nombreKeyTyped(evt);
            }
        });

        jTextField_dni.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_dni.setToolTipText("Introducir un dni valido");

        jLabel_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_direccion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_direccion.setText("Direccion");

        jTextField_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_direccion.setToolTipText("Introducir una direccion valida");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_edad, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox_provincias, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner_edad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_agregar_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 166, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_edad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner_edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_provincia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_provincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton_agregar_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 70, 300, 440);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado Civil", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("Elige un estado civil valido");
        jPanel2.setOpaque(false);

        buttonGroup_grupo.add(jRadioButton_soltero);
        jRadioButton_soltero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton_soltero.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_soltero.setText("Solter@");
        jRadioButton_soltero.setOpaque(false);

        buttonGroup_grupo.add(jRadioButton_casado);
        jRadioButton_casado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton_casado.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_casado.setText("Casad@");
        jRadioButton_casado.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton_casado)
                    .addComponent(jRadioButton_soltero))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jRadioButton_soltero)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton_casado)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 514, 139, 150);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estudios", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setToolTipText("Elige un estudio valido");
        jPanel3.setOpaque(false);

        jCheckBox_primaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_primaria.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_primaria.setText("Primaria");
        jCheckBox_primaria.setOpaque(false);

        jCheckBox_secundaria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_secundaria.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_secundaria.setText("Secundaria");
        jCheckBox_secundaria.setOpaque(false);

        jCheckBox_formacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_formacion.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_formacion.setText("Formacion P.");
        jCheckBox_formacion.setOpaque(false);

        jCheckBox_univer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCheckBox_univer.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_univer.setText("Universitarios");
        jCheckBox_univer.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_primaria)
                    .addComponent(jCheckBox_secundaria)
                    .addComponent(jCheckBox_formacion)
                    .addComponent(jCheckBox_univer))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox_primaria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_secundaria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_formacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_univer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(170, 510, 139, 160);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);

        jButton_modificar.setText("MODIFICAR");
        jButton_modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });

        jButton_imprimir.setText("IMPRIMIR");
        jButton_imprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirActionPerformed(evt);
            }
        });

        jButton_reiniciar.setText("REINICIAR");
        jButton_reiniciar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_reiniciarActionPerformed(evt);
            }
        });

        jButton_tabla.setText("TABLA");
        jButton_tabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_tablaActionPerformed(evt);
            }
        });

        jButton_salir.setText("SALIR");
        jButton_salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });

        jButton_borrar.setText("BORRAR");
        jButton_borrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrarActionPerformed(evt);
            }
        });

        jButton_buscar.setText("BUSCAR");
        jButton_buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });

        jButton_agregar.setText("AGREGAR");
        jButton_agregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_agregarActionPerformed(evt);
            }
        });

        jButton_ver2.setText("VER");
        jButton_ver2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_ver2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ver2ActionPerformed(evt);
            }
        });

        jButton_cargar.setText("CARGAR");
        jButton_cargar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(jButton_reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jButton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_ver2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jButton_cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jButton_borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ver2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_reiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(10, 680, 1250, 150);

        jTextArea.setEditable(false);
        jTextArea.setColumns(20);
        jTextArea.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea.setLineWrap(true);
        jTextArea.setRows(5);
        jTextArea.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(320, 520, 940, 140);

        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "DNI", "Telefono", "Direccion", "Email", "Edad", "Provincia", "Fecha Matriculacion", "Estado Civil", "Estudios"
            }
        ));
        tabla.setToolTipText("");
        jScrollPane2.setViewportView(tabla);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(320, 80, 940, 430);

        jLabel_foto.setOpaque(true);
        getContentPane().add(jLabel_foto);
        jLabel_foto.setBounds(330, 10, 60, 50);
        getContentPane().add(jLabel_fondo);
        jLabel_fondo.setBounds(0, 0, 1500, 1020);

        jMenu_opciones.setText("Ficheros");

        jMenuItem_abrir_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_abrir_menu.setText("Abrir Fichero");
        jMenuItem_abrir_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_abrir_menuActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_abrir_menu);

        jMenuItem_guardar_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_guardar_menu.setText("Guardar Fichero");
        jMenuItem_guardar_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_guardar_menuActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_guardar_menu);

        jMenuItem_modificar_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_modificar_menu.setText("Modificar Fichero");
        jMenuItem_modificar_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_modificar_menuActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_modificar_menu);

        jMenuItem_borrar_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_borrar_menu.setText("Borrar Fichero");
        jMenuItem_borrar_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_borrar_menuActionPerformed(evt);
            }
        });
        jMenu_opciones.add(jMenuItem_borrar_menu);

        jMenuBar1.add(jMenu_opciones);

        jMenu1.setText("Opciones");

        jMenuItem_cambiar_sesion.setText("Cerrar sesion");
        jMenuItem_cambiar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cambiar_sesionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_cambiar_sesion);

        jMenuItem_salir_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem_salir_menu.setText("Salir");
        jMenuItem_salir_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_salir_menuActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_salir_menu);

        jMenuBar1.add(jMenu1);

        jMenu_alumnos.setText("Alumnos");

        jMenuItem_cargar_alumnos.setText("Cargar alumnos");
        jMenuItem_cargar_alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cargar_alumnosActionPerformed(evt);
            }
        });
        jMenu_alumnos.add(jMenuItem_cargar_alumnos);

        jMenuItem_agregar_alumnos.setText("Agregar alumnos");
        jMenuItem_agregar_alumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_agregar_alumnosActionPerformed(evt);
            }
        });
        jMenu_alumnos.add(jMenuItem_agregar_alumnos);

        jMenuBar1.add(jMenu_alumnos);

        jMenu_usuarios.setText("Usuarios");

        jMenuItem_crear_usuario.setText("Crear usuario");
        jMenuItem_crear_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_crear_usuarioActionPerformed(evt);
            }
        });
        jMenu_usuarios.add(jMenuItem_crear_usuario);

        jMenuItem_cargar_usuario.setText("Cargar usuario");
        jMenuItem_cargar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cargar_usuarioActionPerformed(evt);
            }
        });
        jMenu_usuarios.add(jMenuItem_cargar_usuario);

        jMenuItem_modificar_usuario.setText("Modificar usuario");
        jMenuItem_modificar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_modificar_usuarioActionPerformed(evt);
            }
        });
        jMenu_usuarios.add(jMenuItem_modificar_usuario);

        jMenuItem_borrar_usuario.setText("Borrar usuario");
        jMenuItem_borrar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_borrar_usuarioActionPerformed(evt);
            }
        });
        jMenu_usuarios.add(jMenuItem_borrar_usuario);

        jMenuBar1.add(jMenu_usuarios);

        jMenu_ayuda.setText("Ayuda");

        jMenuItem_javadoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem_javadoc.setText("JavaDoc 8");
        jMenuItem_javadoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_javadocActionPerformed(evt);
            }
        });
        jMenu_ayuda.add(jMenuItem_javadoc);

        jMenuItem_ayuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem_ayuda.setText("Ayuda");
        jMenuItem_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ayudaActionPerformed(evt);
            }
        });
        jMenu_ayuda.add(jMenuItem_ayuda);

        jMenuBar1.add(jMenu_ayuda);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        System.exit(0);     //Para salir de la aplicación.
    }//GEN-LAST:event_jButton_salirActionPerformed

    //Método para reiniciar los campos y la tabla por defecto.
    public void reiniciarCampos() {
        //Para reiniciar los valores y poner los valores por defecto.
        jTextField_nombre.setText("");  //Para borrar el campo del JTextField.
        jTextField_dni.setText("");
        jTextField_telefono.setText("");
        jTextField_direccion.setText("");
        jTextField_email.setText("");
        jSpinner_edad.setValue(0);
        jTextArea.setText("");
        buttonGroup_grupo.clearSelection(); //Quita la selección por defecto del grupo de los JRadioButtons.
        jComboBox_provincias.setSelectedIndex(0); //Para poner el valor por defecto en el JComboBox.
        jDateChooser_fecha.setDate(new JCalendar().getDate()); //Para resetaear el valor de la fecha a la fecha actual por defecto.
        jCheckBox_primaria.setSelected(false);  //Para quitar la selección del JCheckBox.
        jCheckBox_secundaria.setSelected(false);
        jCheckBox_formacion.setSelected(false);
        jCheckBox_univer.setSelected(false);
        jTextArea.setText("");  //Se reinician los valores del JTextArea.    
    }

    private void jButton_reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_reiniciarActionPerformed
        reiniciarCampos();  //Para reiniciar todos los campos.
        lista.limpiarTabla(); //Para limpiar las filas de la tabla por completo.
    }//GEN-LAST:event_jButton_reiniciarActionPerformed

    private void jTextField_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_telefonoKeyTyped
        //Para poder introducir sólo números.
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            jTextField_telefono.setBorder(new LineBorder(Color.RED));   //Para poner el color del borde en rojo en el campo.
            JOptionPane.showMessageDialog(null, "Error, introducir sólo números", "ERROR", 0);
        } else {
            jTextField_telefono.setBorder(null);   //Para quitar el color del borde.
        }
    }//GEN-LAST:event_jTextField_telefonoKeyTyped

    private void jMenuItem_salir_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_salir_menuActionPerformed
        System.exit(0);     //Para salir de la aplicación.
    }//GEN-LAST:event_jMenuItem_salir_menuActionPerformed

    private void jMenuItem_javadocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_javadocActionPerformed
        try {
            //Para acceder al web de JavaDoc 8 en caso de necesitar ayuda.
            Desktop.getDesktop().browse(new URI("https://docs.oracle.com/javase/8/docs/api/"));
        } catch (URISyntaxException | IOException urie) {
            JOptionPane.showMessageDialog(null, "Error, no se ha encontrado la web de ayuda de Java", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_javadocActionPerformed

    //Método para abrir el fichero.
    public void abrirFichero() throws IOException {
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
        //Si se pulsa en aceptar.
        nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
        archivo = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Para abrir el archivo seleccionado.
            Desktop.getDesktop().open(archivo);
        }
    }

    private void jMenuItem_abrir_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_abrir_menuActionPerformed
        try {
            abrirFichero();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, al abrir el archivo", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_abrir_menuActionPerformed

    //Método para borrar el fichero completo seleccionado.
    public void borrarFicheroAlumnos() {
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
        //Si se pulsa en aceptar.
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            if (archivo.exists()) {
                archivo.delete();
                JOptionPane.showMessageDialog(null, "El archivo ha sido borrado correctamente", "RESULTADO", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Error, no se ha podido borrar el archivo, ya que no existe", "ERROR", 0);
            }
        }
    }

    private void jMenuItem_borrar_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_borrar_menuActionPerformed
        borrarFicheroAlumnos();
    }//GEN-LAST:event_jMenuItem_borrar_menuActionPerformed

    //Método para poder imprimir un documento eligiendo la impresora al pulsar un botón.
    public void imprimir() throws IOException {
        PrinterJob job = PrinterJob.getPrinterJob();    //Se crea un objeto job de la clase PrinterJob
        job.printDialog();  //Para ejecutar el cuadro de diálogo de impresoras
        String impresora = job.getPrintService().getName(); //Se recoge el nombre de la impresora.
        Desktop desktop = Desktop.getDesktop(); //Para abrir la ventana de impresión.
        archivo = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
        //Si la acción es soportada se imprime.
        if (desktop.isSupported(Desktop.Action.PRINT)) {
            Process pr = Runtime.getRuntime().exec("Rundll32 printui.dll,PrintUIEntry /y /n \"" + impresora + "\"");    //Se cambia la impresora por defecto.
            desktop.print(archivo);
        } else {
            JOptionPane.showMessageDialog(null, "Error, no ha podido imprimirse el documento", "ERROR", 0);
        }

    }
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
                imprimir();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error, no ha podido imprimirse el documento", "ERROR", 0);
            }
        }
    }//GEN-LAST:event_jButton_imprimirActionPerformed

    private void jButton_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_agregarActionPerformed
        try {
            lista.agregarAlumnos(); //Para agregar datos al ArrayList de alumnos.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no ha podido agregarse alumnos al fichero", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_agregarActionPerformed

    private void jButton_ver2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ver2ActionPerformed
        try {
            cargarFichero();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no se ha podido cargar el fichero", "ERROR", 0);
        }
        //Se reinician los campos y se muestran los campos.
        reiniciarCampos();
        lista.mostrarAlumnos();
    }//GEN-LAST:event_jButton_ver2ActionPerformed

    private void jButton_tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_tablaActionPerformed
        try {
            //Se cargan los datos en el arrayList con el método cargarFichero.
            cargarFichero();
            //Se crea y se instancia el objeto tab de la clase Tabla para poder hacer visible la tabla2.
            Tabla tab = new Tabla(this, true);
            tab.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no se han podido cargar los alumnos en la tabla", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_tablaActionPerformed

    private void jButton_agregar_fotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_agregar_fotoActionPerformed
        //Para cargar una foto que se adapte al jlabel.
        JFileChooser jfc = new JFileChooser(ruta2);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Se crea el filtro de archivos con extensión *.png
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.png", "png");
        //Se indica el filtro
        jfc.setFileFilter(filtro);
        jfc.showOpenDialog(this);
        imagenes = jfc.getSelectedFile();
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel_foto, String.valueOf(imagenes));
        //ImageIcon ii = new ImageIcon(String.valueOf(imagenes));
        //jLabel_foto.setIcon(ii);
        //Para añadir una foto escalable a la etiqueta, gracias a la librería RSScaleLabel.jar.
    }//GEN-LAST:event_jButton_agregar_fotoActionPerformed

    //Método para guardar el fichero seleccionado.
    public void guardarFicheroAlumnos() throws IOException {
        //Se crea el objeto JFileChooser y se instancia en la ruta actual.
        JFileChooser jfc = new JFileChooser(ruta);
        //Se le indica que sólo se puede seleccionar ficheros.
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Se crea el filtro de archivos con extensión *.txt
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
        //Se indica el filtro
        jfc.setFileFilter(filtro);
        //Se abre la ventana y se guarda la opcion seleccionada.
        int seleccion = jfc.showSaveDialog(this);
        PrintWriter pw;
        //Si se pulsa en guardar.
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
            archivo = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
            pw = new PrintWriter(new FileWriter(archivo));
            for (int i = 0; i < alumnos.size(); i++) {
                pw.println(alumnos.get(i).insertarFilas());
            }
            pw.close();
        }
    }

    private void jMenuItem_guardar_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_guardar_menuActionPerformed
        try {
            guardarFicheroAlumnos();
            JOptionPane.showMessageDialog(null, "El archivo se ha guardado correctamente", "RESULTADO", 1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no se ha podido guardar los datos", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_guardar_menuActionPerformed

    //Método para mostrar el fichero en el JTextArea y en el JOptionPane después de haberlo cargado.
    public void mostrarFichero() throws IOException {
        nomFichero = archivo.getAbsolutePath();   //Para recoger el nombre del fichero.
        archivo = new File(nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea, aux = "";
        while ((linea = br.readLine()) != null) {
            System.out.println(linea); //Se muestra todo el resultado de leer el fichero por consola.
            aux += linea + "\n";   //Se concatenan los valores de la cadena y se guardan en una variable auxiliar.
            jTextArea.append(aux);     //Se muestra el fichero en el JTextArea.
        }
        System.out.println("\n\n");
        JOptionPane.showMessageDialog(null, aux);   //Se muestra el fichero en el JOptionPane.
        br.close();  //Cerrar el fichero.
    }

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        lista.buscarAlumnos();  //Se busca si existe el alumno seleccionado.
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jTextField_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nombreKeyTyped
        //Para poder introducir sólo letras.
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
            jTextField_nombre.setBorder(new LineBorder(Color.RED)); //Para poner el color del borde en rojo en el campo.
            JOptionPane.showMessageDialog(null, "Error, introducir sólo letras", "ERROR", 0);
        } else {
            jTextField_nombre.setBorder(null); //Para quitar el color del borde.
        }
    }//GEN-LAST:event_jTextField_nombreKeyTyped

    //Método para poder cargar el fichero y rellenar el arrayList.
    public void cargarFichero() throws IOException {
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
        //Si se pulsa en aceptar.
        nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
        archivo = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
        alumnos = new ArrayList<>();    //Se instancia, para que cree de nuevo el arrayList desde cero.
        BufferedReader br;
        String nomb, dni, telefono, direccion, email, provincia, fecha, estadoCivil, estudios;
        int idAlumno,edad;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            //Se lee desde el fichero y se guarda en el arrayList
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split("]");
                idAlumno = Integer.parseInt(datos[0]);
                nomb = datos[1];
                dni = datos[2];
                telefono = datos[3];
                direccion = datos[4];
                email = datos[5];
                edad = Integer.parseInt(datos[6]);
                provincia = datos[7];
                fecha = datos[8];
                estadoCivil = datos[9];
                estudios = datos[10];
                alumnos.add(new Alumno(idAlumno, nomb, dni, telefono, direccion, email, edad, provincia, fecha, estadoCivil, estudios));   
            }
            br.close();  //Cerrar el fichero.      
        }
    }

    private void jMenuItem_cargar_alumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cargar_alumnosActionPerformed
        try {
            cargarFichero();    //Se carga el fichero en el ArrayList.
            lista.rellenarTabla();  //Se rellena la tabla con los datos del ArrayList.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el fichero no ha podido cargarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_cargar_alumnosActionPerformed

    private void jMenuItem_agregar_alumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_agregar_alumnosActionPerformed
        try {
            lista.agregarAlumnos(); //Para agregar datos al ArrayList de alumnos.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no ha podido agregarse alumnos al fichero", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_agregar_alumnosActionPerformed

    private void jMenuItem_modificar_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_modificar_menuActionPerformed
        //Se crea el objeto del menú modificar y se instancia la clase.
        MenuModificar md = new MenuModificar();
        md.setResizable(false); //Para hacer la ventana no redimensionable.
        md.setLocationRelativeTo(this); //Para colocar la ventana centrada por defecto en cualquier pantalla.
        //md.pack();  //Para hacer que se adapte la ventana a los compoenentes.
        md.setVisible(true);    //Para hacer visible la ventana.
    }//GEN-LAST:event_jMenuItem_modificar_menuActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        //Se crea el objeto del menú modificar y se instancia la clase.
        MenuModificar md = new MenuModificar();
        md.setResizable(false); //Para hacer la ventana no redimensionable.
        md.setLocationRelativeTo(this); //Para colocar la ventana centrada por defecto en cualquier pantalla.
        //md.pack();  //Para hacer que se adapte la ventana a los compoenentes.
        md.setVisible(true);    //Para hacer visible la ventana.
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void jButton_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrarActionPerformed
        try {
            lista.borrarAlumnos();  //Se borran los datos del ArrayList y se pregunat si se quiere guardar en el fichero.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el alumno no ha podido borrarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_borrarActionPerformed

    public void documentoAyuda() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(ayuda);
        BufferedReader br = new BufferedReader(fr);
        String linea, aux = "";
        while ((linea = br.readLine()) != null) {
            System.out.println(linea); //Se muestra todo el resultado de leer el fichero por consola.
            aux += linea + "\n";   //Se concatenan los valores de la cadena y se guardan en una variable auxiliar.
            jTextArea.append(aux);     //Se muestra el fichero en el JTextArea.
        }
        System.out.println("\n\n");
        JOptionPane.showMessageDialog(null, aux);   //Se muestra el fichero en el JOptionPane.
        br.close();  //Cerrar el fichero.
    }

    private void jMenuItem_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ayudaActionPerformed
        try {
            documentoAyuda();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error, no ha podido accederse al fichero por no existir", "ERROR", 0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, no ha podido accederse al fichero", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_ayudaActionPerformed

    private void jButton_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cargarActionPerformed
        try {
            cargarFichero();    //Se carga el fichero en el ArrayList.
            lista.rellenarTabla();  //Se rellena la tabla con los datos del ArrayList.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el fichero no ha podido cargarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jButton_cargarActionPerformed

    private void jMenuItem_cambiar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cambiar_sesionActionPerformed
        InicioSesion is = new InicioSesion();
        is.setVisible(true);    //Para visualizar la ventana de sesión de usuario.
        this.setVisible(false); //Para cerrar la ventana actual.
    }//GEN-LAST:event_jMenuItem_cambiar_sesionActionPerformed

    //Para guardar el fichero de usuarios.
    public void guardarFicheroUsuarios() throws IOException {
        //Se crea el objeto JFileChooser y se instancia en la ruta actual.
        JFileChooser jfc = new JFileChooser(ruta);
        //Se le indica que sólo se puede seleccionar ficheros.
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //Se crea el filtro de archivos con extensión *.txt
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.txt", "txt");
        //Se indica el filtro
        jfc.setFileFilter(filtro);
        //Se abre la ventana y se guarda la opcion seleccionada.
        int seleccion = jfc.showSaveDialog(this);
        PrintWriter pw;
        //Si se pulsa en guardar.
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
            archivoUsuarios = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
            pw = new PrintWriter(new FileWriter(archivoUsuarios));
            for (int i = 0; i < usuarios.size(); i++) {
                pw.println(usuarios.get(i).toString());
            }
            pw.close();

        }
    }

    private void jMenuItem_crear_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_crear_usuarioActionPerformed
        if (bandera) {
            try {
                lista2.agregarUsuario();    //Para guardar los campos del arrayList.   
                JOptionPane.showMessageDialog(null, "Los usuarios han sido guardados con exito", "RESULTADO", 1);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error, no se ha podido guardar el fichero de usuarios", "ERROR", 0);
            }

        }
    }//GEN-LAST:event_jMenuItem_crear_usuarioActionPerformed

    private void jMenuItem_borrar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_borrar_usuarioActionPerformed
        try {
            lista2.borrarUsuarios();  //Se borran los datos del ArrayList y se pregunat si se quiere guardar en el fichero.
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el usuario no ha podido borrarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_borrar_usuarioActionPerformed

    private void jMenuItem_modificar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_modificar_usuarioActionPerformed
        //Se crea el objeto del menú modificar y se instancia la clase.
        MenuModificarUsuario mdu = new MenuModificarUsuario();
        mdu.setResizable(false); //Para hacer la ventana no redimensionable.
        mdu.setLocationRelativeTo(this); //Para colocar la ventana centrada por defecto en cualquier pantalla.
        //md.pack();  //Para hacer que se adapte la ventana a los componenentes.
        mdu.setVisible(true);    //Para hacer visible la ventana.
    }//GEN-LAST:event_jMenuItem_modificar_usuarioActionPerformed

    //Método para cargar el fichero de usuarios.
    public void cargarFicheroUsuarios() throws IOException {
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
        //Si se pulsa en aceptar.
        nomFichero = jfc.getSelectedFile().getName();   //Para recoger el nombre del fichero.
        archivo = new File(ruta, nomFichero);  //Se crea el fichero con la ruta, el nombre y la extensión. 
        usuarios = new ArrayList<>();    //Se instancia, para que cree de nuevo el arrayList desde cero.
        BufferedReader br;
        String nomb, cla;
        int idUsuario;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            //Se lee desde el fichero y se guarda en el arrayList
            while ((linea = br.readLine()) != null) {
                String datos[] = linea.split("&");
                idUsuario = Integer.parseInt(datos[0]); //Se convierte el String a entero.
                nomb = datos[1];
                cla = datos[2];
                usuarios.add(new Usuario(idUsuario, nomb, cla));    //Se añade al arrayList por medio del constructor.
                
            }
            br.close();  //Cerrar el fichero.      
        }
    }

    private void jMenuItem_cargar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cargar_usuarioActionPerformed
        try {
            cargarFicheroUsuarios();    //Se carga el fichero de usuarios en el ArrayList.
            JOptionPane.showMessageDialog(null, "Los usuarios han sido cargados con exito", "RESULTADO", 1);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, el fichero no ha podido cargarse", "ERROR", 0);
        }
    }//GEN-LAST:event_jMenuItem_cargar_usuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected static javax.swing.ButtonGroup buttonGroup_grupo;
    public static javax.swing.JButton jButton_agregar;
    public static javax.swing.JButton jButton_agregar_foto;
    public static javax.swing.JButton jButton_borrar;
    public static javax.swing.JButton jButton_buscar;
    public static javax.swing.JButton jButton_cargar;
    public static javax.swing.JButton jButton_imprimir;
    public static javax.swing.JButton jButton_modificar;
    public static javax.swing.JButton jButton_reiniciar;
    public static javax.swing.JButton jButton_salir;
    public static javax.swing.JButton jButton_tabla;
    public static javax.swing.JButton jButton_ver2;
    public static javax.swing.JCheckBox jCheckBox_formacion;
    public static javax.swing.JCheckBox jCheckBox_primaria;
    public static javax.swing.JCheckBox jCheckBox_secundaria;
    public static javax.swing.JCheckBox jCheckBox_univer;
    public static javax.swing.JComboBox<String> jComboBox_provincias;
    public static com.toedter.calendar.JDateChooser jDateChooser_fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_dni;
    private javax.swing.JLabel jLabel_edad;
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_fondo;
    public static javax.swing.JLabel jLabel_foto;
    private javax.swing.JLabel jLabel_nombre1;
    private javax.swing.JLabel jLabel_provincia;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JLabel jLabel_titulo_principal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JMenuItem jMenuItem_abrir_menu;
    private javax.swing.JMenuItem jMenuItem_agregar_alumnos;
    private javax.swing.JMenuItem jMenuItem_ayuda;
    public static javax.swing.JMenuItem jMenuItem_borrar_menu;
    public static javax.swing.JMenuItem jMenuItem_borrar_usuario;
    private javax.swing.JMenuItem jMenuItem_cambiar_sesion;
    private javax.swing.JMenuItem jMenuItem_cargar_alumnos;
    private javax.swing.JMenuItem jMenuItem_cargar_usuario;
    public static javax.swing.JMenuItem jMenuItem_crear_usuario;
    public static javax.swing.JMenuItem jMenuItem_guardar_menu;
    private javax.swing.JMenuItem jMenuItem_javadoc;
    public static javax.swing.JMenuItem jMenuItem_modificar_menu;
    public static javax.swing.JMenuItem jMenuItem_modificar_usuario;
    public static javax.swing.JMenuItem jMenuItem_salir_menu;
    public static javax.swing.JMenu jMenu_alumnos;
    public static javax.swing.JMenu jMenu_ayuda;
    public static javax.swing.JMenu jMenu_opciones;
    public static javax.swing.JMenu jMenu_usuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JRadioButton jRadioButton_casado;
    public static javax.swing.JRadioButton jRadioButton_soltero;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JSpinner jSpinner_edad;
    public static javax.swing.JTextArea jTextArea;
    public static javax.swing.JTextField jTextField_direccion;
    public static javax.swing.JTextField jTextField_dni;
    public static javax.swing.JTextField jTextField_email;
    public static javax.swing.JTextField jTextField_nombre;
    public static javax.swing.JTextField jTextField_telefono;
    public static javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
