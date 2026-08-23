package at.htlkaindorf._03_managementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {
    private Long id;
    private String name;

    public static Club toClub(String line) {
        String[] tokens = line.split(",");

        return Club.builder()
                .id(Long.parseLong(tokens[0]))
                .name(tokens[1])
                .build();
    }

    @Override
    public String toString() {
        return name;
    }
}
