package com.clubtools.belgianvolleycompetitionapi.core.dao;

import com.clubtools.belgianvolleycompetitionapi.rest.FederationController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class MinimalFederationDao extends ResourceSupport {

    public final String name;
    public final String abbreviation;

    public MinimalFederationDao(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;

        // links for further querying
        this.add(linkTo(methodOn(FederationController.class)
                .getFederation(abbreviation.toLowerCase()))
                .withRel("details"));
    }
}
