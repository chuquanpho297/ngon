package com.example.oopmain.modelquery.people;

public class Politician extends People {

	public Politician() {
	  super();
	  this.dataSource = "?data dct:subject dbc:Members_of_the_Politburo_of_the_Communist_Party_of_Vietnam";
	}

	protected String createConstruct() {
	    return super.createConstruct() + "\n" ;
	}

	protected String createWhere() {
	    return super.createWhere() ;
	}
}



