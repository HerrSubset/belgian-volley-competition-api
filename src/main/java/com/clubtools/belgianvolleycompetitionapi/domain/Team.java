package com.clubtools.belgianvolleycompetitionapi.domain;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class Team {

    private String name;
    private List<Game> games;

    public Team(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public List<Game> getGames() {
        return games;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return name.equals(team.getName());
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
