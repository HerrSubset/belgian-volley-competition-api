package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;
import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


public class MinimalTeamDao extends ResourceSupport {

    public final String name;

    public MinimalTeamDao(Team team, String federationAbbreviation, LeagueId leagueId) {
        this.name = team.getName();
    }

    public void addLinks(String federationAbbreviation, String leagueSlug) {
        this.add(linkTo(methodOn(FederationController.class)
                .getTeam(federationAbbreviation, leagueSlug, UrlUtils.sluggify(name)))
                .withRel("details"));
    }
}
