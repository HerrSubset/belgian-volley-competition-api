package com.clubtools.belgianvolleycompetitionapi.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class TeamTest {

    @Test
    public void constructorSetsVariablesProperly() {
        List<Game> games = new ArrayList<>();
        String name = "name";

        Team team = new Team(name, games);

        assertSame(games, team.getGames());
        assertSame(name, team.getName());
    }
}
