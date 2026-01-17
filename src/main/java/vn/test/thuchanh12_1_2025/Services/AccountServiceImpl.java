package vn.test.thuchanh12_1_2025.Services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.test.thuchanh12_1_2025.DTO.request.*;
import vn.test.thuchanh12_1_2025.Exception.BusinessException;
import vn.test.thuchanh12_1_2025.Models.Account;
import vn.test.thuchanh12_1_2025.Models.AccountStatus;
import vn.test.thuchanh12_1_2025.Models.Department;
import vn.test.thuchanh12_1_2025.Repositories.AccountRepository;
import vn.test.thuchanh12_1_2025.Repositories.DepartmentRepository;
import vn.test.thuchanh12_1_2025.Repositories.OtpRepository;
import vn.test.thuchanh12_1_2025.Specification.AccountSpecification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import vn.test.thuchanh12_1_2025.Models.PasswordResetToken;
import vn.test.thuchanh12_1_2025.Repositories.PasswordResetTokenRepository;


import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    public final AccountRepository accountRepository;
    public final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;




    @Override
    public Account addAccount(CreateAccountRequest createAccountRequest) {

        Account existedAccount = accountRepository.findByUsername(createAccountRequest.getUsername());
        if (existedAccount != null) {
            throw new BusinessException("Account already exists");
        }
        if (createAccountRequest.getDepartmentId() != null) {
            Optional<Department> existedDepartment = departmentRepository.findById(createAccountRequest.getDepartmentId());
            if (existedDepartment.isEmpty()) {
                throw new BusinessException("Department not found");
            }
        }
        Account account = modelMapper.map(createAccountRequest, Account.class);
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setId(null);
        return accountRepository.save(account);

    }

    @Override
    public Account updateAccount(Integer id, UpdateAccountRequest updateAccountRequest) {
        Optional<Account> existedAccount = accountRepository.findById(id);
        if (existedAccount.isEmpty()) {
            throw new BusinessException("Account not found");
        }
        Account accountUpdate = existedAccount.get();
        accountUpdate.setFirstName(updateAccountRequest.getFirstName());
        accountUpdate.setLastName(updateAccountRequest.getLastName());
        accountUpdate.setUsername(updateAccountRequest.getUsername());
        return accountRepository.save(accountUpdate);

    }

    @Override
    public void deleteMultipleAccounts(List<Integer> ids) {
        List<Account> existedAccount = accountRepository.findAllById(ids);
        if (existedAccount.isEmpty()) {
            throw new BusinessException("Account not found");
        }

        accountRepository.deleteAllById(ids);
    }

    @Override
    public Page<Account> getAccountByFilter(GetAccountRequest request, Pageable pageable) {
        Specification<Account> specification =
                (root, query, cb) -> cb.conjunction();
        if (request.getUsername() != null) {
            specification = specification.and(AccountSpecification.hasUsername(request.getUsername()));
        }
        if (request.getFirstName() != null) {
            specification = specification.and(AccountSpecification.hasFirstName(request.getFirstName()));
        }
        if (request.getLastName() != null) {
            specification = specification.and(AccountSpecification.hasLastName(request.getLastName()));

        }
        return accountRepository.findAll(specification, pageable);
    }

    @Override
    public Account addAccountToDepartment(CreateAccountRequest createAccountRequest) {
        Optional<Department> existedDepartment = departmentRepository.findById(createAccountRequest.getDepartmentId());
        if (existedDepartment.isEmpty()) {
            throw new BusinessException("Department not found");
        }

        if (accountRepository.existsByUsername(createAccountRequest.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        Account account = modelMapper.map(createAccountRequest, Account.class);
        account.setId(null);
        account.setUsername(createAccountRequest.getUsername());
        account.setPassword(passwordEncoder.encode(createAccountRequest.getPassword()));
        account.setFirstName(createAccountRequest.getFirstName());
        account.setLastName(createAccountRequest.getLastName());
        account.setRole(createAccountRequest.getRole());
        account.setDepartmentId(createAccountRequest.getDepartmentId());
        return accountRepository.save(account);
    }


    @Transactional
    public String forgotPassword(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // tạo token
        String token = UUID.randomUUID().toString().replace("-", "");

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .account(account)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .usedAt(null)
                .build();

        passwordResetTokenRepository.save(resetToken);
        return token;
    }
//    public Boolean forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
//    Account existedAccount = accountRepository.findByUsername(forgotPasswordRequest.getUsername());
//        if (existedAccount == null) {
//            throw new BusinessException("Account not found");
//        }
//        Random random = new Random();
//        Integer otp = random.nextInt(1000000);
//
//        OTP newOTP = new OTP();
//        newOTP.setUsername(forgotPasswordRequest.getUsername());
//        newOTP.setOtp(otp.toString());
//
//        OTP createdOTP = otpRepository.save(newOTP);
//        log.info("OTP for username {}: {}", forgotPasswordRequest.getUsername(), createdOTP.getOtp());
//        return true;
//    }

//    @Override
//    public Boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
//        OTP existedOTP = otpRepository.findByUsernameAndOtp(resetPasswordRequest.getUsername(), resetPasswordRequest.getOtp());
//        if (existedOTP == null) {
//            throw new BusinessException("Invalid OTP or username");
//        }
//        Account existedAccount = accountRepository.findByUsername(resetPasswordRequest.getUsername());
//        if (existedAccount == null) {
//            throw new BusinessException("Account not found");
//        }
//        existedAccount.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
//        accountRepository.save(existedAccount);
//        otpRepository.delete(existedOTP);
//        return true;
//    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.isUsed()) {
            throw new RuntimeException("Token already used");
        }
        if (resetToken.isExpired()) {
            throw new RuntimeException("Token expired");
        }

        Account account = resetToken.getAccount();
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);

        resetToken.setUsedAt(LocalDateTime.now());
        passwordResetTokenRepository.save(resetToken);

    }






    @Override
    public Boolean lockAccount(Integer id, AccountLockRequest lockAccountRequest) {

        Optional<Account> existedAccount = accountRepository.findById(id);
        if (existedAccount.isEmpty()) {
            throw new BusinessException("Account not found");
        }

        Account account = existedAccount.get();

        // kiem tra account da bi lock chua
        if (account.getStatus() == AccountStatus.LOCKED) {
            throw new BusinessException("Account is already LOCKED");
        }

        account.setStatus(AccountStatus.LOCKED);
        account.setLockedAt(LocalDateTime.now());
        account.setLockReason(lockAccountRequest.getReason());

        accountRepository.save(account);

        return true;
    }


    @Override
    public Boolean unlockAccount(Integer id) {

        Optional<Account> existedAccount = accountRepository.findById(id);
        if (existedAccount.isEmpty()) {
            throw new BusinessException("Account not found");
        }

        Account account = existedAccount.get();

        if (account.getStatus() == AccountStatus.ACTIVE) {
            throw new BusinessException("Account is already ACTIVE");
        }

        account.setStatus(AccountStatus.ACTIVE);
        account.setLockedAt(null);
        account.setLockReason(null);

        accountRepository.save(account);

        return true;
    }

}






















