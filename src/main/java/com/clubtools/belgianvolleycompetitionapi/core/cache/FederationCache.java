package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.LeagueDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalFederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalLeagueDao;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
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

        for (String name : loader.getLeagueNames()) {
            leagues.add(new MinimalLeagueDao(name, abbreviation));
        }

        return new FederationDao(leagues);
    }


    public MinimalFederationDao getMinimalDao() {
        return new MinimalFederationDao(name, abbreviation);
    }

    public LeagueDao getLeague(String leagueSlug) {
        League league = loader.getLeague(leagueSlug);
        return new LeagueDao(league);
    }
}
