package cloudNative.mainApi.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import cloudNative.mainApi.enums.Difficulty;

public record QuizDTO(
                @NotNull @Min(1) Integer quantity,
                @NotBlank String subject,
                @NotNull Difficulty difficulty) {

        public QuizDTO {
                if (quantity == null) {
                        quantity = 1;
                }
        }
}
