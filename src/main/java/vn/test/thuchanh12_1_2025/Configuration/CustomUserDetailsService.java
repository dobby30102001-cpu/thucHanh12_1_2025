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
        String role = account.getRole();
        if (role == null || role.isBlank()) role = "USER";

// Nếu DB lỡ lưu "ROLE_ADMIN" thì bỏ prefix để dùng .roles()
        if (role.startsWith("ROLE_")) role = role.substring(5);

// Chuẩn hoá để khớp @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        role = role.trim().toUpperCase();

        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(role)   // "ADMIN" -> ROLE_ADMIN
                .build();
    }


}
