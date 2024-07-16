package com.queined.literalura.service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The RequestHandler class is responsible for sending HTTP GET requests and
 * returning the response body as a string.
 */
public class RequestHandler {
    /**
     * Sends an HTTP GET request to the specified URL and returns the response body
     * as a string.
     *
     * @param url the URL to send the GET request to
     * @return the response body as a string, or null if an exception occurs
     */
    public String get(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
