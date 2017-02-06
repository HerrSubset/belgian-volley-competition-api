package com.clubtools.belgianvolleycompetitionapi.service;

import com.clubtools.belgianvolleycompetitionapi.core.cache.FederationCache;
import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FederationServiceMockitoTest {

    private static final String CACHE1_NAME = "VVB";
    private static final String CACHE2_NAME = "KOVV";
    private static final String ABBREVIATION = "vvb";
    private static final String LEAGUE_SLUG = "h2a";
    private static final String TEAM_SLUG = "mendo-booischot-c";

    private FederationService service;

    @Mock
    private FederationCache cache1;

    @Mock
    private FederationCache cache2;

    @Before
    public void setUp() {
        List<FederationCache> federationCaches = Arrays.asList(cache1, cache2);
        when(cache1.getAbbreviation()).thenReturn(CACHE1_NAME);
        when(cache2.getAbbreviation()).thenReturn(CACHE2_NAME);
        service = new FederationService(federationCaches);
    }

    @Test
    public void getOverviewReturnsDataFromEveryFederationCache() {
        MinimalFederationDao dao1 = mock(MinimalFederationDao.class);
        MinimalFederationDao dao2 = mock(MinimalFederationDao.class);

        List<FederationCache> federationCaches = Arrays.asList(cache1, cache2);

        when(cache1.getMinimalDao()).thenReturn(dao1);
        when(cache2.getMinimalDao()).thenReturn(dao2);

        TotalOverviewDao overview = service.getOverview();

        verify(cache1).getMinimalDao();
        assertTrue(overview.federations.contains(dao1));
        assertTrue(overview.federations.contains(dao2));
    }

    @Test
    public void getFederationLeaguesREturnsObjectGivenByCache() {
        FederationDao federationDao = mock(FederationDao.class);

        when(cache1.getFederationInfo()).thenReturn(federationDao);

        FederationDao leagues = service.getFederationLeagues(ABBREVIATION);

        verify(cache1).getFederationInfo();
        assertSame(federationDao, leagues);
    }

    @Test
    public void getLeagueRetunsObjectGivenByCache() {
        LeagueDao leagueDao = mock(LeagueDao.class);

        when(cache1.getLeague(LEAGUE_SLUG)).thenReturn(leagueDao);

        LeagueDao league = service.getLeague(ABBREVIATION, LEAGUE_SLUG);

        verify(cache1).getLeague(LEAGUE_SLUG);
        assertSame(leagueDao, league);
    }

    @Test
    public void getTeamReturnsObjectGivenByCache() {
        TeamDao teamDao = mock(TeamDao.class);

        when(cache1.getTeam(LEAGUE_SLUG, TEAM_SLUG)).thenReturn(teamDao);

        TeamDao team = service.getTeam(ABBREVIATION, LEAGUE_SLUG, TEAM_SLUG);

        verify(cache1).getTeam(LEAGUE_SLUG, TEAM_SLUG);
        assertSame(teamDao, team);
    }
}
