package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.ClubRepository;

public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService() {
        this.clubRepository = new ClubRepository();
    }

    public void initDB() {
        clubRepository.createTable();
    }
}
