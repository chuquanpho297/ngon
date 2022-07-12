package com.example.oopmain.util;

import com.example.oopmain.constant.PrefixConstant;
import com.example.oopmain.constant.QueryUrlConstant;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.resultset.ResultsFormat;

public class QueryUtility {
	public static void setNsPrefix(ParameterizedSparqlString queryStr) {
		queryStr.setNsPrefix("wikibase", PrefixConstant.WIKIBASE);
		queryStr.setNsPrefix("bd", PrefixConstant.BD);
		queryStr.setNsPrefix("dbp", PrefixConstant.DBP);
		queryStr.setNsPrefix("rdf", PrefixConstant.RDF);
		queryStr.setNsPrefix("wd", PrefixConstant.WD);
		queryStr.setNsPrefix("wdt", PrefixConstant.WDT);
		queryStr.setNsPrefix("yago", PrefixConstant.YAGO);
		queryStr.setNsPrefix("schema", PrefixConstant.SCHEMA);
		queryStr.setNsPrefix("dbo", PrefixConstant.DBO);
		queryStr.setNsPrefix("xsd", PrefixConstant.XSD);
		queryStr.setNsPrefix("foaf", PrefixConstant.FOAF);
		queryStr.setNsPrefix("rs", PrefixConstant.RS);
		queryStr.setNsPrefix("pq",PrefixConstant.PQ);
		queryStr.setNsPrefix("p",PrefixConstant.P);
	}
	public static void setNsPrefix(Model model) {
		model.setNsPrefix("wikibase", PrefixConstant.WIKIBASE);
		model.setNsPrefix("bd", PrefixConstant.BD);
		model.setNsPrefix("dbp", PrefixConstant.DBP);
		model.setNsPrefix("rdf", PrefixConstant.RDF);
		model.setNsPrefix("wd", PrefixConstant.WD);
		model.setNsPrefix("wdt", PrefixConstant.WDT);
		model.setNsPrefix("yago", PrefixConstant.YAGO);
		model.setNsPrefix("schema", PrefixConstant.SCHEMA);
		model.setNsPrefix("dbo", PrefixConstant.DBO);
		model.setNsPrefix("xsd", PrefixConstant.XSD);
		model.setNsPrefix("foaf", PrefixConstant.FOAF);
		model.setNsPrefix("rs", PrefixConstant.RS);
		model.setNsPrefix("pq",PrefixConstant.PQ);
		model.setNsPrefix("p",PrefixConstant.P);
	}

	public static ResultsFormat checkFormat(String format){
		ResultsFormat resultsFormat = null;
		System.out.println(format.length());
		switch (format) {
			case "Turtle" -> resultsFormat = ResultsFormat.FMT_RDF_TURTLE;
			case "N-Triples" -> resultsFormat = ResultsFormat.FMT_RDF_NT;
			case "RDF/XML" -> resultsFormat = ResultsFormat.FMT_RDF_XML;
			case "JSON-LD" -> resultsFormat = ResultsFormat.FMT_RS_JSON;
			default -> {
			}
		}
		System.out.println(resultsFormat);
		return resultsFormat;
	}

	public static String checkQueryAdd(String queryAdd){
		String sparqlUrl = null;
		System.out.println(queryAdd.length());
		if(queryAdd.equals("Wikidata")){
			sparqlUrl = QueryUrlConstant.WIKIDATA_QUERYURL;
		}
		if (queryAdd.equals("Dbpedia")){
			sparqlUrl = QueryUrlConstant.DBPEDIA_QUERYURL;
		}
		System.out.println(sparqlUrl);
		return sparqlUrl;
	}
	public static String checkFileExtend(String formatFile){
		String fileExtend;
		System.out.println(formatFile.length());
		fileExtend = switch (formatFile) {
			case "Turtle" -> ".ttl";
			case "N-Triples" -> ".nt";
			case "RDF/XML" -> ".rdf";
			case "JSON" -> ".json";
			default -> ".txt";
		};
		System.out.println(fileExtend);
		return fileExtend;
	}
}
