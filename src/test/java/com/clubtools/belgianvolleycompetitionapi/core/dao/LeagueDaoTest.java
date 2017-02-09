package com.clubtools.belgianvolleycompetitionapi.core.dao;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class LeagueDaoTest {

    private static final String TEST_NAME = "2de Divisie Heren A";
    private static final String TEST_FEDERATION_ABBREVIATION = "vvb";
    private static final String TEST_LEAGUE_SLUG = "h2a";

    @Test
    public void addLinksCallsAddLinksOnAllTeamDaos() {
        MinimalTeamDao dao1 = mock(MinimalTeamDao.class);
        MinimalTeamDao dao2 = mock(MinimalTeamDao.class);
        List<MinimalTeamDao> teamDaos = Arrays.asList(dao1, dao2);

        LeagueDao leagueDao = new LeagueDao(TEST_NAME, teamDaos);

        leagueDao.addLinks(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
        verify(dao1).addLinks(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
    }
}