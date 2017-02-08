package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.KovvLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class KovvCacheTest {

    public static final String KOVV_NAME = "Koninklijke Oost-Vlaamse Volleybalbond";
    public static final String KOVV_ABBREVIATION = "KOVV";

    @Test
    public void constructorSetsFieldsCorrectly() {
        KovvLoader loader = mock(KovvLoader.class);

        KovvCache kovvCache = new KovvCache(loader);

        assertEquals(KOVV_NAME, kovvCache.getName());
        assertEquals(KOVV_ABBREVIATION, kovvCache.getAbbreviation());
        assertSame(loader, kovvCache.getLoader());
    }
}