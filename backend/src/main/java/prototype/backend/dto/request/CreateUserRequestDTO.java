package prototype.backend.dto.request;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO extends LoginRequestDTO{
    private String email;
    private String password;
}
