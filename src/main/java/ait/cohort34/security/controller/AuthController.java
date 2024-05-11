package ait.cohort34.security.controller;

import ait.cohort34.accounting.dto.UserAuthDto;
import ait.cohort34.accounting.dto.UserRegisterDto;
import ait.cohort34.accounting.model.UserAccount;
import ait.cohort34.security.dto.RefreshRequestDto;
import ait.cohort34.security.dto.TokenResponseDto;
import ait.cohort34.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    final private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody UserAuthDto userAccount) {
        try{
            TokenResponseDto responseDto=authService.login(userAccount);
            return ResponseEntity.ok(responseDto);
        }catch (AuthenticationException e){
            TokenResponseDto responseDto= new TokenResponseDto(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(@RequestBody RefreshRequestDto request) throws AuthenticationException {
        TokenResponseDto accessToken=authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(accessToken);
    }
}
