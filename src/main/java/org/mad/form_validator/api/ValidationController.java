package org.mad.form_validator.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.mad.form_validator.dto.RequestDTO;
import org.mad.form_validator.models.ErrorResponse;
import org.mad.form_validator.services.registrationService.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api")
public class ValidationController {
    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);

    private final ValidationService validationService;

    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }


    /**
     * Endpoint destiné à la validation d'un ensemble de champs d'un formulaire par exemple.
     *
     * @param requestDTO
     * @return
     */

    @Tag(name = "Validation of a form", description = "Validating a set of fields in a form")
    @Operation(
            summary = "valid fields",
            description = "checks the validity of the fields.",
            operationId = "validatedFields"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "validation successful",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema( )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request - invalid fields.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "length less than 8",
                                            value = """
                                                        {
                                                         "status": 400,
                                                          "error": "Validation Failed",
                                                         "message": "Password must contain at least 8 characters",
                                                         "path": "/api/validate",
                                                         "timestamp": "2025-06-21T17:45:00"
                                                         }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Missing first or last name",
                                            value = """
                                                        {
                                                         "status": 400,
                                                          "error": "Validation Failed",
                                                         "message": "Missing first or last name",
                                                         "path": "/api/validate",
                                                         "timestamp": "2025-05-21T17:45:00"
                                                         }
                                                    """
                                    ),
                                          
                            }
                    )
            )
    })
    @PostMapping("/validate")
    public ResponseEntity<String> validate(@RequestBody @Valid RequestDTO requestDTO) {
        logger.info("Init validate from controller");

        validationService.validate(requestDTO);

        return ResponseEntity.ok("validation successful");
    }
}
