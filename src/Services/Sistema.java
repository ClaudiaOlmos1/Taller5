package Services;

import Entities.Libro;
import Entities.Usuario;

public interface Sistema {
    boolean iniciarSesion(String rut, String contrasena);
    Usuario buscarUsuario(String rut);
    Libro buscarLibro(String isbn);
    boolean prestarLibro(String isbn, String rutUsuario);
    boolean agregarLibro(Libro libro);
    boolean devolverLibro(String isbn, String rutVendedor);
    void guardarLibros();
    void cerrarSistema();
}