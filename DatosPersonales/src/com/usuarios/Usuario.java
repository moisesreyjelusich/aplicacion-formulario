/*
 *  Esta clase es la plantilla de usuario con sus dos constructores, los 
 *  métodos getters y setters y el método toString.
 */
package com.usuarios;

/**
 * El autor de esta aplicación es: Moisés Rey Jelusich
 * @author moises
 */
public class Usuario {
    //Atributos.
    private int idUsuario;
    private String nombre;
    private String clave;
    private int contUsuarios;

    //Constructor vacío.
    public Usuario() {
        this.idUsuario = ++contUsuarios;  //Para que sea el id autoincrementable.
    }
    
    //Constructor con 2 variables.
    public Usuario(int idUsuario, String nombre, String clave) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.clave = clave;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    //Métodos getters y setters.
    public void setIdUsuario(int idUsuario) {    
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    //Método toString.
    @Override
    public String toString() {
        return idUsuario + "&" + nombre + "&" + clave;
    }
}
