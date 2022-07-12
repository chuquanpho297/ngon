package com.example.oopmain.rdf.impl;

import com.example.oopmain.modelquery.people.Astronaut;
import com.example.oopmain.modelquery.people.Engineer;
import com.example.oopmain.modelquery.people.Politician;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.util.QueryUtility;
import org.apache.jena.query.*;
import org.apache.jena.sparql.resultset.ResultsFormat;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

@Dependent
public class QueryHandler implements IQueryHandler {

    public String QuerySqarql(String querySelect, String queryAdd, String formatFile) {
            ResultsFormat format = QueryUtility.checkFormat(formatFile);
            String sparqlUrl = QueryUtility.checkQueryAdd(queryAdd);
            ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
            QueryUtility.setNsPrefix(queryStr);
            queryStr.append(querySelect);
            Query query = queryStr.asQuery();
            try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlUrl, query)) {
                qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
                ResultSet results = qexec.execSelect();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ResultSetFormatter.output(outputStream, results, format);
                return new String(outputStream.toByteArray());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            return null;
    }

    public String constructQuery(String topic){
        String query = "test";
        System.out.println(query);
        switch(topic){
            case "Astronaut"->query = new Astronaut().createConstructQuery();
            case "Engineer"->query = new Engineer().createConstructQuery();
            case "Politician"-> query = new Politician().createConstructQuery();
        }
        return query;
    }
}
