package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.Game;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class TeamDao {
    public final String name;
    public final List<Game> games;

    public TeamDao(Team team) {
        this.name = team.getName();
        this.games = team.getGames();
    }
}
