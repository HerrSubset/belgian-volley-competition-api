package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.KovvLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@Component
public class KovvCache extends FederationCache {

    @PostConstruct
    public void setUp() {
        name = "Koninklijke Oost-Vlaamse Volleybalbond";
        abbreviation = "KOVV";
        loader = new KovvLoader();
    }
}
