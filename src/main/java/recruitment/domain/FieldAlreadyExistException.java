package recruitment.domain;

/**
 * Exception thrown when a field is already registered to a user
 */
public class FieldAlreadyExistException extends Exception {
    public FieldAlreadyExistException(String msg) {
        super(msg);
    }
}
