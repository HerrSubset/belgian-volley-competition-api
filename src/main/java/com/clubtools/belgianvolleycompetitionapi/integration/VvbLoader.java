package com.clubtools.belgianvolleycompetitionapi.integration;

import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import com.clubtools.belgianvolleycompetitionapi.domain.Team;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class VvbLoader implements FederationLoader {

    private final static String API_URL = "http://volleybieb.be/AjaxVVBWedstijden.php";
    private final static String WAT_TE_DOEN = "wattedoen";
    private final static String REEKS_TYPE = "reekstype";
    private final static String PROVINCIE = "provincie";
    private final static String REEKSNAAM = "reeksnaam";
    private static final String REEKSAFKORTING = "reeksafkorting";


    @Override
    public List<LeagueId> getLeagueIds() {

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair(WAT_TE_DOEN, "2"));
        params.add(new BasicNameValuePair(REEKS_TYPE, "1"));
        params.add(new BasicNameValuePair(PROVINCIE, "0"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        // perform request
        String json;
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            json = CharStreams.toString(new InputStreamReader(entity.getContent()));
        } catch (IOException e) {
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


        return ids;
    }

    @Override
    public League getLeague(String leagueSlug) {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("VVBB example team 1 - " + leagueSlug));
        teams.add(new Team("VVBB example team 2 - " + leagueSlug));
        teams.add(new Team("VVBB example team 3 - " + leagueSlug));
        teams.add(new Team("VVBB example team 4 - " + leagueSlug));

        return new League(teams);
    }
}
