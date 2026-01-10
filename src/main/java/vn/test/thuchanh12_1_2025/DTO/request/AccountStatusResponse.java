package vn.test.thuchanh12_1_2025.DTO.request;

import java.time.LocalDateTime;

public class AccountStatusResponse {
    private Long id;
    private String status;
    private LocalDateTime lockedAt;
    private String lockReason;
}
