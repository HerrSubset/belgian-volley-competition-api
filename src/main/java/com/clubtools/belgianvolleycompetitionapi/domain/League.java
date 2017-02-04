package com.clubtools.belgianvolleycompetitionapi.domain;

import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalTeamDao;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class League {
    private LeagueId id;
    private List<Game> games;

    public League(LeagueId id, List<Game> games) {
        this.id = id;
        this.games = games;
    }

    public List<MinimalTeamDao> getBasicTeamInfo(String federationAbbreviation) {
        List<MinimalTeamDao> teamDaos = new ArrayList<>();

        for (Team team : getTeams()) {
            teamDaos.add(new MinimalTeamDao(team, federationAbbreviation, id));
        }

        return teamDaos;
    }


    public Set<Team> getTeams() {
        HashSet<Team> teams = new HashSet<>();

        for (Game game: games) {
            teams.add(new Team(game.getHomeTeam(), getTeamGames(game.getHomeTeam())));
            teams.add(new Team(game.getAwayTeam(), getTeamGames(game.getAwayTeam())));
        }

        return teams;
    }

    private List<Game> getTeamGames(String teamName) {
        List<Game> teamGames = new ArrayList<>();

        for (Game game : games) {

            if (game.getHomeTeam().equals(teamName))
                teamGames.add(game);

            else if (game.getAwayTeam().equals(teamName))
                teamGames.add(game);
        }

        return teamGames;
    }

    public Team getTeamBySlug(String teamSlug) {
        for (Team team : getTeams()) {
            if (UrlUtils.sluggify(team.getName()).equals(teamSlug)) return team;
        }
        throw new RuntimeException("Could not find team with slug " + teamSlug + " in league with id " + id.getId());
    }
}
