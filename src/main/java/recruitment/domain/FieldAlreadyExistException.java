package recruitment.domain;

public class FieldAlreadyExistException extends Exception {
    public FieldAlreadyExistException(String msg) {
        super(msg);
    }
}
