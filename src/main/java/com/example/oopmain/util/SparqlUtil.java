package com.example.oopmain.util;

public class SparqlUtil {
public static String createOptional(String subQuery){
    return "OPTIONAL {" + subQuery + "}.";
}
}
