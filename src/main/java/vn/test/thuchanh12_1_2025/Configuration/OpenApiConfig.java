package vn.test.thuchanh12_1_2025.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account Management API")
                        .version("1.0")
                        .description("REST API quản lý account: CRUD, lock/unlock, forgot & reset password"));
    }
}
