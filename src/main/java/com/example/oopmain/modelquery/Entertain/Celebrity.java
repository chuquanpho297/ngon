package com.example.oopmain.modelquery.Entertain;

import com.example.oopmain.modelquery.General;
import com.example.oopmain.util.SparqlUtil;


public class Celebrity extends General {

    private final String POINT;
    private final String NATIONALITY;

    public Celebrity() {
        super();
        this.POINT = "?data georss:point ?point.";
        this.NATIONALITY = "?data dbo:nationality ?nationality.";
    }

    public String getPoint() {
        return POINT;
    }

    public String getNationality() {
        return NATIONALITY;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES() +
                """                
                        PREFIX georss: <http://www.georss.org/georss/>
                        PREFIX dbp: <http://dbpedia.org/property/>
                        PREFIX dbo:    <http://dbpedia.org/ontology/>
                        """;
    }

    @Override
    public String createConstruct() {
        return super.createConstruct() +
                this.getPoint() + "\n" +
                this.getNationality();
    }

    @Override
    public String createWhere() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getPoint()) + "\n" +

                SparqlUtil.createOptional(this.getNationality());
    }

}

