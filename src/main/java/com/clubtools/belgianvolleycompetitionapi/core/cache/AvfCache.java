package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.AvfLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class AvfCache extends FederationCache {

    @Autowired
    public AvfCache(AvfLoader loader) {
        super(loader);
        checkNotNull(loader);
        name = "Antwerpse Volleybal Federatie";
        abbreviation = "AVF";
    }
}