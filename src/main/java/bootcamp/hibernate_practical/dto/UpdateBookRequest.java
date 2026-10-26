package bootcamp.hibernate_practical.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class UpdateBookRequest {
    @NotBlank(message = "Title mustn't be empty or blank")
    private String title;
    @NotBlank(message = "Author mustn't be empty or blank")
    private String author;
    private String genre;
    @Positive
    private int publicationYear;
    @NotNull(message = "Availability should be set either True or False")
    public boolean available;
    @NotNull(message = "Borrowed status should be set either True or False")
    public boolean borrowedStatus;
}
