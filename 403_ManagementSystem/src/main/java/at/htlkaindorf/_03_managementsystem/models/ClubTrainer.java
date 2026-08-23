package at.htlkaindorf._03_managementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubTrainer {
    private Club club;
    private Trainer trainer;
    private LocalDate startDate;
    private LocalDate endDate;
}
