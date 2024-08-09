package org.gradle.homework.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String title;
    private String description;
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime publicationYear;
}
