package prestamos;

import java.time.LocalDate;

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

    /*public boolean devolverLibro(String codigoLibro, LocalDate fechaDevolucion){

    }*/

}
