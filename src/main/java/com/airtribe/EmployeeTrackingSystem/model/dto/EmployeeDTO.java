package com.airtribe.EmployeeTrackingSystem.model.dto;


import com.airtribe.EmployeeTrackingSystem.utils.CreateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.NullableNotBlankDateFormat;
import com.airtribe.EmployeeTrackingSystem.utils.UpdateValidation;
import com.airtribe.EmployeeTrackingSystem.utils.ValidDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

public class EmployeeDTO {
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters", groups = CreateValidation.class)
    @NotBlank(message = "Name should not be empty", groups = CreateValidation.class)
    private String fullName;

    @Email(message = "Invalid email format", groups = {CreateValidation.class, UpdateValidation.class})
    @NotBlank(message = "Email should not be empty", groups = CreateValidation.class)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone Number must only be 10 digits", groups = {CreateValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone Number should not be empty", groups = CreateValidation.class)
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Joining Date should not be empty", groups = CreateValidation.class)
    @ValidDateFormat(groups = CreateValidation.class)
    private String dateOfJoining;

    public EmployeeDTO(String fullName, String email, String phoneNumber, String dateOfJoining) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
    }

    public EmployeeDTO() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}
