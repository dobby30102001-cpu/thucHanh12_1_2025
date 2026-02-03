package vn.test.thuchanh12_1_2025.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.test.thuchanh12_1_2025.DTO.request.*;
import vn.test.thuchanh12_1_2025.Models.Account;

import java.util.List;

public interface AccountService {

    String login(String username, String password);



    Account getAccountById(Integer id);

    Page<Account> getAllAccounts(Pageable pageable);

    Account addAccount(CreateAccountRequest account);

    Account updateAccount(Integer id, UpdateAccountRequest updateAccountRequest);

    void deleteAccounts(Integer id);

    Page<Account> getAccountByFilter(GetAccountRequest request, Pageable pageable);

    Account addAccountToDepartment(CreateAccountRequest createAccountRequest);

    String forgotPassword(String forgotPasswordRequest);

    void resetPassword(String token, String newPassword);

    Boolean lockAccount(Integer id, AccountLockRequest lockAccountRequest);

    Boolean unlockAccount(Integer id);


}
