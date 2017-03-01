package com.clubtools.belgianvolleycompetitionapi.service;

import com.clubtools.belgianvolleycompetitionapi.core.Federation;
import com.clubtools.belgianvolleycompetitionapi.core.dao.*;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import com.clubtools.belgianvolleycompetitionapi.integration.FederationLoader;
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

    private static final Federation TEST_FEDERATION_1 = Federation.VVB;
    private static final Federation TEST_FEDERATION_2 = Federation.KOVV;
    private static final String ABBREVIATION = "vvb";
    private static final String LEAGUE_SLUG = "h2a";
    private static final String TEAM_SLUG = "mendo-booischot-c";

    private FederationService service;

    @Mock
    private FederationLoader loader1;

    @Mock
    private FederationLoader loader2;

    @Before
    public void setUp() {
        List<FederationLoader> federationLoaders = Arrays.asList(loader1, loader2);
        when(loader1.getFederation()).thenReturn(TEST_FEDERATION_1);
        when(loader2.getFederation()).thenReturn(TEST_FEDERATION_2);
        service = new FederationService(federationLoaders);
    }

    @Test
    public void getOverviewReturnsDataFromEveryFederationLoader() {
        MinimalFederationDao dao1 = mock(MinimalFederationDao.class);
        MinimalFederationDao dao2 = mock(MinimalFederationDao.class);

        when(loader1.getMinimalDao()).thenReturn(dao1);
        when(loader2.getMinimalDao()).thenReturn(dao2);

        TotalOverviewDao overview = service.getOverview();

        verify(loader1).getMinimalDao();
        assertTrue(overview.federations.contains(dao1));
        assertTrue(overview.federations.contains(dao2));
    }

    @Test
    public void getFederationLeaguesREturnsObjectGivenByCache() {
        FederationDao federationDao = mock(FederationDao.class);

        when(loader1.getFederationInfo()).thenReturn(federationDao);

        FederationDao leagues = service.getFederationLeagues(ABBREVIATION);

        verify(loader1).getFederationInfo();
        assertSame(federationDao, leagues);
    }

    @Test
    public void getLeagueRetunsObjectGivenByCache() {
        LeagueDao leagueDao = mock(LeagueDao.class);

        when(loader1.getLeague(LEAGUE_SLUG)).thenReturn(leagueDao);

        LeagueDao league = service.getLeague(ABBREVIATION, LEAGUE_SLUG);

        verify(loader1).getLeague(LEAGUE_SLUG);
        assertSame(leagueDao, league);
    }

    @Test
    public void getTeamReturnsObjectGivenByCache() {
        TeamDao teamDao = mock(TeamDao.class);

        when(loader1.getTeam(LEAGUE_SLUG, TEAM_SLUG)).thenReturn(teamDao);

        TeamDao team = service.getTeam(ABBREVIATION, LEAGUE_SLUG, TEAM_SLUG);

        verify(loader1).getTeam(LEAGUE_SLUG, TEAM_SLUG);
        assertSame(teamDao, team);
    }
}
