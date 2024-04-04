package com.gravity.fastcommerce.exceptions;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CustomErrorResponse {

    private final int statusCode;
    private final String errorMessage;
    private final String apiPath;
    private final LocalDateTime timestamp;

    private CustomErrorResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.errorMessage = builder.errorMessage;
        this.apiPath = builder.apiPath;
        this.timestamp = builder.timestamp;
    }

    public static class Builder {
        private int statusCode;
        private String errorMessage;
        private String apiPath;
        private LocalDateTime timestamp;

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder apiPath(String apiPath) {
            this.apiPath = apiPath;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CustomErrorResponse build() {
            return new CustomErrorResponse(this);
        }
    }
}
