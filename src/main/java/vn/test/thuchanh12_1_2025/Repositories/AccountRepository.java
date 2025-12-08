package vn.test.thuchanh12_1_2025.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.thuchanh12_1_2025.Models.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
