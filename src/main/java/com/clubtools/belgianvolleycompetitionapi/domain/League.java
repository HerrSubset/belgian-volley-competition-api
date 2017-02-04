package com.clubtools.belgianvolleycompetitionapi.domain;

import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalTeamDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class League {
    private List<Game> games;

    public League(List<Game> games) {
        this.games = games;
    }

    public List<MinimalTeamDao> getBasicTeamInfo() {
        List<MinimalTeamDao> teamDaos = new ArrayList<>();

        for (Team team : getTeams()) {
            teamDaos.add(new MinimalTeamDao(team));
        }

        return teamDaos;
    }


    public Set<Team> getTeams() {
        HashSet<Team> teams = new HashSet<>();

        for (Game game: games) {
            teams.add(new Team(game.getHomeTeam()));
            teams.add(new Team(game.getAwayTeam()));
        }

        return teams;
    }
}
