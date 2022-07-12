package com.example.oopmain.modelquery.People;


import com.example.oopmain.util.SparqlUtil;

public class Engineer extends People {
	private String EDUCATION;
	private String NATIONALITY;

	public Engineer() {
	  super();
	  this.EDUCATION = "?data dbo:education ?education.";
	  this.NATIONALITY = "?data dbo:nationality ?nationality.";
	  this.dataSource = "?data dct:subject dbc:Vietnamese_engineers.";
	}

	public String getDataSource() {
		return dataSource;
	}

	public String getEducation() {
		return EDUCATION;
	}

	public String getNationality() {
		return NATIONALITY;
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