package com.example.oopmain.modelquery.Destination;

import com.example.oopmain.util.SparqlUtil;

public class School extends Destination {
    private final String province;
    public School() {
        super();
        this.province = "?data dbp:province ?province.";
        this.dataSource =
                """
                 ?data dct:subject dbc:Schools_in_Vietnam. 
               
                """;
    }
    public String getProvince() {
        return province;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES();
    }

    public String createConstructBody() {
        return super.createConstruct() +
                this.getProvince();
    }

    public String createWhereBody() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getProvince());
    }
}

