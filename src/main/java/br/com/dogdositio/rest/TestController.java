package br.com.dogdositio.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeferson.oliveira on 19/02/2018.
 */
@RestController
public class TestController {

    @GetMapping(value = {"/test/{pathParam}", "/hello/{pathParam}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> testEndpoint (@PathVariable String pathParam, @RequestParam(required = false) String requestParam) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("requestDate", new Date());
        responseBody.put("pathParam", pathParam);
        if(requestParam != null && !requestParam.trim().isEmpty()) {
            responseBody.put("requestParam", requestParam);
        }
        return responseBody;
    }
}
