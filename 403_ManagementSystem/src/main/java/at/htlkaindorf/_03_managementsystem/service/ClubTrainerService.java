package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.ClubTrainerRepository;

public class ClubTrainerService {
    private final ClubTrainerRepository clubTrainerRepository;

    public ClubTrainerService() {
        this.clubTrainerRepository = new ClubTrainerRepository();
    }

    public void initDB() {
        clubTrainerRepository.createTable();
    }
}
