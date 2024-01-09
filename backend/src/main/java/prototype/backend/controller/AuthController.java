package prototype.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prototype.backend.dto.request.CreateUserRequestDTO;
import prototype.backend.dto.request.LoginRequestDTO;
import prototype.backend.dto.response.UserResponseDTO;
import prototype.backend.service.IAuthService;
import prototype.backend.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;
    @PostMapping("/{id}")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws Exception {
        return new ResponseEntity<>(authService.login(loginRequestDTO), HttpStatus.OK);
    }

}
