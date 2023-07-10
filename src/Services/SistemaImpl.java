package Services;

import Entities.Libro;
import Entities.Registro;
import Entities.Usuario;
import Services.Sistema;
import Utils.ManejadorArchivos;

import java.util.*;

/**
 * Implementación del sistema de gestión de biblioteca.
 */
public class SistemaImpl implements Sistema {
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Registro> registros;
    private ManejadorArchivos manejadorArchivos;

    /**
     * Constructor de la clase SistemaImpl.
     * Inicializa las listas de usuarios, libros y registros,
     * y crea una instancia de ManejadorArchivos para leer los datos iniciales.
     */
    public SistemaImpl() {
        manejadorArchivos = new ManejadorArchivos();
        usuarios = manejadorArchivos.leerUsuarios("usuarios.txt");
        libros = manejadorArchivos.leerLibros("libros.txt");
        registros = new ArrayList<>();
    }

    /**
     * Inicia sesión de un usuario en el sistema.
     *
     * @param rut el RUT del usuario
     * @param contrasena la contraseña del usuario
     * @return true si la sesión se inicia correctamente, false en caso contrario
     */
    @Override
    public boolean iniciarSesion(String rut, String contrasena) {
        System.out.println("RUT proporcionado: " + rut);
        System.out.println("Contraseña proporcionada: " + contrasena);
        Usuario usuario = buscarUsuario(rut);
        System.out.println("Usuario obtenido: " + usuario);
        if (usuario != null && usuario.verificarContrasena(contrasena)) {
            return true;
        }
        return false;
    }

    /**
     * Busca un usuario por su RUT.
     *
     * @param rut el RUT del usuario a buscar
     * @return el Usuario encontrado, o null si no se encuentra
     */
    @Override
    public Usuario buscarUsuario(String rut) {
        for (Usuario usuario : usuarios) {
            if (usuario.getRut().equals(rut)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Busca un libro por su ISBN.
     *
     * @param isbn el ISBN del libro a buscar
     * @return el Libro encontrado, o null si no se encuentra
     */
    @Override
    public Libro buscarLibro(String isbn) {
        for (Libro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }

    /**
     * Realiza el préstamo de un libro a un usuario.
     *
     * @param isbn el ISBN del libro a prestar
     * @param rutUsuario  el RUT del usuario que realiza el préstamo
     * @return true si el préstamo se realiza correctamente, false en caso contrario
     */
    @Override
    public boolean prestarLibro(String isbn, String rutUsuario) {
        Libro libro = buscarLibro(isbn);
        Usuario usuario = buscarUsuario(rutUsuario);
        if (libro != null && usuario != null) {
            if (libro.getStock() > 0 && libro.prestar()) {
                Registro registro = new Registro(rutUsuario, usuario.getNombre(), usuario.getApellido(), isbn, libro.getNombre(), "Prestamo");
                registros.add(registro);
                guardarLibros();
                return true;
            } else {
                System.out.println("El libro no está disponible para prestar.");
                return false;
            }
        } else {
            System.out.println("No se encontró un libro con el ISBN o un usuario con el RUT proporcionado.");
            return false;
        }
    }

    /**
     * Agrega un libro al sistema.
     *
     * @param libro el libro a agregar
     * @return true si el libro se agrega correctamente, false si el libro ya existe en el sistema
     */
    @Override
    public boolean agregarLibro(Libro libro) {
        for (Libro libroExistente:libros){
            if (libroExistente.getIsbn().equalsIgnoreCase(libro.getIsbn())){
                //El libro ya existe en el sistema
                return false;
            }
        }
        //El libro no existe en el sistema, asi que lo agregamos.
        libros.add(libro);
        guardarLibros();
        return true;
    }

    /**
     * Realiza la devolución de un libro.
     *
     * @param isbn el ISBN del libro a devolver
     * @param rutVendedor el RUT del vendedor que realiza la devolución
     * @return true si la devolución se realiza correctamente, false si el libro no se encuentra en el sistema
     */
    @Override
    public boolean devolverLibro(String isbn, String rutVendedor) {
        Usuario usuario = buscarUsuario(rutVendedor);
        Libro libro = buscarLibro(isbn);
        if (libro != null) {
            libro.devolver();
            Registro registro = new Registro(rutVendedor, usuario.getNombre(), usuario.getApellido(), isbn, libro.getNombre(), "Devolucion");
            registros.add(registro);
            guardarLibros();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Guarda los libros en el archivo de libros.
     */
    @Override
    public void guardarLibros() {
        manejadorArchivos.escribirLibros(libros, "libros.txt");
    }

    /**
     * Cierra el sistema.
     * Guarda los libros en el archivo de libros y los registros en el archivo de registro.
     */
    @Override
    public void cerrarSistema() {
        guardarLibros();
        manejadorArchivos.escribirRegistro(registros, "registro.txt");
    }
}
