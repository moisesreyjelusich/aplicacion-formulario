/**
 * Esta clase es la plantilla de cada alumno y consta de los atributos, los diferentes constructores,
 * los getters y setters, aparte de varios métodos toString diferentes.
 */
package com.alumnos;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 * @author moises
 */

public class Alumno {

    //Atributos.
    private int idAlumno;
    private String nombre;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;
    private int edad;
    private String provincia;
    private String fecha;
    private String estadoCivil;
    private String estudios;
    private static int contAlumnos;

    //Constructor vacío.
    public Alumno() {
        this.idAlumno = ++contAlumnos;  //Para que sea el id autoincrementable.
    }

    //Constructor con los parámetros para inicializarlos.
    public Alumno(int idAlumno, String nombre, String dni, String telefono, String direccion, String email, int edad, String provincia, String fecha, String estadoCivil, String estudios) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.edad = edad;
        this.provincia = provincia;
        this.fecha = fecha;
        this.estadoCivil = estadoCivil;
        this.estudios = estudios;
    }

    //Getters y Setters.
    public int getIdAlumno() {
        return idAlumno;
    }
    
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEstudios() {
        return estudios;
    }

    public void setEstudios(String estudios) {
        this.estudios = estudios;
    }

    //Método toString().
    @Override
    public String toString() {
        return "Id Alumno " + idAlumno + "\nNombre: " + nombre + "\nDNI: " + dni + "\nTelefono: " + telefono + "\nDireccion: " + direccion + "\nEmail: " + email + "\nEdad: " + edad + "\nProvincia: " + provincia + "\nFecha de nacimiento: " + fecha + "\nEstado Civil: " + estadoCivil + "\nEstudios: " + estudios + "\n\n";
    }
    
    //Método para poder insertar los datos en el fichero usando el separador ",".
    public String insertarFilas() {
        return idAlumno + "]" + nombre + "]" + dni + "]" + telefono + "]" + direccion + "]" + email + "]" + edad + "]" + provincia + "]" + fecha + "]" + estadoCivil + "]" + estudios;
    }
}
