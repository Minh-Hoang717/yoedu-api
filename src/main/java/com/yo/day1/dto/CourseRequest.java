// dto/CourseRequest.java
package com.yo.day1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CourseRequest(
        @NotBlank String courseCode,
        @NotBlank String name,
        String description,
        @Positive double tuitionFee,
        @PositiveOrZero int totalSession,
        byte isActive
) {}