package de.ait.cohort34.security;

import ait.cohort34.accounting.dao.UserAccountRepository;
import ait.cohort34.accounting.model.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

// UserDetailsService- встроенный интерфейс в спринг секьюрити для работы с учетными записями пользователей
// не забываем про аннотацию service, чтобы поместить класс в аппликационный контекст
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserAccountRepository userAccountRepository;

// Этот метод загружает данные пользователя из базы данных по его имени пользователя
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

// поиск пользователя в базе данных по его имени пользователя
        UserAccount userAccount = userAccountRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username));
// Получаем роль пользователя
        String userRole = "ROLE_" + userAccount.getRole().name();

// Создаем коллекцию с одной ролью пользователя
        Collection<String> authorities = Collections.singleton(userRole);

// Создаем объект UserDetails из Spring Security, который представляет аутентифицированного пользователя.
// Он включает имя пользователя (username), пароль пользователя (password) и список его ролей (authorities).
// Для каждой роли пользователя создается строка, начинающаяся с "ROLE_", как требует Spring Security
        return new User(username, userAccount.getPassword(), AuthorityUtils.createAuthorityList(authorities));
    }
}
