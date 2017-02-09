package com.clubtools.belgianvolleycompetitionapi.core.dao;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FederationDaoTest {

    private final static String TEST_NAME = "Vlaamse Volleybalbond";
    private final static String TEST_ABBREVIATION = "VVB";


    @Test
    public void addLinksCallsAddLinksOnAllLeagueDaos() {
        MinimalLeagueDao dao1 = mock(MinimalLeagueDao.class);
        MinimalLeagueDao dao2 = mock(MinimalLeagueDao.class);
        List<MinimalLeagueDao> minimalLeagueDaos = Arrays.asList(dao1, dao2);

        FederationDao federationDao = new FederationDao(TEST_NAME, TEST_ABBREVIATION, minimalLeagueDaos);
        federationDao.addLinks();

        verify(dao1).addLinks(TEST_ABBREVIATION);
        verify(dao2).addLinks(TEST_ABBREVIATION);
    }
}