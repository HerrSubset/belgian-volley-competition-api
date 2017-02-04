package com.clubtools.belgianvolleycompetitionapi.core.dao;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class FederationDao {

    public final List<String> leagueNames;

    public FederationDao(List<String> leagueNames) {
        this.leagueNames = leagueNames;
    }
}
