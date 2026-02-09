public class Excepciones extends  Exception {
    class UsuarioInvalidoException extends Exception {
        public UsuarioInvalidoException(String message) { super(message); }
    }
}
