package at.htlkaindorf._03_managementsystem;

import at.htlkaindorf._03_managementsystem.service.*;

public class Data {
    public static void main(String[] args) {
        ClubPlayerService clubPlayerService = new ClubPlayerService();
        ClubService clubService = new ClubService();
        ClubTrainerService clubTrainerService = new ClubTrainerService();
        MatchService matchService = new MatchService();
        PlayerService playerService = new PlayerService();
        TrainerService trainerService = new TrainerService();

        clubPlayerService.initDB();
        clubService.initDB();
        clubTrainerService.initDB();
        matchService.initDB();
        playerService.initDB();
        trainerService.initDB();
    }
}
