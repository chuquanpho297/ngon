package com.example.oopmain.rdf;

public interface IQueryHandler {
    String constructQuery(String topic1);
    String QuerySqarqlSelect(String querySelect, String queryAdd, String formatFile);
    String QuerySqarqlConstruct(String queryConstruct, String queryAdd, String formatFile);
}
