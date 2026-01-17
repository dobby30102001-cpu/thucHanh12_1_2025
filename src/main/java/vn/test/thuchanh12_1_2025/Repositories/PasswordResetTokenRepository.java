package vn.test.thuchanh12_1_2025.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.thuchanh12_1_2025.Models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
}
