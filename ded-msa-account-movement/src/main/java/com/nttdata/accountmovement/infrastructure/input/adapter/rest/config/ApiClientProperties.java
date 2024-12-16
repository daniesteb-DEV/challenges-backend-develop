package com.nttdata.accountmovement.infrastructure.input.adapter.rest.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "http-client.api")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class ApiClientProperties {
    @NotNull
    @Valid
    HttpApiClient clientCustomer;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Validated
    public static class HttpApiClient {
        @NotBlank
        @Size(max = 255)
        private String baseUrl;
    }
}
