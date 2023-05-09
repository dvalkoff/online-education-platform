package ru.mirea.valkov.education.platform.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailsRequest {
    private String title;
    private String description;
}
