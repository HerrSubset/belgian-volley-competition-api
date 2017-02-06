package com.clubtools.belgianvolleycompetitionapi.domain;


import org.junit.Test;

import static org.junit.Assert.assertSame;

public class LeagueIdTest {

    @Test
    public void constructorProperlySetsVariables() {
        String name = "name";
        String id = "id";

        LeagueId leagueId = new LeagueId(name, id);

        assertSame(name, leagueId.getName());
        assertSame(id, leagueId.getId());
    }
}
