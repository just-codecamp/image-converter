package io.scode.imageconvertservice.dto;

import io.scode.imageconvertservice.utils.validator.IsAvailableSource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageSource {

    @IsAvailableSource
    private String source;

    @Min(100)
    @Positive
    private Integer width;

    @Min(100)
    @Positive
    private Integer height;

    @NotBlank
    private String Key;

}
