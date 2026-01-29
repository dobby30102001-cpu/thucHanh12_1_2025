package vn.test.thuchanh12_1_2025.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.test.thuchanh12_1_2025.Models.Account;
import vn.test.thuchanh12_1_2025.Repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new ApplicationContextException("User not found with username: " + username);

        }
        //  tránh null role
        String role = account.getRole() == null ? "USER" : account.getRole();

        return  User.withUsername(account.getUsername())   //  dùng username từ DB cho an toàn
                .password(account.getPassword())          // BCrypt hash
                .roles(role)                              // "ADMIN" -> ROLE_ADMIN
                .build();
    }


}
