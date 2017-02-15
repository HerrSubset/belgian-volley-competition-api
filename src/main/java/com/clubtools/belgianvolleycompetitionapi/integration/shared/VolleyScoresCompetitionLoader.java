package com.clubtools.belgianvolleycompetitionapi.integration.shared;

import com.clubtools.belgianvolleycompetitionapi.domain.Game;
import com.clubtools.belgianvolleycompetitionapi.domain.League;
import com.clubtools.belgianvolleycompetitionapi.domain.LeagueId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class VolleyScoresCompetitionLoader {

    private final static String API_HOST = "http://www.volleyscores.be/";
    private final static String API_PATH = "/index.php?";
    private final static String API_QUERY_PARAMS = "a=sd&w=%&ssi=";


    public List<LeagueId> getLeagueIds(String provinceId) {
        Document document = null;
        try {
            document = Jsoup.connect(API_HOST).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element provinceTitle = document.select("i#" + provinceId).first();
        Element competitionOverviewList = provinceTitle.parent().nextElementSibling();
        Element leagueList = competitionOverviewList.select("li.mnuLevel2").get(1).child(1);

        Iterator<Element> iterator = leagueList.children().iterator();
        ArrayList<LeagueId> leagueIds = new ArrayList<>();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            String leagueName = next.ownText();
            String id = next.attr("onclick").split(",")[4].replace("'", "");
            leagueIds.add(new LeagueId(leagueName, id));
        }


        return leagueIds;
    }

    public LeagueId getLeagueId(String leagueSlug, String provinceId) {
        for (LeagueId id : getLeagueIds(provinceId)) {
            if (id.getId().equalsIgnoreCase(leagueSlug)) return id;
        }
        throw new RuntimeException("could not find league with id " + leagueSlug);
    }

    public League getLeague(LeagueId leagueId, String provinceId) {
        String url = API_HOST + API_PATH + API_QUERY_PARAMS + leagueId.getId();
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element gameTableBody = document.select("table.table tbody").get(2);

        ArrayList<Game> games = new ArrayList<>();
        Iterator<Element> iterator = gameTableBody.children().iterator();
        while(iterator.hasNext()) {
            Element e = iterator.next();

            String code = e.child(1).text().trim();
            String day = e.child(3).text().trim();
            String hour = e.child(4).text().trim();
            String homeTeam = e.child(5).text().trim();
            String awayTeam = e.child(6).text().trim();
            String sportsHall = e.child(7).text().trim();
            String result = e.child(8).text().trim();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date;
            try {
                String fullDateString = day + " " + hour;
                date = format.parse(fullDateString);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            Integer homeSets = null;
            Integer awaySets = null;
            if (result.length() == 5) {
                homeSets = Integer.parseInt(result.substring(0, 1));
                awaySets = Integer.parseInt(result.substring(4, 5));
            }

            games.add(new Game(homeTeam, awayTeam, date, homeSets, awaySets));
        }

        return new League(leagueId, games);
    }
}
