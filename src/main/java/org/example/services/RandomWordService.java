package org.example.services;

import org.example.models.RandomWord;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class RandomWordService {
    private RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://random-word-api.herokuapp.com/word";

    public String getWord() {
        String url = API_URL + "?length=6";
        return restTemplate.getForObject(url, String.class);
    }
}
