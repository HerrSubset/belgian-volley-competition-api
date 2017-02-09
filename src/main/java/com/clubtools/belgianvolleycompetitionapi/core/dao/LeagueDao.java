package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;

import java.util.List;


public class LeagueDao {
    public final String name;
    public final List<MinimalTeamDao> teams;

    public LeagueDao(String name, List<MinimalTeamDao> teams) {
        this.name = name;
        this.teams = teams;
    }

    public LeagueDao(League league, String federationAbbreviation) {
        this(league.getName(), league.getBasicTeamInfo(federationAbbreviation));
    }

    public void addLinks(String federationAbbreviation, String leagueSlug) {
        for (MinimalTeamDao minimalTeamDao : teams) {
            minimalTeamDao.addLinks(federationAbbreviation, leagueSlug);
        }
    }
}
