package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.Game;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;
import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


public class TeamDao extends ResourceSupport {
    public final String name;
    public final List<Game> games;

    public TeamDao(Team team) {
        this.name = team.getName();
        this.games = team.getGames();
    }

    public void addLinks(String federationAbbreviation, String leagueSlug) {
        this.add(linkTo(methodOn(FederationController.class)
                .getTeam(federationAbbreviation, leagueSlug, UrlUtils.sluggify(name)))
                .withRel("self"));

        this.add(linkTo(methodOn(FederationController.class)
                .getLeague(federationAbbreviation, leagueSlug))
                .withRel("league"));

        this.add(linkTo(methodOn(FederationController.class)
                .getFederation(federationAbbreviation))
                .withRel("federation"));
    }
}
