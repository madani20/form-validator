package org.mad.form_validator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    @Email
    @Schema(description = "A valid email", example = "doe.john@gmail.com")
    @NotNull
    private String email;


    @Schema(description = "Password length (min: 8)")
    @NotNull
    private String password;

    @Schema(description = "First name ", example = "John")
    @NotNull
    private String firstName;

    @Schema(description = "Last name ", example = "Doe")
    @NotNull
    private String lastName;

    @Schema(description = "Country", example = "Dallas")
    private String country;

    public RequestDTO() {}

    public RequestDTO(String email, String password, String firstName, String lastName, String country) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }
}
