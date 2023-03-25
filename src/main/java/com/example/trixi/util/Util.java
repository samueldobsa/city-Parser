package com.example.trixi.util;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

public class Util {

    public static String inputXml() throws IOException, UnirestException {
        HttpResponse<InputStream> response = Unirest.get("https://www.smartform.cz/download/kopidlno.xml.zip").asBinary();
        List<String> xmlFiles = getXMLFilesFromZIPInputStream(response.getBody());
        if (!xmlFiles.isEmpty()){
            String xml = xmlFiles.get(0);
            return xml;
        }
        return null;
    }

    public static List<String> getXMLFilesFromZIPInputStream(InputStream inputStream) throws IOException {
        List<String> files = new ArrayList<>();

        ZipInputStream zis = new ZipInputStream(inputStream);
        while ((zis.getNextEntry()) != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zis.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
            }
            InputStream fileInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            if (isFileXML(fileInputStream)) {
                files.add(convertXMLInputStreamToString(fileInputStream));
            }
            fileInputStream.close();
            outputStream.close();
        }
        zis.closeEntry();

        return files;
    }

    public static boolean isFileXML(InputStream inputStream) throws IOException {
        byte[] header = new byte[5];
        inputStream.mark(5);
        inputStream.read(header, 0, 5);
        inputStream.reset();
        return Arrays.equals(header, "<?xml".getBytes());
    }

    public static String convertXMLInputStreamToString(InputStream inputStream) {
        String content = "";
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            content = scanner.useDelimiter("\\A").next();
        }
        return content;
    }
}
