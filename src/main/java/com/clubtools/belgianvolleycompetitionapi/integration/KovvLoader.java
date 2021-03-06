package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.core.Federation;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.integration.shared.VolleyBiebLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class KovvLoader extends FederationLoader {

    private static final String PROVINCE_CODE = "6";
    private final VolleyBiebLoader volleyBiebLoader;

    @Autowired
    public KovvLoader(VolleyBiebLoader volleyBiebLoader) {
        super(Federation.KOVV);
        this.volleyBiebLoader = volleyBiebLoader;
    }

    @Override
    public List<LeagueId> getLeagueIds() {
        return volleyBiebLoader.getLeagueIds(PROVINCE_CODE);
    }

    @Override
    public League getLeague(LeagueId leagueId) {
        return volleyBiebLoader.getLeague(leagueId, PROVINCE_CODE);
    }

    @Override
    public LeagueId getLeagueId(String leagueSlug) {
        return volleyBiebLoader.getLeagueId(leagueSlug, PROVINCE_CODE);
    }
}
