package co.edu.udea.api.excepciones;

public class ExcepcionesNegocio extends ExcepcionesGeneralesRuntime {
    private static final long serialVersionUID = 1L;

    public ExcepcionesNegocio(String message) {
        super(message);
    }

    public ExcepcionesNegocio(String message, String translationKey) {
        super(message, translationKey);
    }
}
