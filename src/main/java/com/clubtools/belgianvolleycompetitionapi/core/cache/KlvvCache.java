package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.KlvvLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;


@Component
public class KlvvCache extends FederationCache {

    @Autowired
    public KlvvCache(KlvvLoader loader) {
        super(loader);
        checkNotNull(loader);
        name = "Koninklijk Limburgs VolleybalVerbond";
        abbreviation = "KLVV";
    }
}
