package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


public class MinimalLeagueDao extends ResourceSupport  {
    public final String name;
    public final String abbreviation;

    public MinimalLeagueDao(LeagueId leagueId) {
        this.name = leagueId.getName();
        this.abbreviation = leagueId.getId();
    }

    public void addLinks(String federationAbbreviation) {
        this.add(linkTo(methodOn(FederationController.class)
                .getLeague(UrlUtils.sluggify(federationAbbreviation), UrlUtils.sluggify(abbreviation)))
                .withRel("details"));
    }
}
