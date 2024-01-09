package prototype.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

    private String error;
    private int statusCode;
    public ResponseDTO(RuntimeException e){
        this.statusCode = 404;
        this.error = e.getMessage();
    }
}
