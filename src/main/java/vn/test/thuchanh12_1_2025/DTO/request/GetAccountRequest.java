package vn.test.thuchanh12_1_2025.DTO.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAccountRequest {
    private String username;
    private String FirstName;
    private String LastName;
    private String role;
}
