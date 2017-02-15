package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.AvfLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Component
public class AvfCache extends FederationCache {

    @Autowired
    public AvfCache(AvfLoader loader) {
        super(loader);
        assertNotNull(loader);
        name = "Antwerpse Volleybal Federatie";
        abbreviation = "AVF";
    }
}
