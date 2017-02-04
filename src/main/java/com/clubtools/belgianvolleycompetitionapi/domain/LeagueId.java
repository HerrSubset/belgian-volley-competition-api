package com.clubtools.belgianvolleycompetitionapi.domain;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class LeagueId {
    private String name;
    private String abbreviation;

    public LeagueId(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
