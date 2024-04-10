package francescoandrisani.u5w2d3.exceptions;
public class NotFound extends RuntimeException {
    public NotFound(int id) {
        super("Elemento con id" + id + " non trovato");
    }
}
