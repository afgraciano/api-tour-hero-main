package co.edu.udea.api.excepciones;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class ControladorDeExcepciones extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ControladorDeExcepciones.class);

    @ExceptionHandler({ ExcepcionesNegocio.class })
    protected ResponseEntity handleBusinessException(HttpServletRequest request, ExcepcionesNegocio ex) {
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExcepcionDatosNoEncontrados.class)
    protected ResponseEntity handleEntityNotFound(HttpServletRequest request, ExcepcionDatosNoEncontrados ex) {
        logger.error("error: {}, resource: {}", ex.getMessage(), request.getRequestURL().toString());
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity handleUnhandledException(
            HttpServletRequest request, Throwable ex) {
        // TODO: Enviar correo electrónico con error no manejado
        logger.error(request.getRequestURL().toString(), ex);
        return new ResponseEntity<>("No se ha podido procesar su solicitud. Contacte al administrador.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}