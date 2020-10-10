package pe.yeilinux.dailyfood.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.yeilinux.dailyfood.domain.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String error = exception.getParameterName() + " falta un parametro";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception, WebRequest request) {
        String error = exception.getName() + " deberia ser " + exception.getRequiredType().getName();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMethod());
        builder.append(" el metodo no esta soportado.Los metodos que estan soportados son :) (: ");

        exception.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, exception.getLocalizedMessage(), builder.toString());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();

        builder.append(exception.getContentType());
        builder.append(" este tipo de medio no esta soportado.Los medios soportados son: ");

        exception.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception.getLocalizedMessage(),
                builder.substring(0, builder.length() - 2));

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(),
                "Ocurrio un error general.");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        return processFieldError(error);
    }

    private ResponseEntity<Object> processFieldError(FieldError error) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error.getField(), "Error en el campo "+error.getField());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
