package at.htlkaindorf._03_managementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubPlayer {
    private Club club;
    private Player player;
    private LocalDate startDate;
    private LocalDate endDate;
}
