package com.commonUtils;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ExtentPropertiesUpdater {

	public static String reportPath;
    public static void updateExtenthtmlProperties(String browser) {
        Properties properties = new Properties();
        String propertiesFilePath = "src/test/resources/extent.properties";
        try (FileInputStream input = new FileInputStream(propertiesFilePath)) {
            // Load existing properties
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Generate the timestamp
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
         reportPath = "AllReports/reports/report_"+browser+ timeStamp + ".html";

        // Set new properties or overwrite existing ones
        properties.setProperty("extent.reporter.spark.out", reportPath);

        // Save updated properties to the same file
        try (OutputStream output = new FileOutputStream(propertiesFilePath)) {
            properties.store(output, "saved ");
            WebGeneralActions.userWait(5000);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public static void updateExtentPdfProperties(String browser) {
        Properties properties = new Properties();
        String propertiesFilePath = "src/test/resources/extent.properties";
        try (FileInputStream input = new FileInputStream(propertiesFilePath)) {
            // Load existing properties
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Generate the timestamp
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        String reportPath = "AllReports/reports/report_"+browser+ timeStamp + ".pdf";

        // Set new properties or overwrite existing ones
        properties.setProperty("extent.reporter.pdf.out", reportPath);

        // Save updated properties to the same file
        try (OutputStream output = new FileOutputStream(propertiesFilePath)) {
            properties.store(output, "saved ");
            WebGeneralActions.userWait(5000);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}