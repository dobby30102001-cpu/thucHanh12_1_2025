package vn.test.thuchanh12_1_2025.DTO.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepartment {
    @NotBlank(message = "Department name must not be blank")
    private String name;

    @NotBlank(message = "Department totalmember must not be blank")
    private String totalMember;

    @NotBlank(message = "CreaDate must not be blank")
    private String createDate;

}
