package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.VvbLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@Component
public class VvbCache extends FederationCache {

    @PostConstruct
    public void setUp() {
        name = "Vlaamse Volleybalbond";
        abbreviation = "VVB";
        loader = new VvbLoader();
    }
}
