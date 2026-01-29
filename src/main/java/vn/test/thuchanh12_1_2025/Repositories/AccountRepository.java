package vn.test.thuchanh12_1_2025.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.test.thuchanh12_1_2025.Models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);


    boolean existsByUsername(String username);


    Optional<Account> findByEmail(String email);

//    Page<Account> findAllAccount( Pageable pageable);


}
