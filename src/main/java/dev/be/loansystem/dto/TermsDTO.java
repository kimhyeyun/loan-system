package dev.be.loansystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.time.LocalDateTime;

public class TermsDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request{

        private String name;
        private String termsDetailUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long termsId;
        private String name;
        private String termsDetailUrl;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
