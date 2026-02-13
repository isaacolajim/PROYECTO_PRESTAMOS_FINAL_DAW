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
                resp = 0;
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
                    try{
                        System.out.println("Codigo libro (LIB0000): ");
                        String codigolibro = in.nextLine();
                        System.out.println("Titulo: ");
                        String titulo = in.nextLine();
                        System.out.println("Numero socio: ");
                        String numsocio = in.nextLine();
                        System.out.println("Fecha de prestamo: ");
                        LocalDate fechaPrestamo = LocalDate.parse(in.nextLine(), formatoFecha);
                        Usuario uprestamo=gestor.buscarUsuario(numsocio);
                        Prestamo p = gestor.realizarPrestamo(codigolibro,titulo,uprestamo,fechaPrestamo);
                        System.out.println("Prestamo realizado");
                        System.out.println("Devolucion prevista: "+p.getFechaDevolucionPrevista().format(formatoFecha));
                    }
                    catch (DateTimeParseException e) {
                        System.out.println("Error: Formato de fecha incorrecto. Usa dd/mm/aaaa");
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    /*try {
                        System.out.println("Codigo libro (LIB0000): ");
                        String codigolibro = in.nextLine();
                        System.out.println("Fecha devolucion: ");
                        LocalDate fechadevolucion = LocalDate.parse(in.nextLine(), formatoFecha);
                        boolean devolucion = gestor.devolverLibro(codigolibro,fechadevolucion);
                        if(fechadevolucion){

                        }
                    }*/
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
                    System.out.println("SALIENDO..............");
                    break;

            }
        }
        while (resp!=8);



    }
}
