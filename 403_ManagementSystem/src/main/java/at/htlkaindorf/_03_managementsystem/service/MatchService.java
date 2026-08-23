package at.htlkaindorf._03_managementsystem.service;

import at.htlkaindorf._03_managementsystem.repository.MatchRepository;

public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService() {
        this.matchRepository = new MatchRepository();
    }

    public void initDB() {
        matchRepository.createTable();
    }
}
