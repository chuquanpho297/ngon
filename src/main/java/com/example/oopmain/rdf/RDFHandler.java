package com.example.oopmain.rdf;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

import com.example.oopmain.constant.DirConstant;
import com.example.oopmain.helper.Utility;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.resultset.ResultsFormat;


public class RDFHandler {
	public static String QueryAndSave(String querySelect, String fileName,String queryAdd,String formatFile) {
		ResultsFormat format = Utility.checkFormat(formatFile);
		String sparqlUrl = Utility.checkQueryAdd(queryAdd);
		String fileExtend = Utility.checkFileExtend(formatFile);
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		Utility.setNsPrefix(queryStr);
		queryStr.append(querySelect);
		Query query = queryStr.asQuery();
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlUrl, query)) {
			qexec.setTimeout(60000, TimeUnit.MILLISECONDS);
			ResultSet results = qexec.execSelect();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ResultSetFormatter.output(outputStream, results, format);
			String data = new String(outputStream.toByteArray());
			String fileDir = DirConstant.curDir + "\\" + "Data" + "\\" + fileName + fileExtend;
			try (FileWriter fileWriter = new FileWriter(fileDir)) {
				fileWriter.write(data);
				return "Done !";
			} catch (IOException e) {
				return "Don't write file!";
			}
		} catch (Exception e) {
			return "Invalid query!";
		}
	}

	public static String LoadFile(String fileName,String format) {

		String fileDir = DirConstant.curDir + "\\" + "Data" + "\\" + fileName + ".ttl";
		Model model = RDFDataMgr.loadModel(fileDir);
		StringWriter out = new StringWriter();
		model.write(out,"Turtle");
		String result = out.toString();
		return result;
//		System.out.println(model);
//		StmtIterator iter = model.listStatements();
//		while (iter.hasNext()) {
//			Statement stmt = iter.nextStatement(); // get next statement
//			Resource subject = stmt.getSubject(); // get the subject
//			Property predicate = stmt.getPredicate(); // get the predicate
//			RDFNode object = stmt.getObject(); // get the object
//
//			System.out.print(subject.toString());
//			System.out.print(" " + predicate.toString() + " ");
//			if (object instanceof Resource) {
//				System.out.print(object.toString());
//			} else {
//				// object is a literal
//				System.out.print(" \"" + object.toString() + "\"");
//			}
//
//			System.out.println(" .");
//		}

	}
}
