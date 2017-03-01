package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.core.Federation;
import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;

import java.util.ArrayList;
import java.util.List;


public abstract class FederationLoader {

    private final Federation federation;

    //**************************************************************************
    //* Constructor
    //**************************************************************************

    public FederationLoader(Federation federation) {
        this.federation = federation;
    }

    //**************************************************************************
    //* Getters / Setters
    //**************************************************************************


    public Federation getFederation() {
        return federation;
    }

    //**************************************************************************
    //* Methods
    //**************************************************************************

    public FederationDao getFederationInfo() {
        List<MinimalLeagueDao> leagues = new ArrayList<>();

        for (LeagueId id : getLeagueIds()) {
            leagues.add(new MinimalLeagueDao(id));
        }

        return new FederationDao(federation.getName(), federation.getAbbreviation(), leagues);
    }


    public MinimalFederationDao getMinimalDao() {
        return new MinimalFederationDao(federation.getName(), federation.getAbbreviation());
    }

    public LeagueDao getLeague(String leagueSlug) {
        League league = getLeagueBySlug(leagueSlug);
        return new LeagueDao(league, federation.getAbbreviation());
    }

    public TeamDao getTeam(String leagueSlug, String teamSlug) {
        League league = getLeagueBySlug(leagueSlug);
        Team team = league.getTeamBySlug(teamSlug);
        return new TeamDao(team);
    }


    private League getLeagueBySlug(String leagueSlug) {
        LeagueId leagueId = getLeagueId(leagueSlug);
        return getLeague(leagueId);
    }

    //**************************************************************************
    //* Abstract Methods
    //**************************************************************************

    protected abstract List<LeagueId> getLeagueIds();

    protected abstract League getLeague(LeagueId leagueId);

    protected abstract LeagueId getLeagueId(String leagueSlug);
}
