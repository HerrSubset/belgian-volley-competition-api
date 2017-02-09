package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.KovvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class KovvCache extends FederationCache {

    @Autowired
    public KovvCache(KovvLoader loader) {
        super(loader);
        checkNotNull(loader);
        name = "Koninklijke Oost-Vlaamse Volleybalbond";
        abbreviation = "KOVV";
    }
}
