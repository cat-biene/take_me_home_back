package de.ait.cohort34.security.auth;

import ait.cohort34.accounting.dao.UserAccountRepository;
import ait.cohort34.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountRepository accountRepository;

    @Autowired
    public CustomUserDetailsService(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountRepository.findById(login)
                .map(UsersDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Manager not found with name: " + login));
    }
}
