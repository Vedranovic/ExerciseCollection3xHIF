package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.ClubPlayerRepository;

public class ClubPlayerService {
    private final ClubPlayerRepository clubPlayerRepository;

    public ClubPlayerService() {
        this.clubPlayerRepository = new ClubPlayerRepository();
    }

    public void initDB() {
        clubPlayerRepository.createTable();
    }
}
