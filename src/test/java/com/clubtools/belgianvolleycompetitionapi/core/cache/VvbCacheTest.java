package com.clubtools.belgianvolleycompetitionapi.core.cache;

import com.clubtools.belgianvolleycompetitionapi.integration.VvbLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VvbCacheTest {

    private static final String VVB_NAME = "Vlaamse Volleybalbond";
    private static final String VVB_ABBREVIATION = "VVB";

    @Test
    public void constructorSetsFieldsCorrectly() {
        VvbLoader loader = mock(VvbLoader.class);
        VvbCache vvbCache = new VvbCache(loader);

        assertEquals(VVB_ABBREVIATION, vvbCache.getAbbreviation());
        assertEquals(VVB_ABBREVIATION, vvbCache.getAbbreviation());
        assertSame(loader, vvbCache.getLoader());
    }
}