package com.example.oopmain.modelquery.Entertain;

import com.example.oopmain.util.SparqlUtil;

public class FemaleSinger extends Celebrity {
    private final String PROVINCE;

    public FemaleSinger() {
        super();
        this.PROVINCE = "?data dbp:province ?province.";
        this.dataSource = "?data dct:subject dbc:21st-century_Vietnamese_woman_singers.";
    }

    public String getProvince() {
        return PROVINCE;
    }

    public String createConstruct() {
        return super.createConstruct() + "\n" +
                this.getProvince();
    }

    public String createWhere() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getProvince());
    }
}

