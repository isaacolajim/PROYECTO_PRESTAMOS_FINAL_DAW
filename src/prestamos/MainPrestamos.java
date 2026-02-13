package prestamos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class MainPrestamos {
    public static void main(String[] args)throws Excepciones.UsuarioInvalidoException, Excepciones.LibroNoDisponibleException,
            Excepciones.PrestamoInvalidoException, Excepciones.UsuarioRepetidoException, Excepciones.UsuarioSancionadoException{
        Scanner in = new Scanner(System.in);
        GestorBiblioteca gestor = new GestorBiblioteca();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int resp;
        do{
            System.out.println("1. REGISTRAR NUEVO USUARIO");
            System.out.println("2. REALIZAR PRESTAMO DE LIBRO");
            System.out.println("3. DEVOLVER LIBRO");
            System.out.println("4. CONSULTAR ESTADO DE USUARIO");
            System.out.println("5. MOSTRAR PRESTAMOS ACTIVOS");
            System.out.println("6. MOSTRAR USUARIOS SANCIONADOS");
            System.out.println("7. ACTUALIZAR SANCIONES");
            System.out.println("8. SALIR");
            try {
                resp = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número.");
                resp = 0; // Para que repita el bucle
            }
            switch (resp){
                case 1:
                    try{
                        System.out.println("Nombre: ");
                        String nombre = in.nextLine();
                        System.out.print("Email: ");
                        String email = in.nextLine();
                        System.out.print("Número de socio (SOCxxxxx): ");
                        String socio = in.nextLine();
                        System.out.print("Fecha registro (dd/mm/aaaa): ");
                        LocalDate fecha = LocalDate.parse(in.nextLine(), formatoFecha);

                        Usuario u = new Usuario(nombre,email,socio,fecha);
                        gestor.registrarUsuario(u);
                        System.out.println("USUARIO CORRECTAMENRE REGISTRADO");
                    }
                    catch (DateTimeParseException e) {
                        System.out.println("Error: Formato de fecha incorrecto. Usa dd/mm/aaaa");
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:

            }
        }
        while (resp!=8);



    }
}
