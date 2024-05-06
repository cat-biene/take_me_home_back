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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException(username));
        String userRole = "ROLE_" + userAccount.getRole().name();
        Collection<String> authorities = Collections.singleton(userRole);
        return new User(username, userAccount.getPassword(), AuthorityUtils.createAuthorityList(authorities));
    }
}
