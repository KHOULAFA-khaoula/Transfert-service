package com.example.clientservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : ayoubrhiti
 * @version 1.0
 * @since 10/1/2022
 */
@Data
public class ClientDTO {
    @NotNull(message = "Id must not be null")
    private Long id;

    @NotBlank(message = "FirstName must not be blank")
    private String firstName;
    @NotBlank(message = "LastName must not be blank")
    private String lastName;
}
