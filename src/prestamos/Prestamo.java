package prestamos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo extends Excepciones {
    private String codigoLibro;
    private String tituloLibro;
    private Usuario socio;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;

    public Prestamo(String codigoLibro, Usuario socio, String tituloLibro,
                    LocalDate fechaPrestamo)throws PrestamoInvalidoException{

        if(codigoLibro==null||!codigoLibro.matches("^LIB\\d{4}$")){
            throw  new PrestamoInvalidoException("EL FORMATO DEL CODIGO DEBE SER LIB0001 ");
        }
        if(tituloLibro==null){
            throw new PrestamoInvalidoException("NO HAS INTRODUCIDO EL TITULO DEL LIBRO");
        }
        if(socio==null){
            throw new PrestamoInvalidoException("NO HAS INTRODUCIDO NINGUN USUARIO");
        }
        if(fechaPrestamo==null){
            throw new PrestamoInvalidoException("NO HAS INTRODUCIDO LA FECHA DE PRESTAMO");
        }
        this.codigoLibro=codigoLibro;
        this.socio=socio;
        this.tituloLibro=tituloLibro;
        this.fechaPrestamo=fechaPrestamo;
        this.fechaDevolucionPrevista = fechaPrestamo.plusDays(14);

    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public LocalDate getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public Usuario getSocio (){
        return socio;
    }

    public void registrarDevolucion(LocalDate devolucion)throws PrestamoInvalidoException{
        if(devolucion==null||devolucion.isBefore(LocalDate.now())){
            throw new PrestamoInvalidoException("NO ES POSIBLE REALIZAR " +
                    "UN PRESTAMO EN ESTAS FECHAS");
        }
        fechaDevolucionReal=devolucion;
    }

    public int calcularDiasRetraso(){
        if(fechaDevolucionReal.isAfter(fechaDevolucionPrevista)){
            long retraso = ChronoUnit.DAYS.between(fechaDevolucionReal,fechaDevolucionPrevista);
            return (int)retraso;
        }
        else{
            return 0;
        }
    }

    public boolean estaRetrasado(){
        if(fechaDevolucionReal.isAfter(fechaDevolucionPrevista)){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
        return "Codigo libro: "+this.codigoLibro+" Usuario"+this.socio+" Titulo"+this.tituloLibro+
                " Fecha prestamo"+this.fechaPrestamo;
    }

}

