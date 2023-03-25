package com.example.trixi.service.api;

import com.example.trixi.entity.City;
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
public class CityApiService {

    public static List<City> parseCities() throws UnirestException, IOException {
        List<City> cities = new ArrayList<>();
        Document document = Jsoup.parse(inputXml());
        document.getElementsByTag("vf:Obec").forEach(element -> {
            Elements nameElement = element.getElementsByTag("obi:Nazev");
            Elements codeElement = element.getElementsByTag("obi:Kod");
            cities.add(new City(nameElement.text(), codeElement.text()));
        });
        return cities;
    }

}

