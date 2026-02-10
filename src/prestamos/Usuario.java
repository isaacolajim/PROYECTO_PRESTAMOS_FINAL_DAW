package prestamos;

import java.time.LocalDate;

public class Usuario extends Excepciones {
    private String nombre;
    private String email;
    private String numeroSocio;
    private LocalDate fechaRegistro;
    private boolean sancionado;
    private LocalDate fechaFinSancion;

    public Usuario(String nombre, String email, String numeroSocio, LocalDate fechaRegistro)throws UsuarioInvalidoException{
        if(email==null||!email.contains("@")||!email.contains(".")){
            throw new UsuarioInvalidoException("Debe tener  @ y .");
        }
        if (numeroSocio == null || !numeroSocio.matches("^SOC\\d{5}$")) {
            throw new UsuarioInvalidoException("Número de socio inválido El formato debe ser: SOC00000");
        }

        this.nombre=nombre;
        this.email=email;
        this.numeroSocio=numeroSocio;
        this.fechaRegistro=fechaRegistro;
    }
    public void sancionar(){
        this.sancionado =true;
    }

    public void levantarSancion(){
        this.sancionado=false;
    }

    public boolean estaSancionado(){
        if ( fechaFinSancion!=null && LocalDate.now().isAfter(fechaFinSancion)){
            return false;
        }

        return sancionado;
    }

    @Override
    public String toString(){
        return "Nombre: "+this.nombre+" Email: "+this.email+" Número de Socio: "+this.numeroSocio+
                " Fecha de registro: "+this.fechaRegistro;
    }

}
