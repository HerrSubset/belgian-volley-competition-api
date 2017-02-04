package com.clubtools.belgianvolleycompetitionapi.domain;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class LeagueId {
    private String name;
    private String id;

    public LeagueId(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
