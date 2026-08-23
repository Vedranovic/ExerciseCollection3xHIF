package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stars {
    private Long id;
    @NonNull
    private String value;
}
