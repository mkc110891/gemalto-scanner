package com.interviewpractice.gateway.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>Created By Mayank Chauhan on 2/8/21 - 6:25 PM</p>
 */

@Getter
@Setter
@Validated
public class CustomerModel {
    @NotNull
    @NotBlank
    String firstName;

    @NotNull
    @NotBlank
    String lastName;
    String id;
}
