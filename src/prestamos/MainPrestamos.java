package prestamos;

import java.time.LocalDate;

public class MainPrestamos {
    public static void main(String[] args)throws Excepciones.UsuarioInvalidoException {
        Usuario u1 = new Usuario("Isaac","isaac@gmail.com","SOC00123",
                LocalDate.of(2000,12,12));
        u1.sancionar();
        System.out.println(u1.toString());
        System.out.println(u1.estaSancionado());
        u1.levantarSancion();
        System.out.println(u1.estaSancionado());
    }
}
