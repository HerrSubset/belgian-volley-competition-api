package com.clubtools.belgianvolleycompetitionapi.core.service;

import com.clubtools.belgianvolleycompetitionapi.core.Federation;
import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import com.clubtools.belgianvolleycompetitionapi.integration.FederationLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


@Service
public class FederationService {

    private HashMap<Federation, FederationLoader> routeMap;
    private Collection<FederationLoader> federationLoaders;


    @Autowired
    public FederationService(Collection<FederationLoader> federationLoaders) {
        checkNotNull(federationLoaders);
        this.federationLoaders = federationLoaders;

        routeMap = new HashMap<>();
        for (FederationLoader loader : federationLoaders) {
            routeMap.put(loader.getFederation(), loader);
        }
    }

    public TotalOverviewDao getOverview() {
        List<MinimalFederationDao> federations = new ArrayList<>();

        for (FederationLoader loader : routeMap.values()) {
            federations.add(loader.getMinimalDao());
        }

        return new TotalOverviewDao(federations);
    }

    public FederationDao getFederationLeagues(String abbreviation) {
        FederationLoader federationLoader = getLoader(abbreviation);

        return federationLoader.getFederationInfo();
    }

    public LeagueDao getLeague(String federationAbbreviation, String leagueSlug) {
        FederationLoader federationLoader = getLoader(federationAbbreviation);
        return federationLoader.getLeague(leagueSlug);
    }


    private FederationLoader getLoader(String abbreviation) {
        // TODO: throw error causing 404 when federation is not found
        Federation federation = Federation.valueOf(abbreviation.toUpperCase());
        return routeMap.get(federation);
    }

    public TeamDao getTeam(String federationAbbreviation, String leagueSlug, String teamSlug) {
        FederationLoader loader = getLoader(federationAbbreviation);
        return loader.getTeam(leagueSlug, teamSlug);
    }
}
