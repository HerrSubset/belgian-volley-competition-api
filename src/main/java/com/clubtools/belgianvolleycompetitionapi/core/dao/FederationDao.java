package com.clubtools.belgianvolleycompetitionapi.core.dao;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class FederationDao {

    public final List<MinimalLeagueDao> leagues;

    public FederationDao(List<MinimalLeagueDao> leagues) {
        this.leagues = leagues;
    }
}
