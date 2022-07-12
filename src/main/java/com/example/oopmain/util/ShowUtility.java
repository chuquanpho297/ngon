package com.example.oopmain.util;

import org.apache.jena.riot.Lang;

public class ShowUtility {

    public static Lang getFormatFile(String format){
        Lang formatFile = null;
        switch (format){
            case "Turtle" -> formatFile = Lang.TURTLE;
            case "RDF/XML" -> formatFile = Lang.RDFXML;
            case "N3" -> formatFile = Lang.N3;
            case "JSONLD" -> formatFile = Lang.JSONLD;
        }
        return formatFile;
    }
}
