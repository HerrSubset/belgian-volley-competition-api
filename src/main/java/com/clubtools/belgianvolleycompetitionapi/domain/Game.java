package com.clubtools.belgianvolleycompetitionapi.domain;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Game {
    private final String homeTeam;
    private final String awayTeam;
    private final Date date;
    private final Integer homeSets;
    private final Integer awaySets;
    private final Boolean forfait;


    //**************************************************************************
    //* Constructor
    //**************************************************************************

    public Game(String homeTeam, String awayTeam, Date date, Integer homeSets, Integer awaySets, Boolean forfait) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.homeSets = homeSets;
        this.awaySets = awaySets;
        this.forfait = forfait;
    }


    //**************************************************************************
    //* Getters / Setters
    //**************************************************************************

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

    public Boolean getForfait() {
        return forfait;
    }

    //**************************************************************************
    //* Methods
    //**************************************************************************

    public String getDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return simpleDateFormat.format(date);
    }
}
