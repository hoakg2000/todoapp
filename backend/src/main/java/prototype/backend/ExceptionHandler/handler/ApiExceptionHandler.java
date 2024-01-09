package prototype.backend.ExceptionHandler.handler;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import prototype.backend.ExceptionHandler.exception.ApiRequestException;
import prototype.backend.ExceptionHandler.exception.DataBaseException;
import prototype.backend.ExceptionHandler.exception.NotFoundException;
import prototype.backend.ExceptionHandler.exception.UniqueEntityException;
import prototype.backend.dto.response.ResponseDTO;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiRequestException.class)
    public ResponseEntity<ResponseDTO> handleApiRequestException(ApiRequestException e){
        return new ResponseEntity<>(initExceptionResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UniqueEntityException.class)
    public ResponseEntity<Object> handleUniqueEntityException(UniqueEntityException ex) {
        Logger.getLogger(ApiExceptionHandler.class.getName())
                .error("WTF");
        ResponseDTO responseDTO = initExceptionResponse(ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDTO);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleNotFoundException(NotFoundException e){
        return initExceptionResponse(e);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ResponseDTO> handleDataBaseException(DataBaseException e){
        return new ResponseEntity<>(initExceptionResponse(e), HttpStatus.BAD_REQUEST);
    }

    public ResponseDTO initExceptionResponse(RuntimeException e){
        return new ResponseDTO(e);
    }
}
