package edu.school21.cinema.models.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/*
@JsonIgnoreProperties из библиотеки обработки JSON Джексона, чтобы указать,
что любые свойства, не связанные с этим типом, следует игнорировать.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDto {
    private boolean success;
    @JsonAlias("error-codes")
    private Set<String> errorCodes;
}
