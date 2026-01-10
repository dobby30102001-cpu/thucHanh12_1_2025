package vn.test.thuchanh12_1_2025.DTO.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountLockRequest {
    @Size(max = 255)
    private String reason;
}
