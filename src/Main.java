import Services.SistemaImpl;
import UI.Interfaz;

/**
 * Clase principal de la aplicación.
 */
public class Main {
    /**
     * Punto de entrada principal de la aplicación.
     *
     * @param args Los argumentos de línea de comando.
     */
    public static void main(String[] args) {
        SistemaImpl sistema = new SistemaImpl(); // Inicializa la implementación del sistema
        Interfaz interfaz = new Interfaz(sistema); // Crea la interfaz con la implementación del sistema
        interfaz.mostrar(); // Muestra la interfaz
    }
}
