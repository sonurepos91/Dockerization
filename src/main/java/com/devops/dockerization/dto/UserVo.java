package com.devops.dockerization.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserVo implements Serializable {

    @NotEmpty(message = "not.empty.field.validation.error")
    private String firstName;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String lastName;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String email;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String phoneNo;
    @NotNull(message = "not.null.field.validation.error")
    private Integer age;

    @Valid
    @NotEmpty(message = "not.empty.field.validation.error")
    private List<UserAddressVo> addresses = new ArrayList<>();
    @Valid
    @NotEmpty(message = "not.empty.field.validation.error")
    private List<BankAccountsVo> bankAccounts = new ArrayList<>();
}
