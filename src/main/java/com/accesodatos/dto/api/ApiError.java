package com.accesodatos.dto.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiError {

    @Schema(
            example = "404",
            description = "status error code"
    )
    @NonNull
    HttpStatus status;

    @NonNull
    private String message;

    private List<String> errors;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd/MM/yy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiError(final @NonNull HttpStatus status, final @NonNull String message, final List<String> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(final @NonNull HttpStatus status, final @NonNull String message, final String error) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }
}
