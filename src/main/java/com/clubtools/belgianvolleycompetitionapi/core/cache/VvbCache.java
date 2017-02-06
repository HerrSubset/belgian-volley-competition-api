package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.VvbLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
@Component
public class VvbCache extends FederationCache {

    @Autowired
    public VvbCache(VvbLoader loader) {
        super(loader);
        checkNotNull(loader);
        name = "Vlaamse Volleybalbond";
        abbreviation = "VVB";
    }
}
