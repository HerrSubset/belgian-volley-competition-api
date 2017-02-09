package com.clubtools.belgianvolleycompetitionapi.rest;

import com.clubtools.belgianvolleycompetitionapi.core.dao.FederationDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.LeagueDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TeamDao;
import com.clubtools.belgianvolleycompetitionapi.core.dao.TotalOverviewDao;
import com.clubtools.belgianvolleycompetitionapi.core.service.FederationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.base.Preconditions.checkNotNull;


@RestController
public class FederationController {

    private FederationService service;

    @Autowired
    public FederationController(FederationService service) {
        checkNotNull(service);
        this.service = service;
    }


    /**
     * Retuns an overview of all federations that are available in the api.
     */
    @RequestMapping("/federations")
    public HttpEntity<TotalOverviewDao> getOverview() {
        TotalOverviewDao overview = service.getOverview();

        overview.addLinks();

        return new ResponseEntity<>(overview, HttpStatus.OK);
    }


    /**
     * Return an overview of the federation that is being requested by passing its abbreviation. The overview
     * contains a list of all leagues that are available for this federation.
     *
     * @param abbreviation  The abbreviation of the league's name.
     * @return              Object with details on the federation.
     */
    @RequestMapping("/federations/{abbreviation}")
    public HttpEntity<FederationDao> getFederation(@PathVariable String abbreviation) {
        FederationDao federation = service.getFederationLeagues(abbreviation);

        federation.addLinks();

        return new ResponseEntity<>(federation, HttpStatus.OK);
    }


    /**
     * Return an overview of the requested league. The overview contains a list of all teams playing in this league
     * and information on the ranking of the teams (TODO).
     *
     * @param abbreviation  The abbreviation of the federation the league.
     * @param leagueSlug    The slug version of the league's name.
     * @return              Object with details info on the league.
     */
    @RequestMapping("/federations/{abbreviation}/leagues/{leagueslug}")
    public HttpEntity<LeagueDao> getLeague(
            @PathVariable("abbreviation") String abbreviation,
            @PathVariable("leagueslug") String leagueSlug) {

        LeagueDao league = service.getLeague(abbreviation, leagueSlug);
        league.addLinks(abbreviation, leagueSlug);
        return new ResponseEntity<>(league, HttpStatus.OK);
    }


    /**
     * Return an object with info on a team. The object includes a list with the team's game results
     * of the current season.
     *
     * @param abbreviation  The abbreviation of the federation's name that the requested team is playing under.
     * @param leagueSlug    The slug version of the league's name that the requested team plays in.
     * @param teamSlug      The slug version of the requested team's name
     * @return              The details object.
     */
    @RequestMapping("/federations/{abbreviation}/leagues/{leagueslug}/teams/{teamslug}")
    public HttpEntity<TeamDao> getTeam(
            @PathVariable("abbreviation") String abbreviation,
            @PathVariable("leagueslug") String leagueSlug,
            @PathVariable("teamslug") String teamSlug) {

        TeamDao team = service.getTeam(abbreviation, leagueSlug, teamSlug);
        team.addLinks(abbreviation, leagueSlug);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
