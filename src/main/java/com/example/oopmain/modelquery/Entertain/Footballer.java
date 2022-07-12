package com.example.oopmain.modelquery.Entertain;


import com.example.oopmain.util.SparqlUtil;

public class Footballer extends Celebrity {
    private final String province;

    public Footballer() {
        super();
        this.province = "?data dbp:province ?province.";
        this.dataSource = "?data dct:subject dbc:Vietnamese_footballers.";
    }

    public String getProvince() {
        return province;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES();
    }

    public String createConstructBody() {
        return super.createConstruct() + "\n" +
                this.getProvince();
    }

    public String createWhereBody() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getProvince());
    }
}
