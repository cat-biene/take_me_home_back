package de.ait.cohort34.accounting.controller;

import ait.cohort34.accounting.dto.UserDto;
import ait.cohort34.accounting.dto.UserEditDto;
import ait.cohort34.accounting.dto.UserRegisterDto;
import ait.cohort34.accounting.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {
    final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
        return userAccountService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto login(Principal principal) {
        return userAccountService.getUser(principal.getName());
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userAccountService.getUsers();
    }

    @GetMapping("/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userAccountService.getUser(login);
    }

    @DeleteMapping("/user/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userAccountService.removeUser(login);
    }

    @PutMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UserEditDto userEditDto) {
        return userAccountService.updateUser(login, userEditDto);
    }

    @PutMapping("/user/{login}/role")
    public boolean changeRole(@PathVariable String login) {
        return userAccountService.changeRole(login);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userAccountService.changePassword(principal.getName(), newPassword);
    }
    @GetMapping("/user/{login}/telegram")
    public String getTelegram(@PathVariable String login){
        return userAccountService.getTelegram(login);
    }
}
