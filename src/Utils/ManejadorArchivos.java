package Utils;


import Entities.Libro;
import Entities.Registro;
import Entities.Usuario;

import java.io.*;
import java.util.*;

/**
 * Clase para manejar archivos de usuarios, libros y registros.
 */
public class ManejadorArchivos {

    /**
     * Lee el archivo de usuarios y devuelve una lista de usuarios.
     *
     * @param rutaArchivo la ruta del archivo de usuarios
     * @return una lista de usuarios leída desde el archivo
     */
    public List<Usuario> leerUsuarios(String rutaArchivo) {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String rut = partes[0];
                String nombre = partes[1];
                String apellido = partes[2];
                String contrasena = partes[3];
                usuarios.add(new Usuario(rut, nombre, apellido, contrasena));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Lee el archivo de libros y devuelve una lista de libros.
     *
     * @param rutaArchivo la ruta del archivo de libros
     * @return una lista de libros leída desde el archivo
     */
    public List<Libro> leerLibros(String rutaArchivo) {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String isbn = partes[0];
                String nombre = partes[1];
                String autor = partes[2];
                String categoria = partes[3];
                int paginas = Integer.parseInt(partes[4]);
                int stock = Integer.parseInt(partes[5]);
                libros.add(new Libro(isbn, nombre, autor, categoria, paginas, stock));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libros;
    }

    /**
     * Escribe los registros en el archivo de registro.
     *
     * @param registros los registros a escribir
     * @param rutaArchivo la ruta del archivo de registro
     */
    public void escribirRegistro(List<Registro> registros, String rutaArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            for (Registro registro : registros) {
                pw.println(registro.getRutVendedor() + ", " + registro.getNombre() + ", " +
                        registro.getApellido() + ", " + registro.getIsbnLibro() + ", " +
                        registro.getNombreLibro() + ", " + registro.getTipoTransaccion());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe los libros en el archivo de libros.
     *
     * @param libros los libros a escribir
     * @param rutaArchivo la ruta del archivo de libros
     */
    public void escribirLibros(List<Libro> libros, String rutaArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            for (Libro libro : libros) {
                pw.println(libro.getIsbn() + "," + libro.getNombre() + "," + libro.getAutor() + "," +
                        libro.getCategoria() + "," + libro.getPaginas() + "," + libro.getStock());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

