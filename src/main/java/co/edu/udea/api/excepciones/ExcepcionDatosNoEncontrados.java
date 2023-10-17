package co.edu.udea.api.excepciones;

/**
 * Excepción por datos duplicados en la aplicación
 */
public class ExcepcionDatosNoEncontrados extends ExcepcionesGeneralesRuntime {

    private static final long serialVersionUID = 1L;

    public ExcepcionDatosNoEncontrados(String message) {
        super(message);
    }
}
