package vn.test.thuchanh12_1_2025.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.thuchanh12_1_2025.Models.OTP;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
    OTP findByUsernameAndOtp( String username, String otp);
}
