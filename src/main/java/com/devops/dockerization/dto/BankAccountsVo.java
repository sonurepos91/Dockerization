package com.devops.dockerization.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BankAccountsVo {

    @NotEmpty(message = "not.empty.field.validation.error")
    private String bankName;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String bankAccountNo;
    @NotEmpty(message = "not.empty.field.validation.error")
    private String ifscCode;

}
