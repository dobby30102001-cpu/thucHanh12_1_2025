package vn.test.thuchanh12_1_2025.Services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.test.thuchanh12_1_2025.DTO.request.CreateAccountRequest;
import vn.test.thuchanh12_1_2025.Exception.BusinessException;
import vn.test.thuchanh12_1_2025.Models.Account;
import vn.test.thuchanh12_1_2025.Models.Department;
import vn.test.thuchanh12_1_2025.Repositories.AccountRepository;
import vn.test.thuchanh12_1_2025.Repositories.DepartmentRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    public final AccountRepository accountRepository;
    public final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final  PasswordEncoder passwordEncoder;


    @Override
    public Account addAccount(CreateAccountRequest createAccountRequest) {

        Account existedAccount = accountRepository.findByUsername(createAccountRequest.getUsername());
        if (existedAccount != null) {
            throw new BusinessException("Account already exists");
        }
        Optional<Department> existedDepartment = departmentRepository.findById(createAccountRequest.getDepartmentId());
        if (existedDepartment.isEmpty()) {
            throw new BusinessException("Department not found");
        }
        Account account = modelMapper.map(createAccountRequest, Account.class);
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setId(null);
        return accountRepository.save(account);

    }
}
