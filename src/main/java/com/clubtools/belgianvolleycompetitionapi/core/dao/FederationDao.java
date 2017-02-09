package com.clubtools.belgianvolleycompetitionapi.core.dao;

import java.util.List;


public class FederationDao {

    private final String name;
    private final String abbreviation;
    public final List<MinimalLeagueDao> leagues;

    public FederationDao(String name, String abbreviation, List<MinimalLeagueDao> leagues) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.leagues = leagues;
    }

    public void addLinks() {
        for (MinimalLeagueDao minimalLeagueDao : leagues) {
            minimalLeagueDao.addLinks(abbreviation);
        }
    }
}
