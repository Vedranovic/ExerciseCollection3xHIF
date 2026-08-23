package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Genre {
    private Long id;
    @NonNull
    private String name;
}
