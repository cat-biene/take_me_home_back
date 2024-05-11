package ait.cohort34.accounting.service;

import ait.cohort34.accounting.dto.*;

import java.util.List;

public interface UserAccountService {
    UserDto register(UserRegisterDto userRegisterDto);

    List<UserDto> getUsers();

    UserDto getUser(String login);

    UserDto removeUser(String login);

    UserDto updateUser(String login, UserEditDto userEditDto);

    boolean changeRole(String login);

    void changePassword(String login, String newPassword);

    String getTelegram(String login);
}
