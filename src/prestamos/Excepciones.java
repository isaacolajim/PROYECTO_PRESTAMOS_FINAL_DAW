package prestamos;

public class Excepciones extends  Exception {
     public class UsuarioInvalidoException extends Exception {
        public UsuarioInvalidoException(String message) { super(message); }
    }
}
