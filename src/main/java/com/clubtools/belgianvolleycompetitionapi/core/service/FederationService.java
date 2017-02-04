package com.clubtools.belgianvolleycompetitionapi.core.service;

import com.clubtools.belgianvolleycompetitionapi.core.cache.FederationCache;
import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.MinimalFederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TotalOverviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@Service
public class FederationService {

    @Autowired
    private Collection<FederationCache> federationCaches;

    private HashMap<String, FederationCache> routeMap;


    @PostConstruct
    public void setUp() {
        routeMap = new HashMap<>();

        for (FederationCache cache : federationCaches) {
            routeMap.put(cache.getAbbreviation().toUpperCase(), cache);
        }
    }


    public TotalOverviewDao getOverview() {
        List<MinimalFederationDao> federations = new ArrayList<>();

        for (FederationCache cache : federationCaches) {
            federations.add(cache.getMinimalDao());
        }

        return new TotalOverviewDao(federations);
    }

    public FederationDao getFederationLeagues(String abbreviation) {
        FederationCache federationCache = routeMap.get(abbreviation.toUpperCase());

        // TODO: throw error causing 404 when federation is not found

        return federationCache.getFederationInfo();
    }
}
