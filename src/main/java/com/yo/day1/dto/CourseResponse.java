// dto/CourseResponse.java
package com.yo.day1.dto;

public record CourseResponse(
        long id,
        String courseCode,
        String name,
        String description,
        double tuitionFee,
        int totalSession,
        byte isActive
) {}