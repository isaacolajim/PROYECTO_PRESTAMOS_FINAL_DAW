package prestamos;

public class Excepciones extends  Exception {
     public class UsuarioInvalidoException extends Exception {
        public UsuarioInvalidoException(String message) { super(message); }
    }
    public class PrestamoInvalidoException extends Exception{
         public PrestamoInvalidoException (String message){ super(message);}
    }
    public class UsuarioRepetidoException extends Exception{
        public UsuarioRepetidoException (String message){ super(message);}
    }
    public class UsuarioSancionadoException extends Exception{
        public UsuarioSancionadoException (String message){ super(message);}
    }
    public class LibroNoDisponibleException extends Exception{
        public LibroNoDisponibleException(String message){ super(message);}
    }
}
