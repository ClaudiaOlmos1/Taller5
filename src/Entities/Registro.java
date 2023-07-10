package Entities;

/**
 * Clase Registro que representa un registro de transacción en la biblioteca.
 */
public class Registro {
    private String rutVendedor;
    private String nombre;
    private String apellido;
    private String isbnLibro;
    private String nombreLibro;
    private String tipoTransaccion;

    /**
     * Constructor para la clase Registro.
     *
     * @param rutVendedor el RUT del vendedor.
     * @param nombre el nombre del vendedor.
     * @param apellido el apellido del vendedor.
     * @param isbnLibro el ISBN del libro involucrado en la transacción.
     * @param nombreLibro el nombre del libro involucrado en la transacción.
     * @param tipoTransaccion el tipo de transacción que se ha realizado.
     */
    public Registro(String rutVendedor, String nombre, String apellido, String isbnLibro, String nombreLibro, String tipoTransaccion) {
        this.rutVendedor = rutVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.isbnLibro = isbnLibro;
        this.nombreLibro = nombreLibro;
        this.tipoTransaccion = tipoTransaccion;
    }
    public String getRutVendedor() {
        return rutVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    // Setters
    public void setRutVendedor(String rutVendedor) {
        this.rutVendedor = rutVendedor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setIsbnLibro(String isbnLibro) {
        this.isbnLibro = isbnLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
}