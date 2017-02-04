package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class KovvLoader implements FederationLoader {
    @Override
    public List<LeagueId> getLeagueIds() {
        ArrayList<LeagueId> ids = new ArrayList<>();
        ids.add(new LeagueId("League 1", "a"));
        return ids;
    }

    @Override
    public League getLeague(String leagueSlug) {
        return null;
    }
}
