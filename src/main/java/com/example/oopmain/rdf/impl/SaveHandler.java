package com.example.oopmain.rdf.impl;

import com.example.oopmain.constant.DirConstant;
import com.example.oopmain.rdf.ISaveHandler;
import com.example.oopmain.util.QueryUtility;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import java.io.FileWriter;

@Dependent
public class SaveHandler implements ISaveHandler {
    public String SaveFileToDir(String data,String formatFile,String fileName){
        String fileExtend = QueryUtility.checkFileExtend(formatFile);
        String fileDir = DirConstant.CUR_DIR + "\\" + "Data" + "\\" + fileName + fileExtend;
        try (FileWriter fileWriter = new FileWriter(fileDir)) {
            if(data!= null){
                fileWriter.write(data);
                return "Done !";
            }
            else throw new Exception("Not found data");

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
