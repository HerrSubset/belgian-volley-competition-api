package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalFederationDao;
import com.clubtools.belgianvolleycompetitionapi.integration.FederationLoader;

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
        List<String> leagueNames = loader.getLeagueNames();
        return new FederationDao(leagueNames);
    }


    public MinimalFederationDao getMinimalDao() {
        return new MinimalFederationDao(name, abbreviation);
    }
}
