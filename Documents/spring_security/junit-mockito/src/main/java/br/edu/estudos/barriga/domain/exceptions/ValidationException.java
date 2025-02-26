package br.edu.estudos.barriga.domain.exceptions;

public class ValidationException extends RuntimeException{

    private static final long serialversionUID = -2739361004520222002L;

    public ValidationException(String message) {
        super(message);
    }
}
