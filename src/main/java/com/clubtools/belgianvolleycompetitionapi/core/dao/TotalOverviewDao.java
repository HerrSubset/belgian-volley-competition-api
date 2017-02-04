package com.clubtools.belgianvolleycompetitionapi.core.dao;

import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class TotalOverviewDao {

    public final List<MinimalFederationDao> federations;

    public TotalOverviewDao(List<MinimalFederationDao> federations) {
        this.federations = federations;
    }
}
