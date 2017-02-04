package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;

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
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("KOVV example team 1 - " + leagueSlug));
        teams.add(new Team("KOVV example team 2 - " + leagueSlug));
        teams.add(new Team("KOVV example team 3 - " + leagueSlug));
        teams.add(new Team("KOVV example team 4 - " + leagueSlug));

        return new League(teams);
    }
}
