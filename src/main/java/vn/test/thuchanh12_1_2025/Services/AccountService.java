package vn.test.thuchanh12_1_2025.Services;

import vn.test.thuchanh12_1_2025.DTO.request.CreateAccountRequest;
import vn.test.thuchanh12_1_2025.Models.Account;

public interface AccountService {


    Account addAccount(CreateAccountRequest account);
}
