package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class VvbLoader implements FederationLoader {


    @Override
    public List<String> getLeagueNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Ethias Volley League");
        names.add("1ste Divisie heren A");
        names.add("1ste Divisie heren B");
        names.add("1ste Divisie Dames A");
        names.add("1ste Divisie Dames B");
        return names;
    }

    @Override
    public League getLeague(String leagueSlug) {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("VVBB example team 1 - " + leagueSlug));
        teams.add(new Team("VVBB example team 2 - " + leagueSlug));
        teams.add(new Team("VVBB example team 3 - " + leagueSlug));
        teams.add(new Team("VVBB example team 4 - " + leagueSlug));

        return new League(teams);
    }
}
