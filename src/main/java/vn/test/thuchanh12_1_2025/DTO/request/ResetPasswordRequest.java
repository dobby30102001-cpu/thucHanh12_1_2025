package vn.test.thuchanh12_1_2025.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String username;
    private String newPassword;
    private String otp;
}
