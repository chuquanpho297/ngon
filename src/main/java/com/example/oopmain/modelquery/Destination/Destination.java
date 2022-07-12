package com.example.oopmain.modelquery.Destination;

import com.example.oopmain.modelquery.General;
import com.example.oopmain.util.SparqlUtil;

public class Destination extends General {
    private final String point;
    private final String location;

    public Destination() {
        super();
        this.point = "?data georss:point ?point.";
        this.location = "?data dbp:location ?location.";
    }

    public String getPoint() {
        return point;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES() +
                """
                PREFIX georss: <http://www.georss.org/georss/>
                PREFIX dbp: <http://dbpedia.org/property/>
                """;
    }

    public String createConstruct() {
        return super.createConstruct() +
                this.getPoint() +
                this.getLocation();
    }

    public String createWhere() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getPoint()) +
                SparqlUtil.createOptional(this.getLocation());
    }
}

