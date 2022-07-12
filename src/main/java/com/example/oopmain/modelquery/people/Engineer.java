package com.example.oopmain.modelquery.people;


import com.example.oopmain.util.SparqlUtil;

public class Engineer extends People {
	private String education;
	private String nationality;

	public Engineer() {
	  super();
	  this.education = "?data dbo:education ?education.";
	  this.nationality = "?data dbo:nationality ?nationality.";
	  this.dataSource = "?data dct:subject dbc:Vietnamese_engineers.";
	}

	public String getDataSource() {
		return dataSource;
	}

	public String getEducation() {
		return education;
	}

	public String getNationality() {
		return nationality;
	}

	protected String createConstruct() {
	    return super.createConstruct() + "\n" +
	            this.getEducation() + "\n" +
	            this.getNationality() + "\n" ;
	}

	protected String createWhere() {
	    return super.createWhere() +
				SparqlUtil.createOptional(this.getEducation()) + "\n" +
				SparqlUtil.createOptional(this.getNationality()) + "\n" ;
	}
}