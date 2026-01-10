package vn.test.thuchanh12_1_2025.DTO.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
public class UpdateDepartmentRequest {
    private Integer TotalMember;

    private Instant createdDate;
}
