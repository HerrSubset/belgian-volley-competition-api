package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public interface FederationLoader {

    List<String> getLeagueNames();

    League getLeague(String leagueSlug);
}
