package com.devops.dockerization.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
public class UserAddressVo implements Serializable {

    @NotEmpty(message = "not.empty.field.validation.error")
    private String locality;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String street;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String city;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String country;
}
