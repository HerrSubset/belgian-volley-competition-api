package com.clubtools.belgianvolleycompetitionapi.core;


public enum Federation {

    VVB("Vlaamse Volleybal Federatie", "VVB"),
    AVF("Antwerpse Volleybal Federatie", "AVF"),
    KOVV("Koninklijke Oost-Vlaamse Volleybalbond", "KOVV"),
    KLVV("Koninklijk Limburgs VolleybalVerbond", "KLVV");


    private final String name;
    private final String abbreviation;

    Federation(String name, String abbreviation) {
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
