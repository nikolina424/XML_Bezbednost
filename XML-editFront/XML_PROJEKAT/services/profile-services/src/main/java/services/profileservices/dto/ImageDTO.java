package services.profileservices.dto;

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
