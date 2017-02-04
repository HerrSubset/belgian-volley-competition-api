package com.clubtools.belgianvolleycompetitionapi.domain;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class Team {

    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
