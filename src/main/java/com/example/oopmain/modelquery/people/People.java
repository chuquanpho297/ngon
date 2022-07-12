package com.example.oopmain.modelquery.people;


import com.example.oopmain.modelquery.General;
import com.example.oopmain.util.SparqlUtil;

public class People extends General {
	private String abstractProp;
	private String birthDate;
	private String deathDate;

	public People() {
	  super();
	  this.abstractProp = "?data dbo:abstract ?abstract.";
	  this.birthDate = "?data dbp:birthDate ?birthDate.";
	  this.deathDate = "?data dbp:deathDate ?deathDate.";
	}
	

	protected String getAbstractProp() {
	    return abstractProp;
	}

	protected String getBirthDate() {
		return birthDate;
	}

	protected String getDeathDate() {
		return deathDate;
	}

	@Override
	protected String getPREFIXES() {
	    return super.getPREFIXES() +
	                """                
	                PREFIX dbp: <http://dbpedia.org/property/>
	                PREFIX dbo: <http://dbpedia.org/ontology/>
	                PREFIX dbr: <http://dbpedia.org/resource/>
	                """;
	}

	protected String createConstruct() {
	    return super.createConstruct() +
	            this.getAbstractProp() + "\n" +
	            this.getBirthDate() + "\n" +
	            this.getDeathDate() + "\n" ;
	}

	protected String createWhere() {
	    return super.createWhere() +
				SparqlUtil.createOptional(this.getAbstractProp()) + "\n" +
				SparqlUtil.createOptional(this.getBirthDate()) + "\n" +
				SparqlUtil.createOptional(this.getDeathDate())+ "\n" ;
	}
}


