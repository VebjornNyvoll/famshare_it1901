package famshare.jsoncore;

import java.lang.reflect.Array;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;


public class HTTPCaller {
    public Calendar getCalendarFromAPI() {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

                try {
                    HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:8081/calendar"))
                    .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    Calendar calendar = new ObjectMapper().readValue(response.body(), Calendar.class);
                    System.out.println(calendar);
                    return calendar;
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
    }
    
    public void postBookingToAPI(Booking booking) {
        String url = "http://localhost:8081/calendar/";
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
                try {
                    
                    HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(booking)))
                    .header("Content-Type", "application/json") 
                    .uri(URI.create(url))
                    .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    public void deleteBookingFromAPI(int bookingId) {
        String url = "http://localhost:8081/calendar/" + bookingId;
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

                try {
                    HttpRequest request = HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create(url))
                    .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

}
