package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;
import com.clubtools.belgianvolleycompetitionapi.integration.FederationLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public abstract class FederationCache {

    protected String name;
    protected String abbreviation;

    protected FederationLoader loader;

    //**************************************************************************
    //* Getters / Setters
    //**************************************************************************

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public FederationLoader getLoader() {
        return loader;
    }

    //**************************************************************************
    //* Methods
    //**************************************************************************

    public FederationDao getFederationInfo() {
        List<MinimalLeagueDao> leagues = new ArrayList<>();

        for (LeagueId id : loader.getLeagueIds()) {
            leagues.add(new MinimalLeagueDao(id, abbreviation));
        }

        return new FederationDao(leagues);
    }


    public MinimalFederationDao getMinimalDao() {
        return new MinimalFederationDao(name, abbreviation);
    }

    public LeagueDao getLeague(String leagueSlug) {
        League league = getLeagueBySlug(leagueSlug);
        return new LeagueDao(league, abbreviation);
    }

    public TeamDao getTeam(String leagueSlug, String teamSlug) {
        League league = getLeagueBySlug(leagueSlug);
        Team team = league.getTeamBySlug(teamSlug);
        return new TeamDao(team);
    }


    private League getLeagueBySlug(String leagueSlug) {
        LeagueId leagueId = loader.getLeagueId(leagueSlug);
        return loader.getLeague(leagueId);
    }
}
