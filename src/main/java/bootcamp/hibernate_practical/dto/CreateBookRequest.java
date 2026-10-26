package bootcamp.hibernate_practical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CreateBookRequest {
    @NotBlank(message = "Title shouldn't be empty nor null")
    private String title;
    @NotBlank(message = "Author shouldn't be empty nor null")
    private String author;
    private String genre;
    @Positive
    private int publicationYear;
}
