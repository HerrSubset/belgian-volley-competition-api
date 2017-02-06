package com.clubtools.belgianvolleycompetitionapi.core.service;

import com.clubtools.belgianvolleycompetitionapi.core.cache.FederationCache;
import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@Service
public class FederationService {

    private HashMap<String, FederationCache> routeMap;


    @Autowired
    public FederationService(Collection<FederationCache> federationCaches) {
        checkNotNull(federationCaches);

        routeMap = new HashMap<>();
        for (FederationCache cache : federationCaches) {
            routeMap.put(cache.getAbbreviation().toUpperCase(), cache);
        }
    }


    public TotalOverviewDao getOverview() {
        List<MinimalFederationDao> federations = new ArrayList<>();

        for (FederationCache cache : routeMap.values()) {
            federations.add(cache.getMinimalDao());
        }

        return new TotalOverviewDao(federations);
    }

    public FederationDao getFederationLeagues(String abbreviation) {
        FederationCache federationCache = getCache(abbreviation);

        return federationCache.getFederationInfo();
    }

    public LeagueDao getLeague(String federationAbbreviation, String leagueSlug) {
        FederationCache federationCache = getCache(federationAbbreviation);
        return federationCache.getLeague(leagueSlug);
    }


    private FederationCache getCache(String abbreviation) {
        // TODO: throw error causing 404 when federation is not found
        return routeMap.get(abbreviation.toUpperCase());
    }

    public TeamDao getTeam(String federationAbbreviation, String leagueSlug, String teamSlug) {
        FederationCache cache = getCache(federationAbbreviation);
        return cache.getTeam(leagueSlug, teamSlug);
    }
}
