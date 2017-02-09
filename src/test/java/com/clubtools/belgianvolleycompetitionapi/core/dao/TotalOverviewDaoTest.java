package com.clubtools.belgianvolleycompetitionapi.core.dao;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TotalOverviewDaoTest {

    @Test
    public void addLinksCallsAddLinksOnAllFederationDaos() {
        MinimalFederationDao dao1 = mock(MinimalFederationDao.class);
        MinimalFederationDao dao2 = mock(MinimalFederationDao.class);
        List<MinimalFederationDao> daoList = Arrays.asList(dao1, dao2);

        TotalOverviewDao totalOverviewDao = new TotalOverviewDao(daoList);

        totalOverviewDao.addLinks();

        verify(dao1).addLinks();
        verify(dao2).addLinks();
    }
}