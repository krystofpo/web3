package krystof.business;

/**
 * Created by polansky on 25.12.2017.
 */
public class NoteHandlerException extends IllegalArgumentException{

    public NoteHandlerException() {
        super();
    }

    public NoteHandlerException(String s) {
        super(s);
    }

    public NoteHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteHandlerException(Throwable cause) {
        super(cause);
    }
}
