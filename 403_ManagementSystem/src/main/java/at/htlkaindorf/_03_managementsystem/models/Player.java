package at.htlkaindorf._03_managementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Long clubId;

    public static Player toPlayer(String line) {
        String[] tokens = line.split(",");

        return Player.builder()
                .firstname(tokens[0])
                .lastname(tokens[1])
                .age(Integer.parseInt(tokens[2]))
                .clubId(Long.parseLong(tokens[3]))
                .build();
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + " " + age + " years old";
    }
}
