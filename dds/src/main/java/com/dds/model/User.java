package com.dds.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
@Data
@Entity
public class User {
    @Id
    private Long Id;

    @NotBlank(message = "First Name must not be blank!")
    @Size(min=3, message = "First Name must be at least 3 characters long!")
    private String firstName;

    @NotBlank(message = "Last Name must not be blank!")
    @Size(min=3, message = "Last Name must be at least 3 characters long!")
    private String lastName;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Please provide a valid email!")
    private String email;

    @NotBlank(message = "Number must not be blank!")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits!")
    private String number;

    @NotBlank(message = "Location must not be blank!")
    private String location;
}
