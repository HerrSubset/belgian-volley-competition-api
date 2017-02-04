package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class KovvLoader implements FederationLoader {
    @Override
    public List<String> getLeagueNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("1ste provinciale heren");
        names.add("2de provinciale heren A");
        names.add("2de provinciale heren B");
        names.add("1ste provinciale dames");
        names.add("2de provinciale dames A");
        names.add("2de provinciale dames B");
        return names;
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
