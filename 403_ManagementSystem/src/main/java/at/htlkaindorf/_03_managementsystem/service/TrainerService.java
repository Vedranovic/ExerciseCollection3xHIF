package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.TrainerRepository;

public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService() {
        this.trainerRepository = new TrainerRepository();
    }

    public void initDB() {
        trainerRepository.createTable();
    }
}
