package recruitment.domain;

/**
 * Exception thrown when a field is already registered to a user
 */
public class FieldAlreadyExistException extends Exception {
    public final String ERROR_TYPE;
    public FieldAlreadyExistException(String msg,String type) {
        super(msg);
        this.ERROR_TYPE = type;
    }


}
