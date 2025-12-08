package vn.test.thuchanh12_1_2025.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
//    id            INT PRIMARY KEY AUTO_INCREMENT,
//    username      VARCHAR(100) NOT NULL UNIQUE,
//    `password`    VARCHAR(255) NOT NULL,
//    first_name    VARCHAR(100) NOT NULL,
//    last_name     VARCHAR(100) NOT NULL,
//    `role`        VARCHAR(50)  NOT NULL,
//    department_id INT,
//    FOREIGN KEY (department_id)


    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;
    @NotEmpty(message = "Role cannot be empty")
    private String role;


    private Integer departmentId;

}
