package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class MinimalFederationDao extends ResourceSupport {

    public final String name;
    public final String abbreviation;

    public MinimalFederationDao(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public void addLinks() {
        this.add(linkTo(methodOn(FederationController.class)
                .getFederation(abbreviation.toLowerCase()))
                .withRel("details"));
    }
}
