package com.clubtools.belgianvolleycompetitionapi.domain;

import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalTeamDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class League {
    private List<Team> teams;

    public League(List<Team> teams) {
        this.teams = teams;
    }

    public List<MinimalTeamDao> getBasicTeamInfo() {
        List<MinimalTeamDao> teamDaos = new ArrayList<>();

        for (Team team : teams) {
            teamDaos.add(new MinimalTeamDao(team));
        }

        return teamDaos;
    }
}
