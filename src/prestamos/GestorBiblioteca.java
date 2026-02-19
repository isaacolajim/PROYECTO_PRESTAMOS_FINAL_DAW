package prestamos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GestorBiblioteca extends Excepciones {
    private static  final int MAX_USUARIOS = 50;
    private static  final int MAX_PRESTAMOS = 200;
    private Usuario[] usuarios;
    private Prestamo[] prestamos;
    private int numeroUsuarios;
    private int numeroPrestamos;

    public GestorBiblioteca(){
        Usuario[] usuarios = new Usuario[MAX_USUARIOS];
        Prestamo[] prestamos = new Prestamo[MAX_PRESTAMOS];
        numeroUsuarios = 0;
        numeroPrestamos = 0;
    }

    public void registrarUsuario(Usuario usuario)throws UsuarioRepetidoException {
        for(int i = 0; i<usuarios.length;i++){
            if(usuarios[i]==usuario){
                throw new UsuarioRepetidoException("Ese usuario ya está registrado");
            }
        }
        usuarios[numeroUsuarios]=usuario;
        numeroUsuarios++;
    }

    public Prestamo realizarPrestamo(String codigoLibro, String tituloLibro, Usuario socio, LocalDate fechaPrestamo)throws PrestamoInvalidoException,
            UsuarioSancionadoException,LibroNoDisponibleException{
        if(codigoLibro==null||!codigoLibro.matches("^LIB\\d{4}$")){
            throw  new PrestamoInvalidoException("EL FORMATO DEL CODIGO DEBE SER LIB0001 ");
        }
        if(tituloLibro==null){
            throw new PrestamoInvalidoException("NO HAS INTRODUCIDO EL TITULO DEL LIBRO");
        }
        if(socio==null){
            throw new PrestamoInvalidoException("NO HAS INTRODUCIDO NINGUN USUARIO");
        }
        if(socio.estaSancionado()){
            throw  new UsuarioSancionadoException("ESTE USUARIO ESTA SANCIONADO");
        }

        for(int i = 0; i<prestamos.length; i++){
            if(prestamos[i].getCodigoLibro().matches(codigoLibro) && prestamos[i].getFechaDevolucionReal()==null){
                throw new LibroNoDisponibleException("ESTE LIBRO NO ESTA DISPONIBLE");
            }
        }
        Prestamo prestamo= new Prestamo (codigoLibro,socio,tituloLibro, fechaPrestamo);
        return prestamo;
    }

    public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion) throws PrestamoInvalidoException {
        for(int i=0; i<prestamos.length;i++){
            if(prestamos[i] != null && prestamos[i].getCodigoLibro().matches(codigoLibro) && prestamos[i].getFechaDevolucionReal()==null){

                if(fechaDevolucion.isBefore(prestamos[i].getFechaPrestamo())){
                    throw new PrestamoInvalidoException("LA FECHA DE DEVOLUCION NO PUEDE SER ANTERIOR A LA DEL PRESTAMO");
                }

                prestamos[i].setFechaDevolucionReal(fechaDevolucion);

                if (fechaDevolucion.isAfter(prestamos[i].getFechaDevolucionPrevista())) {
                    long retraso = ChronoUnit.DAYS.between(prestamos[i].getFechaDevolucionPrevista(),fechaDevolucion);
                    prestamos[i].getSocio().sancionar((int)retraso);

                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String fechaFin = prestamos[i].getSocio().getFechaFinSancion().format(fmt);

                    System.out.println("Devolución registrada con " + retraso + " días de retraso");
                    System.out.println("Usuario sancionado por " + retraso + " días (hasta el " + fechaFin + ")");
                }

                return true;
            }
        }
        return false;
    }
    public Usuario buscarUsuario(String numeroSocio) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getNumeroSocio().equals(numeroSocio)) {
                return usuarios[i];
            }
        }
        return null;
    }

    public Prestamo getPrestamos() {
        return prestamos[numeroPrestamos];
    }

    public Usuario getUsuarios() {
        return usuarios[numeroUsuarios];
    }

    public String toString(){
        return " PRESTAMOS"+this.getPrestamos()+"Usuarios"+this.getUsuarios();
    }
}
