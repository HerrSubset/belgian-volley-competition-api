package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import com.clubtools.belgianvolleycompetitionapi.util.UrlUtils;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class MinimalLeagueDao extends ResourceSupport  {
    public final String name;

    public MinimalLeagueDao(String name, String federationAbbreviation) {
        this.name = name;

        // add links for further querying
        this.add(linkTo(methodOn(FederationController.class)
                .getLeague(UrlUtils.sluggify(federationAbbreviation), UrlUtils.sluggify(name)))
                .withRel("details"));

        this.add(linkTo(methodOn(FederationController.class)
                .getFederation(UrlUtils.sluggify(federationAbbreviation)))
                .withRel("federation"));
    }
}
