package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.core.Federation;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.integration.shared.VolleyScoresCompetitionLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KlvvLoader extends FederationLoader {

    private final static String VOLLEYSCORES_FEDERATION_LEAGUES_DIV_ID = "mnu_province_4";

    private final VolleyScoresCompetitionLoader loader;

    @Autowired
    public KlvvLoader(VolleyScoresCompetitionLoader loader) {
        super(Federation.KLVV);
        this.loader = loader;
    }

    @Override
    public List<LeagueId> getLeagueIds() {
        return loader.getLeagueIds(VOLLEYSCORES_FEDERATION_LEAGUES_DIV_ID);
    }

    @Override
    public League getLeague(LeagueId leagueId) {
        return loader.getLeague(leagueId, VOLLEYSCORES_FEDERATION_LEAGUES_DIV_ID);
    }

    @Override
    public LeagueId getLeagueId(String leagueSlug) {
        return loader.getLeagueId(leagueSlug, VOLLEYSCORES_FEDERATION_LEAGUES_DIV_ID);
    }
}
