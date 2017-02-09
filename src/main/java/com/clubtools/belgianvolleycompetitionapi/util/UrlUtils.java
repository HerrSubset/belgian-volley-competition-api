package com.clubtools.belgianvolleycompetitionapi.util;


public class UrlUtils {
    public static String sluggify(String name) {
        String slug = name.toLowerCase().replace(' ', '-');
        slug = slug.replace(".", "");
        slug = slug.replace("'", "");
        return slug;
    }
}
