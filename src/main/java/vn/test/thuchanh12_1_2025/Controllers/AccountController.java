package vn.test.thuchanh12_1_2025.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.test.thuchanh12_1_2025.Common.BaseResponse;
import vn.test.thuchanh12_1_2025.DTO.request.*;
import vn.test.thuchanh12_1_2025.Models.Account;
import vn.test.thuchanh12_1_2025.Services.AccountService;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


    @PostMapping("/api/users")
//  or  @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BaseResponse<Account>> createAccount(@RequestBody @Valid CreateAccountRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(accountService.addAccount(user),
                        "Create user successfully"));
    }



    // update account
    @PutMapping("/api/users/{id}")
    public ResponseEntity<BaseResponse<Account>> updateAccount(@PathVariable Integer id, @RequestBody @Valid UpdateAccountRequest userUpdate) {
        return ResponseEntity.ok(new BaseResponse<>(accountService.updateAccount(id, userUpdate), "Update user successfully"));
    }

    // delete multiple accounts by ids
    @DeleteMapping("/api/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<BaseResponse<Void>> deleteMultipleAccounts(@RequestBody DeleteRequest request) {
        accountService.deleteMultipleAccounts(request.getIds());
        return ResponseEntity.ok(new BaseResponse<>(null, "Delete accounts successfully"));

    }

    //    // api lấy danh sách account có phân trang(nhiều điệu kiên searching sư dụng Specification)
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<Account>>> getAccountByFilter(@ModelAttribute  GetAccountRequest request, Pageable pageable) {
        Page<Account> getAccount = accountService.getAccountByFilter(request, pageable);
        return ResponseEntity.ok(new BaseResponse<>(getAccount, "Get accounts successfully"));

    }

    //   api add account vào department
    @PostMapping("create-account-with-department")
    public ResponseEntity<BaseResponse<Account>> createAccountToDepartment(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        Account createdAccount = accountService.addAccountToDepartment(createAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(createdAccount, "Create account with department successfully"));
    }


    // Quên mật khẩu
    // 1. request: username  -> response TRUE/FALSE
    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse<String>> forGotPassWord(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        String token = accountService.forgotPassword(forgotPasswordRequest.getEmail());
        return ResponseEntity.ok(new BaseResponse<>(token, "Reset token created"));
    }

    // 2. verify token và đặt lại mật khẩu mới
    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse<Void>> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
         accountService.resetPassword(resetPasswordRequest.getToken(),resetPasswordRequest.getNewPassword());
        return ResponseEntity.ok(new BaseResponse<>( null,"Password reset success"));
    }


    // lock& unlock account

    @PatchMapping("/{id}/lock")
    public ResponseEntity<BaseResponse<Boolean>> lockAccount(@PathVariable Integer id, @Valid @RequestBody AccountLockRequest accountLockRequest) {
        Boolean result = accountService.lockAccount(id, accountLockRequest);
        return ResponseEntity.ok(new BaseResponse<>(result, "Account locked successfully"));
    }

    @PatchMapping("/{id}/unlock")
    public ResponseEntity<BaseResponse<Boolean>> unlockAccount(@PathVariable Integer id) {
        Boolean unlockAccount = accountService.unlockAccount(id);
        return ResponseEntity.ok(new BaseResponse<>(unlockAccount, "Account unlocked successfully"));
    }


}




