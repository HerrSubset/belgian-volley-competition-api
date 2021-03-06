package com.clubtools.belgianvolleycompetitionapi.integration.shared;


import com.clubtools.belgianvolleycompetitionapi.domain.Game;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.google.common.io.CharStreams;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class VolleyBiebLoader {

    private final static String API_HOST = "volleybieb.be";
    private final static String API_PATH = "/AjaxVVBWedstijden.php";
    private final static String ACTION = "wattedoen";
    private final static String ACTION_FIND_LEAGUES = "2";
    private final static String ACTION_FIND_GAMES = "1";
    private final static String REEKS_TYPE = "reekstype";
    private final static String PROVINCIE = "provincie";
    private final static String REEKSNAAM = "reeksnaam";
    private static final String REEKSAFKORTING = "reeksafkorting";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<LeagueId> getLeagueIds(String provinceCode) {
        HttpClient httpClient = HttpClients.createDefault();
        URI uri;
        try {
            uri = new URIBuilder().setScheme("http")
                    .setHost(API_HOST)
                    .setPath(API_PATH)
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        logger.info("Connecting to " + uri.toString() + " to load leagues for province with code: " + provinceCode);
        HttpPost httpPost = new HttpPost(uri);
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>(3);
        params.add(new BasicNameValuePair(ACTION, ACTION_FIND_LEAGUES));
        params.add(new BasicNameValuePair(REEKS_TYPE, "1"));
        params.add(new BasicNameValuePair(PROVINCIE, provinceCode));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("Error during request to " + uri.toString());
            throw new RuntimeException(e);
        }

        // perform request
        String json;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            json = CharStreams.toString(new InputStreamReader(entity.getContent()));
        } catch (IOException e) {
            logger.error("Error when parsing json received from " + uri.toString());
            throw new RuntimeException(e);
        }


        // parse request and extract league names
        ArrayList<LeagueId> ids = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonArray jsonArray = element.getAsJsonArray();
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject object = iterator.next().getAsJsonObject();
            String name = object.get(REEKSNAAM).getAsString();
            String abbreviation = object.get(REEKSAFKORTING).getAsString();
            ids.add(new LeagueId(name, abbreviation));
        }


        logger.info("Number of leagues found: " + ids.size());
        return ids;
    }



    public League getLeague(LeagueId leagueId, String provinceCode) {
        JsonArray jsonArray = getLeagueGamesJsonArray(leagueId.getId(), provinceCode);
        Iterator<JsonElement> iterator = jsonArray.iterator();
        List<Game> games = new ArrayList<>();

        while (iterator.hasNext()) {
            JsonObject object = iterator.next().getAsJsonObject();
            games.add(createGame(object));
        }

        logger.info("Games found: " + games.size());
        return new League(leagueId, games);
    }

    private Game createGame(JsonObject object) {
        String homeTeam = object.get("thuisploeg").getAsString().trim();
        String awayTeam = object.get("bezoekers").getAsString().trim();
        String day = object.get("Datum").getAsString().trim();
        String time = object.get("aanvangsuur").getAsString().trim();

        String score = object.get("resulthoofd").getAsString().trim();
        Integer homeSets = null;
        Integer awaySets = null;
        if (score.length() == 5) {
            homeSets = Integer.parseInt(score.substring(0, 1));
            awaySets = Integer.parseInt(score.substring(4, 5));
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date;
        try {
            String fullDateString = day + " " + time;
            date = format.parse(fullDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return new Game(homeTeam, awayTeam, date, homeSets, awaySets, null);
    }


    private JsonArray getLeagueGamesJsonArray(String leagueId, String provinceCode) {

        URI uri;
        try {
            uri = new URIBuilder().setScheme("http")
                    .setHost(API_HOST)
                    .setPath(API_PATH)
                    .setParameter(ACTION, ACTION_FIND_GAMES)
                    .setParameter("provincie", provinceCode)
                    .setParameter("reeks", leagueId.toUpperCase())
                    .setParameter("competitie", "1")
                    .setParameter("team", "0")
                    .setParameter("datumbegin", "0")
                    .setParameter("datumeind", "0")
                    .setParameter("stamnummer", "0")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        logger.info("Loading league from " + uri.toString());
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);


        // perform request
        String json;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            json = CharStreams.toString(new InputStreamReader(entity.getContent()));
        } catch (IOException e) {
            logger.error("Error when parsing json received from " + uri.toString());
            throw new RuntimeException(e);
        }

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        return element.getAsJsonArray();
    }


    public LeagueId getLeagueId(String leagueSlug, String provinceCode) {
        for (LeagueId id : getLeagueIds(provinceCode)) {
            if (id.getId().equalsIgnoreCase(leagueSlug)) return id;
        }
        throw new RuntimeException("could not find league with id " + leagueSlug);
    }
}
