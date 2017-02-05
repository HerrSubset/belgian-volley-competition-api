package com.clubtools.belgianvolleycompetitionapi.integration;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class KovvLoader implements FederationLoader {

    private final static String API_HOST = "volleybieb.be";
    private final static String API_PATH = "/AjaxVVBWedstijden.php";
    private final static String ACTION = "wattedoen";
    private final static String ACTION_FIND_LEAGUES = "2";
    private final static String REEKS_TYPE = "reekstype";
    private final static String PROVINCIE = "provincie";
    private final static String REEKSNAAM = "reeksnaam";
    private static final String REEKSAFKORTING = "reeksafkorting";

    @Override
    public List<LeagueId> getLeagueIds() {
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
        HttpPost httpPost = new HttpPost(uri);

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair(ACTION, ACTION_FIND_LEAGUES));
        params.add(new BasicNameValuePair(REEKS_TYPE, "1"));
        params.add(new BasicNameValuePair(PROVINCIE, "6"));
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
    public League getLeague(LeagueId leagueId) {
        return null;
    }

    @Override
    public LeagueId getLeagueId(String leagueSlug) {
        return null;
    }
}
