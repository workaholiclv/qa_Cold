package ticketReservationCheck;

import ticketReservationCheck.model.ReservationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class ReservationRequester {

    private final String GET_RESERVATION_LIST = "http://qaguru.lv:8090/tickets/getReservations.php";
    private final String DELETE_RESERVATION = "http://qaguru.lv:8090/tickets/delete.php?id=";

    RestTemplate restTemplate = new RestTemplate();

    public ReservationResponse getReservationList() throws IOException  {

        String responseToParse = restTemplate.getForEntity(GET_RESERVATION_LIST, String.class).getBody();

        responseToParse = "{\"reservation\":" + responseToParse + "}";

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseToParse, ReservationResponse.class);
    }

    public void deleteReservation(String id) {
        restTemplate.delete(DELETE_RESERVATION + id);
    }
}
