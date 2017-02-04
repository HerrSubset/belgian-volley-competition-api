package com.clubtools.belgianvolleycompetitionapi.util;

/**
 * @Author: HerrSubset
 * @Created: 2/4/17
 */
public class UrlUtils {
    public static String sluggify(String name) {
        String slug = name.toLowerCase().replace(' ', '-');
        return slug;
    }
}
