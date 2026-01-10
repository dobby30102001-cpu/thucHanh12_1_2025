package vn.test.thuchanh12_1_2025.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.test.thuchanh12_1_2025.DTO.request.*;
import vn.test.thuchanh12_1_2025.Models.Account;

import java.util.List;

public interface AccountService {


    Account addAccount(CreateAccountRequest account);

    Account updateAccount(Integer id, UpdateAccountRequest updateAccountRequest);

    void deleteMultipleAccounts(List<Integer> ids);

    Page<Account> getAccountByFilter(GetAccountRequest request, Pageable pageable);


    Account addAccountToDepartment(CreateAccountRequest createAccountRequest);

    Boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest);

    Boolean resetPassword(ResetPasswordRequest resetPasswordRequest);

    Boolean lockAccount(Integer id, AccountLockRequest lockAccountRequest);

    Boolean unlockAccount(Integer id);


}
