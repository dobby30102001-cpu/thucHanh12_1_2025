package vn.test.thuchanh12_1_2025.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.test.thuchanh12_1_2025.Common.BaseResponse;
import vn.test.thuchanh12_1_2025.DTO.request.CreateAccountRequest;
import vn.test.thuchanh12_1_2025.Models.Account;
import vn.test.thuchanh12_1_2025.Services.AccountService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;


//    @PostMapping("/users")
//    public ResponseEntity<BaseResponse<Account>> createUser(@RequestBody @Valid CreateAccountRequest user) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(accountService.addAccount(user), "Create user successfully"));
//    }

    @PostMapping("/users")
//  or  @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BaseResponse<Account>> createUser(@RequestBody @Valid CreateAccountRequest user) {
        System.out.println("=== CONTROLLER CALLED ===");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(accountService.addAccount(user),
                        "Create user successfully"));
    }





}



