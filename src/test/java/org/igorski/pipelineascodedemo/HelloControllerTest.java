package org.igorski.pipelineascodedemo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    @Test
    public void shouldGetCorrectHelloMessage() {
        HelloController helloController = new HelloController();
        ResponseEntity<String> response = helloController.helloJenkins();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "Hello Jenkins!");
    }

}