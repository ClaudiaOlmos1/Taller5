package Entities;

/**
 * La clase Libro representa un libro en la biblioteca.
 */
public class Libro {
    private String isbn;
    private String nombre;
    private String autor;
    private String categoria;
    private int paginas;
    private int stock;

    /**
     * Constructor para la clase Libro.
     *
     * @param isbn      El ISBN del libro.
     * @param nombre    El nombre del libro.
     * @param autor     El autor del libro.
     * @param categoria La categoría del libro.
     * @param paginas   El número de páginas del libro.
     * @param stock     El stock del libro.
     */
    public Libro(String isbn, String nombre, String autor, String categoria, int paginas, int stock) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.paginas = paginas;
        this.stock = stock;
    }

    /**
     * Presta el libro.
     *
     * @return Verdadero si el libro se prestó con éxito, falso si no hay stock.
     */
    public boolean prestar() {
        if (stock > 0) {
            stock--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Devuelve el libro aumentando el stock.
     */
    public void devolver() {
        stock++;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getPaginas() {
        return paginas;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Representacion de caracteres Entities.Libro.
     * @return caracteres que representa el libro.
     */
    public String toString() {
        return "ISBN: " + isbn + "\n" +
                "Nombre: " + nombre + "\n" +
                "Autor: " + autor + "\n" +
                "Categoría: " + categoria + "\n" +
                "Páginas: " + paginas + "\n" +
                "Stock: " + stock;
    }
}