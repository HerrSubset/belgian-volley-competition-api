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

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@RestController
public class FederationController {

    @Autowired
    private FederationService service;

    @RequestMapping("/federations")
    public HttpEntity<TotalOverviewDao> getOverview() {
        TotalOverviewDao overview = service.getOverview();
        return new ResponseEntity<>(overview, HttpStatus.OK);
    }

    @RequestMapping("/federations/{abbreviation}")
    public HttpEntity<FederationDao> getFederation(@PathVariable String abbreviation) {
        FederationDao federation = service.getFederationLeagues(abbreviation);
        return new ResponseEntity<>(federation, HttpStatus.OK);
    }

    @RequestMapping("/federations/{abbreviation}/leagues/{leagueslug}")
    public HttpEntity<LeagueDao> getLeague(
            @PathVariable("abbreviation") String abbreviation,
            @PathVariable("leagueslug") String leagueSlug) {

        LeagueDao league = service.getLeague(abbreviation, leagueSlug);
        return new ResponseEntity<>(league, HttpStatus.OK);
    }

    @RequestMapping("/federations/{abbreviation}/leagues/{leagueslug}/teams/{teamslug}")
    public HttpEntity<TeamDao> getTeam(
            @PathVariable("abbreviation") String abbreviation,
            @PathVariable("leagueslug") String leagueSlug,
            @PathVariable("teamslug") String teamSlug) {

        TeamDao team = service.getTeam(abbreviation, leagueSlug, teamSlug);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
}
