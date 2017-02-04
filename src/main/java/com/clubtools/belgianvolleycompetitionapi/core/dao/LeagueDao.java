package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.League;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class LeagueDao {
    public final List<MinimalTeamDao> teams;

    public LeagueDao(List<MinimalTeamDao> teams) {
        this.teams = teams;
    }

    public LeagueDao(League league) {
        this.teams = league.getBasicTeamInfo();
    }
}
