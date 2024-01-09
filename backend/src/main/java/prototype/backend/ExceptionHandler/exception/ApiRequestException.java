package prototype.backend.ExceptionHandler.exception;

import java.util.logging.Logger;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message){
        super(message);
    }
    public ApiRequestException(String message, Throwable cause){
        super(message, cause);
    }
    public ApiRequestException(Logger logger, String message){
        super(message);
        logger.warning(message);
    }
}
