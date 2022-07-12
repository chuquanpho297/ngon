package com.example.oopmain.modelquery;

import com.example.oopmain.util.SparqlUtil;

public class General implements IGeneral {
    private String labelFilter;
    private String commentFilter;
    private String label;
    private String comment;
    private String subject;
    private String name;

    protected String dataSource;

    public General() {
        this.commentFilter = "FILTER(langMatches(lang(?comment), \"en\"))";
        this.labelFilter = "FILTER(langMatches(lang(?label), \"en\"))";
        this.comment = "?data rdfs:comment ?comment.";
        this.subject = "?data dct:subject ?subject.";
        this.label = "?data rdfs:label ?label.";
        this.name = "?data foaf:name ?name.";
    }

    public String getLabelFilter() {
        return labelFilter;
    }

    public String getCommentFilter() {
        return commentFilter;
    }

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    public String getSubject() {
        return subject;
    }

    public String getName() {
        return name;
    }

    public String getDataSource() {
        return dataSource;
    }

    protected String getPREFIXES() {
        return """
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                PREFIX dct: <http://purl.org/dc/terms/>
                PREFIX dbc: <http://dbpedia.org/resource/Category:>
                PREFIX foaf: <http://xmlns.com/foaf/0.1/>
                """;
    }

    protected String createConstruct() {
        return this.getComment() +
                this.getName() +
                this.getLabel() +
                this.getSubject() + "\n";
    }

    protected String createSelect(){
        //Update soon
        return null;
    }
    protected String createWhere() {
        return this.getComment() +
                this.getLabel() +
                this.getSubject() +
                this.getLabelFilter() +
                this.getCommentFilter() +
                SparqlUtil.createOptional(this.getName()) +
                this.getDataSource() + "\n";
    }

    public String createConstructQuery() {
        return getPREFIXES() + "CONSTRUCT {" + createConstruct() + "}" + "WHERE {" + createWhere() + "}";
    }

    public String createSelectQuery() {
        return getPREFIXES() + "SE {" + createSelect() + "}" + "WHERE {" + createWhere() + "}";
    }
}
