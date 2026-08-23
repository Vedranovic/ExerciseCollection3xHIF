package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.PlayerRepository;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepository();
    }

    public void initDB() {
        playerRepository.createTable();
    }
}
