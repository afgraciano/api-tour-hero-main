package co.edu.udea.api.excepciones;

/**
 * Excepción abstracta genérica creada para identificar todas las excepciones.
 */
public class ExcepcionesGeneralesRuntime extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String translationKey;


    public ExcepcionesGeneralesRuntime(String message) {
        super(message);
        this.message = message;
    }

    public ExcepcionesGeneralesRuntime(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }


    public ExcepcionesGeneralesRuntime(String message, String translationKey) {
        super(message);
        this.message = message;
        this.translationKey = translationKey;
    }


    public String getTranslationKey() {
        return translationKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
