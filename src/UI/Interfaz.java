package UI;

import Entities.Libro;
import Services.SistemaImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase Interfaz, proporciona la interfaz gráfica de usuario para la aplicación BiblioTech.
 */
public class Interfaz {

    Color celestePastel = new Color(150, 200, 250);

    Color azulSuave = new Color(100, 150, 200);

    Color Gris = new Color(192, 192, 192);
    private SistemaImpl sistema;
    private JFrame frame;
    private JTextField rutField;
    private JPasswordField contrasenaField;

    private String rutUsuario;

    /**
     * Constructor de la clase Interfaz.
     * @param sistema La instancia del sistema que se va a utilizar.
     */
    public Interfaz(SistemaImpl sistema) {
        this.sistema = sistema;
        createGUI();
    }

    /**
     * Crea la interfaz gráfica de usuario.
     */
    private void createGUI() {
        frame = new JFrame("BiblioTech");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JPanel panel = new JPanel(new GridLayout(6, 5));
        panel.setBackground(celestePastel);

        JLabel rutLabel = new JLabel("RUT:");
        rutField = new JTextField(20);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaField = new JPasswordField(20);
        JButton iniciarSesionButton = new JButton("Iniciar sesión");

        Font font = new Font("Times New Roman", Font.BOLD, 22);
        rutLabel.setFont(font);
        rutField.setFont(font);
        contrasenaLabel.setFont(font);
        contrasenaField.setFont(font);
        iniciarSesionButton.setFont(font);

        iniciarSesionButton.setBackground(azulSuave);
        iniciarSesionButton.setForeground(Color.BLACK);

        iniciarSesionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rut = rutField.getText();
                String contrasena = new String(contrasenaField.getPassword());
                if (sistema.iniciarSesion(rut, contrasena)) {
                    rutUsuario = rut;
                    JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
                    mostrarMenuPrincipal();
                } else {
                    JOptionPane.showMessageDialog(frame, "RUT o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cerrarProgramaButton = new JButton("Cerrar programa");
        cerrarProgramaButton.setBackground(azulSuave);
        cerrarProgramaButton.setForeground(Color.black);

        cerrarProgramaButton.setFont(font);

        cerrarProgramaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.cerrarSistema();
                frame.dispose();
            }
        });

        panel.add(rutLabel);
        panel.add(rutField);
        panel.add(contrasenaLabel);
        panel.add(contrasenaField);
        panel.add(iniciarSesionButton);
        panel.add(cerrarProgramaButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }


    /**
     * Muestra el menú principal de la aplicación después de un inicio de sesión exitoso.
     */
    private void mostrarMenuPrincipal() {
        frame.getContentPane().removeAll(); // Eliminar los componentes anteriores

        frame.setTitle("Menú principal");
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setBackground(celestePastel);
        panel.setLayout(new BorderLayout());

        JLabel bienvenidaLabel = new JLabel("Bienvenido a BiblioTech!");
        bienvenidaLabel.setHorizontalAlignment(JLabel.CENTER);
        bienvenidaLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        panel.add(bienvenidaLabel, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(5, 1, 10, 5));

        JButton buscarLibroButton = new JButton("Buscar libro");
        buscarLibroButton.setFont(new Font("Times New Roman",Font.BOLD,22));
        buscarLibroButton.setBackground(azulSuave);
        buscarLibroButton.setForeground(Color.BLACK);
        buscarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnBuscar = JOptionPane.showInputDialog(frame, "Ingresa el ISBN del libro a buscar:");
                Libro libroBuscado = sistema.buscarLibro(isbnBuscar);
                if (libroBuscado != null) {
                    JOptionPane.showMessageDialog(frame, "Libro encontrado: \n" +
                            "ISBN: " + libroBuscado.getIsbn() + "\n" +
                            "Nombre: " + libroBuscado.getNombre() + "\n" +
                            "Autor: " + libroBuscado.getAutor() + "\n" +
                            "Categoría: " + libroBuscado.getCategoria() + "\n" +
                            "Páginas: " + libroBuscado.getPaginas() + "\n" +
                            "Stock: " + libroBuscado.getStock());
                } else {
                    JOptionPane.showMessageDialog(frame, "No se encontró un libro con el ISBN proporcionado.");
                }
            }
        });
        botonesPanel.add(buscarLibroButton);

        JButton prestarLibroButton = new JButton("Prestar libro");
        prestarLibroButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
        prestarLibroButton.setBackground(azulSuave);
        prestarLibroButton.setForeground(Color.BLACK);
        prestarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnPrestar = JOptionPane.showInputDialog(frame, "Ingresa el ISBN del libro a prestar:");
                boolean prestamoExitoso = sistema.prestarLibro(isbnPrestar, rutUsuario);
                if (prestamoExitoso) {
                    JOptionPane.showMessageDialog(frame, "Préstamo exitoso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo prestar el libro. Verifica el ISBN y asegúrate de que el libro esté disponible.");
                }
            }
        });
        botonesPanel.add(prestarLibroButton);

        JButton agregarLibroButton = new JButton("Agregar libro");
        agregarLibroButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
        agregarLibroButton.setBackground(azulSuave);
        agregarLibroButton.setForeground(Color.BLACK);
        agregarLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnAgregar = JOptionPane.showInputDialog(frame, "Ingresa el ISBN del libro a agregar:");
                String nombreAgregar = JOptionPane.showInputDialog(frame, "Ingresa el nombre del libro:");
                String autorAgregar = JOptionPane.showInputDialog(frame, "Ingresa el autor del libro:");
                String categoriaAgregar = JOptionPane.showInputDialog(frame, "Ingresa la categoría del libro:");
                int paginasAgregar = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingresa el número de páginas del libro:"));
                int stockAgregar = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingresa el stock del libro:"));

                Libro libroAgregar = new Libro(isbnAgregar, nombreAgregar, autorAgregar, categoriaAgregar, paginasAgregar, stockAgregar);
                boolean libroAgregado = sistema.agregarLibro(libroAgregar);
                if (libroAgregado) {
                    JOptionPane.showMessageDialog(frame, "Libro agregado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(frame, "El libro ya existe en el sistema.");
                }
            }
        });
        botonesPanel.add(agregarLibroButton);

        JButton devolverLibroButton = new JButton("Devolver libro");
        devolverLibroButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
        devolverLibroButton.setBackground(azulSuave);
        devolverLibroButton.setForeground(Color.BLACK);
        devolverLibroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String isbnDevolver = JOptionPane.showInputDialog(frame, "Ingresa el ISBN del libro a devolver:");
                boolean devolucionExitosa = sistema.devolverLibro(isbnDevolver, rutUsuario);
                if (devolucionExitosa) {
                    JOptionPane.showMessageDialog(frame, "Devolución exitosa.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo devolver el libro. Verifica el RUT y el ISBN.");
                }
            }
        });
        botonesPanel.add(devolverLibroButton);

        JButton cerrarSesionButton = new JButton("Cerrar sesión");
        cerrarSesionButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
        cerrarSesionButton.setBackground(azulSuave);
        cerrarSesionButton.setForeground(Color.BLACK);
        cerrarSesionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sistema.cerrarSistema();
                JOptionPane.showMessageDialog(frame, "Sesión cerrada exitosamente.");
                frame.dispose();
            }
        });
        botonesPanel.add(cerrarSesionButton);

        panel.add(botonesPanel, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
    }

    /**
     * Hace visible el marco principal de la aplicación.
     */
    public void mostrar() {
        frame.setVisible(true);
    }
}
