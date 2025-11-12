package com.edgestackers.tote.hub.core.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClient {
    private static final java.net.http.HttpClient HTTP_CLIENT = java.net.http.HttpClient.newHttpClient();

    public HttpClient() {}

    public HttpResponse<String> postEncoded(String url, String body) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, String body) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> post(String url, String body, String bearer) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + bearer)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> get(String url) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> get(String url, String bearer) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + bearer)
                .GET()
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());    }

    public HttpResponse<String> put(String url, String body) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> delete(String url, String deleteUriPathParam) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url + "/:" + deleteUriPathParam))
                .DELETE()
                .build();
        return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
