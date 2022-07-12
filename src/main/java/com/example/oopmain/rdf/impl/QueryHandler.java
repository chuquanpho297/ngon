package com.example.oopmain.rdf.impl;

import com.example.oopmain.modelquery.Destination.Dam;
import com.example.oopmain.modelquery.Destination.Hotel;
import com.example.oopmain.modelquery.Destination.School;
import com.example.oopmain.modelquery.Entertain.FemaleSinger;
import com.example.oopmain.modelquery.Entertain.Footballer;
import com.example.oopmain.modelquery.Entertain.MalePoet;
import com.example.oopmain.modelquery.Entertain.MaleSinger;
import com.example.oopmain.modelquery.People.Astronaut;
import com.example.oopmain.modelquery.People.Businessman;
import com.example.oopmain.modelquery.People.Engineer;
import com.example.oopmain.modelquery.People.Politician;
import com.example.oopmain.rdf.IQueryHandler;
import com.example.oopmain.util.QueryUtility;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.resultset.ResultsFormat;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

public class QueryHandler implements IQueryHandler {

    public String QuerySqarqlSelect(String querySelect, String queryAdd, String formatFile) {
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
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String QuerySqarqlConstruct(String queryConstruct, String queryAdd, String format) {
        String sparqlUrl = QueryUtility.checkQueryAdd(queryAdd);
        ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
        queryStr.append(queryConstruct);
        Query query = queryStr.asQuery();
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlUrl, query)) {
            qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
            Model model = qexec.execConstruct();
            StringWriter out = new StringWriter();
            model.write(out, format);
            return out.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String constructQuery(String topic) {
        String query = "";
        switch (topic) {
            case "Astronaut" -> query = new Astronaut().createConstructQuery();
            case "Engineer" -> query = new Engineer().createConstructQuery();
            case "Politician" -> query = new Politician().createConstructQuery();
            case "Dam" -> query = new Dam().createConstructQuery();
            case "Hotel" -> query = new Hotel().createConstructQuery();
            case "School" -> query = new School().createConstructQuery();
            case "FemaleSinger" -> query = new FemaleSinger().createConstructQuery();
            case "Footballer" -> query = new Footballer().createConstructQuery();
            case "MalePoet" -> query = new MalePoet().createConstructQuery();
            case "MaleSinger" -> query = new MaleSinger().createConstructQuery();
            case "Businessman" -> query = new Businessman().createConstructQuery();
        }
        return query;
    }
}
