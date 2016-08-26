package com.roscasend.android.virtualphonecaller.business.util;

import android.util.Log;

import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class BeanUtils {

    private static String NO_SPACE_DATE_FORMAT = "yyyy_MM_dd-HH-mm-ss";
    public static SimpleDateFormat noSpaceDateFormatter = new SimpleDateFormat(NO_SPACE_DATE_FORMAT);

public static void writeCsvFileWithApache2(List<TelephoneNumberBean> emps, String fileName){
    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Output_Numbers/";
    String csvfileNameWithExtension =  fileName+ "_" + noSpaceDateFormatter.format(new Date()) + "_"  + ".csv";

    String filePath = baseDir + File.separator + csvfileNameWithExtension;

    File file  = new File (baseDir);
    boolean success = false;
    if (!file.exists()) {
        file.mkdir();
    }

    if (success) {
        LogS.i("directorula  fost create " + file.getPath());
    } else {
        LogS.i("directorula  nu afost create " + file.getPath());

    }

    //Delimiter used in CSV file
    final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    final Object [] FILE_HEADER = {"Judet","Numar","Este valid","A fost sunat"};

    FileWriter fileWriter = null;

        CSVPrinter csvFilePrinter = null;

        //Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try {

            //initialize FileWriter object
            fileWriter = new FileWriter(filePath);

            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            //Write a new student object list to the CSV file
            for (TelephoneNumberBean student : emps) {
                List<String> studentDataRecord = new ArrayList<String>();
              //  studentDataRecord.add(String.valueOf(student.getId()));
                studentDataRecord.add(student.getCountry());
                studentDataRecord.add(student.getNumber());
                studentDataRecord.add(String.valueOf(student.isValid()));
                studentDataRecord.add(String.valueOf(student.isCalled()));

                csvFilePrinter.printRecord(studentDataRecord);
            }

            LogS. i("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            LogS.e(e.getMessage(), e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
            }
        }
    }
}

