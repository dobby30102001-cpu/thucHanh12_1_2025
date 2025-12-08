package vn.test.thuchanh12_1_2025.Configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMappingConfig {
    @Bean
    protected ModelMapper ModelMapper() {
        return new ModelMapper();
    }
}
