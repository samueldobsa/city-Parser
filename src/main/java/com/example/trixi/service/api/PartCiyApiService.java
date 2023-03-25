package com.example.trixi.service.api;

import com.example.trixi.entity.PartCity;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.trixi.util.Util.inputXml;

@Service
public class PartCiyApiService {

    public static List<PartCity> parsePartCities() throws UnirestException, IOException {
        List<PartCity> partCities = new ArrayList<>();
        Document document = Jsoup.parse(inputXml());
        document.getElementsByTag("vf:CastObce").forEach(element -> {
            Elements codeElement = element.getElementsByTag("coi:Kod");
            Elements nameElement = element.getElementsByTag("coi:Nazev");
            Elements codeCityElement = element.getElementsByTag("obi:Kod");
            partCities.add(new PartCity(codeElement.text(), nameElement.text(), codeCityElement.text()));
        });
        return partCities;
    }
}
