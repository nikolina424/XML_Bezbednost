package services.postservices.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private String name;
    private byte[] content;
}
