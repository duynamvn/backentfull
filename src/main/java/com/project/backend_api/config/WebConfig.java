package com.project.backend_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Cho phép tất cả các yêu cầu đến các điểm cuối bắt đầu bằng /api/
                .allowedOrigins("http://localhost:3000") // Thay đổi địa chỉ này theo ứng dụng front-end của bạn
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Cho phép các phương thức HTTP nào
                .allowedHeaders("*") // Cho phép tất cả các header
                .allowCredentials(true); // Cho phép cookies và thông tin xác thực khác
    }
}
