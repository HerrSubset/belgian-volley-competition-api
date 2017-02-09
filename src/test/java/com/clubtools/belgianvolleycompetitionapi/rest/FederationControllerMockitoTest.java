package com.clubtools.belgianvolleycompetitionapi.rest;

import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.LeagueDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TeamDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TotalOverviewDao;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FederationControllerMockitoTest {

    private static final String TEST_FEDERATION_NAME = "Vlaamse Volleybalbond";
    private static final String TEST_TEAM_SLUG = "mendo-booischot-c";
    private static final String TEST_FEDERATION_ABBREVIATION = "vvb";
    private static final String TEST_LEAGUE_SLUG = "h2a";
    private static final String TEST_LEAGUE_NAME = "2de Divisie Heren A";

    @InjectMocks
    private FederationController controller;

    @Mock
    private FederationService service;

    @After
    public void cleanUp() {
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getOverviewRetunsObjectGivenByService() {
        TotalOverviewDao totalOverviewDao = new TotalOverviewDao(new ArrayList<>());

        when(service.getOverview()).thenReturn(totalOverviewDao);

        HttpEntity<TotalOverviewDao> overview = controller.getOverview();

        verify(service).getOverview();
        assertSame(totalOverviewDao, overview.getBody());
    }

    @Test
    public void getOverviewCallsAddLinksOnDao() {
        TotalOverviewDao dao = mock(TotalOverviewDao.class);
        when(service.getOverview()).thenReturn(dao);

        controller.getOverview();

        verify(service).getOverview();
        verify(dao).addLinks();
    }

    @Test
    public void getFederationReturnsObjectGivenByService() {
        FederationDao federationDao =
                new FederationDao(TEST_FEDERATION_NAME, TEST_FEDERATION_ABBREVIATION, new ArrayList<>());

        when(service.getFederationLeagues(TEST_FEDERATION_ABBREVIATION))
                .thenReturn(federationDao);

        HttpEntity<FederationDao> federation =
                controller.getFederation(TEST_FEDERATION_ABBREVIATION);

        verify(service).getFederationLeagues(TEST_FEDERATION_ABBREVIATION);
        assertSame(federationDao, federation.getBody());
    }

    @Test
    public void getFederationCallsAddLinksOnDao() {
        FederationDao dao = mock(FederationDao.class);
        when(service.getFederationLeagues(TEST_FEDERATION_ABBREVIATION)).thenReturn(dao);

        controller.getFederation(TEST_FEDERATION_ABBREVIATION);

        verify(service).getFederationLeagues(TEST_FEDERATION_ABBREVIATION);
        verify(dao).addLinks();
    }

    @Test
    public void getLeagueReturnsObjectGivenByService() {
        LeagueDao leagueDao = new LeagueDao(TEST_LEAGUE_NAME, new ArrayList<>());

        when(service.getLeague(TEST_FEDERATION_ABBREVIATION,
                TEST_LEAGUE_SLUG)).thenReturn(leagueDao);

        HttpEntity<LeagueDao> league = controller
                .getLeague(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);

        verify(service).getLeague(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
        assertSame(leagueDao, league.getBody());
    }

    @Test
    public void getLeagueCallsAddLinksOnDao() {
        LeagueDao dao = mock(LeagueDao.class);
        when(service.getLeague(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG)).thenReturn(dao);

        controller.getLeague(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);

        verify(service).getLeague(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
        verify(dao).addLinks(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
    }

    @Test
    public void getTeamReturnsObjectGivenByService() {
        TeamDao teamDao = mock(TeamDao.class);

        when(service.getTeam(TEST_FEDERATION_ABBREVIATION,
                TEST_LEAGUE_SLUG, TEST_TEAM_SLUG)).thenReturn(teamDao);

        HttpEntity<TeamDao> team = controller.getTeam(
                TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG, TEST_TEAM_SLUG);

        verify(service).getTeam(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG,
                TEST_TEAM_SLUG);
        assertSame(teamDao, team.getBody());
    }

    @Test
    public void getTeamCallsAddLinksOnDao() {
        TeamDao dao = mock(TeamDao.class);
        when(service.getTeam(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG, TEST_TEAM_SLUG)).thenReturn(dao);

        controller.getTeam(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG, TEST_TEAM_SLUG);
        verify(service).getTeam(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG, TEST_TEAM_SLUG);
        verify(dao).addLinks(TEST_FEDERATION_ABBREVIATION, TEST_LEAGUE_SLUG);
    }
}
