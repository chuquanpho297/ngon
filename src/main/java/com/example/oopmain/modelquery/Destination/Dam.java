package com.example.oopmain.modelquery.Destination;


import com.example.oopmain.util.SparqlUtil;

public class Dam extends Destination{
    private final String river;
    private final String operator;
    private final String openingYear;

    public Dam() {
        super();
        this.dataSource = "?data dct:subject dbc:Dams_in_Vietnam.";
        this.river = "?data dbo:river ?river.";
        this.openingYear = "?data dbo:openingYear ?openingYear.";
        this.operator = "?data dbp:operator ?operator.";
    }

    public String getRiver() {
        return river;
    }

    public String getOperator() {
        return operator;
    }

    public String getOpeningYear() {
        return openingYear;
    }

    @Override
    public String getPREFIXES() {
        return super.getPREFIXES() +
                "PREFIX dbo: <http://dbpedia.org/ontology/>";
    }

    public String createConstructBody() {
        return super.createConstruct() +
                this.getRiver() + "\n" +
                this.getOpeningYear() + "\n" +
                this.getOperator() + "\n";
    }

    public String createWhereBody() {
        return super.createWhere() +
                SparqlUtil.createOptional(this.getOpeningYear()) +
                SparqlUtil.createOptional(this.getOperator()) +
                SparqlUtil.createOptional(this.getRiver());
    }
}
