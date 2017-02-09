package com.clubtools.belgianvolleycompetitionapi.core.dao;

import java.util.List;


public class TotalOverviewDao {

    public final List<MinimalFederationDao> federations;

    public TotalOverviewDao(List<MinimalFederationDao> federations) {
        this.federations = federations;
    }

    public void addLinks() {
        for (MinimalFederationDao minimalFederationDao : federations) {
            minimalFederationDao.addLinks();
        }
    }
}
