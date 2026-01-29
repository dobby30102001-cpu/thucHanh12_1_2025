package vn.test.thuchanh12_1_2025.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.test.thuchanh12_1_2025.Common.BaseResponse;
import vn.test.thuchanh12_1_2025.DTO.request.ForgotPasswordRequest;
import vn.test.thuchanh12_1_2025.DTO.request.ResetPasswordRequest;
import vn.test.thuchanh12_1_2025.Services.AccountService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;

    @PostMapping("/forgot-password")
    public ResponseEntity<BaseResponse<String>> forgot(@RequestBody ForgotPasswordRequest req) {
        String token = accountService.forgotPassword(req.getEmail());
        return ResponseEntity.ok(new BaseResponse<>(token, "Reset token created"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<BaseResponse<Void>> reset(@Valid @RequestBody ResetPasswordRequest req) {
        accountService.resetPassword(req.getToken(), req.getNewPassword());
        return ResponseEntity.ok(new BaseResponse<>(null, "Password reset success"));
    }
}
