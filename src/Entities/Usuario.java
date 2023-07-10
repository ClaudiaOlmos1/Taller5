package Entities;

/**
 * La clase Usuario representa un usuario de la biblioteca.
 */
public class Usuario {
    private String rut;
    private String nombre;
    private String apellido;
    private String contrasena;

    /**
     * Constructor para la clase Usuario.
     *
     * @param rut el RUT del usuario.
     * @param nombre el nombre del usuario.
     * @param apellido el apellido del usuario.
     * @param contrasena la contraseña del usuario.
     */
    public Usuario(String rut, String nombre, String apellido, String contrasena) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la almacenada para este usuario.
     *
     * @param contrasena La contraseña a verificar.
     * @return Verdadero si las contraseñas coinciden, falso en caso contrario.
     */
    public boolean verificarContrasena(String contrasena) {
        System.out.println("Contraseña proporcionada: " + contrasena);
        System.out.println("Contraseña almacenada: " + this.contrasena);
        return this.contrasena.equals(contrasena);
    }
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    // Setters
    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}