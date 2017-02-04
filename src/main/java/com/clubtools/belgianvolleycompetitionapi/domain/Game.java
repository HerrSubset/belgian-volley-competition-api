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


    public Game(String homeTeam, String awayTeam, Date date) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
    }


    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
}
