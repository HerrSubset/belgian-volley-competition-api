package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public interface FederationLoader {

    List<LeagueId> getLeagueIds();

    League getLeague(LeagueId leagueId);

    LeagueId getLeagueId(String leagueSlug);
}
