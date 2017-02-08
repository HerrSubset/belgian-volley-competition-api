package com.clubtools.belgianvolleycompetitionapi.domain;

import java.util.Date;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class Game {
    private String homeTeam;
    private String awayTeam;
    private Date date;
    private Integer homeSets;
    private Integer awaySets;


    public Game(String homeTeam, String awayTeam, Date date, Integer homeSets, Integer awaySets) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.homeSets = homeSets;
        this.awaySets = awaySets;
    }


    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public Integer getHomeSets() {
        return homeSets;
    }

    public Integer getAwaySets() {
        return awaySets;
    }
}
