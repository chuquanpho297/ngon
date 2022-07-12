package com.example.oopmain.rdf.impl;

import com.example.oopmain.rdf.ILoadHandler;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;

import java.io.StringWriter;

public class LoadHandler implements ILoadHandler {
    public String LoadFile(String format, String fileDir) {
        Model model = RDFDataMgr.loadModel(fileDir);
        StringWriter out = new StringWriter();
        model.write(out, format);
        return out.toString();
    }
}
