package vn.test.thuchanh12_1_2025.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {



    @NotEmpty(message = "Username cannot be empty")
    private String username;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    private String password;
    @NotEmpty(message = "Role cannot be empty")
    private String role;
    @NotNull
    private Integer departmentId;

}
