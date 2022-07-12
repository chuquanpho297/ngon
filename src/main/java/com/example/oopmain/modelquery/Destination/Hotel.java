package com.example.oopmain.modelquery.Destination;

import com.example.oopmain.util.SparqlUtil;

public class Hotel extends Destination {
    private final String numberOfRooms;

    public Hotel() {
        super();
        this.numberOfRooms = "?data dbo:numberOfRooms ?numberOfRooms.";
        this.dataSource =
                """
                { ?data dct:subject dbc:Hotels_in_Vietnam. }
                UNION
                { ?data dct:subject dbc:Hotels_in_Hanoi. }
                UNION
                { ?data dct:subject dbc:Hotels_in_Ho_Chi_Minh_City. }
                """;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES() +
                "PREFIX dbo: <http://dbpedia.org/ontology/>";
    }

    public String createConstructBody() {
        return super.createConstruct() +
               this.getNumberOfRooms();
    }

    public String createWhereBody() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getNumberOfRooms());
    }
}
