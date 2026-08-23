package at.htlkaindorf._03_managementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {
    private Club club;
    private Club enemy;
    private LocalDate startDate;

    public static Match toMatch(String line) {
        String[] tokens = line.split(",");

        if (tokens[0].equals(tokens[1])) {
            System.out.println("A club can't play against itself!");
        }

        return Match.builder()
                .club(Club.builder()
                        .name(tokens[0])
                        .build())
                .enemy(Club.builder()
                        .name(tokens[1])
                        .build())
                .startDate(LocalDate.parse(tokens[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

    @Override
    public String toString() {
        return String.format("%s against %s at %s",
                club.getName(),
                enemy.getName(),
                startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
