package com.example.restapiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recruitment/positions")
public class RecruitmentController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<DataRs>> positions(){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions.json").build().encode();
        ResponseEntity<List<DataRs>> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, new ParameterizedTypeReference<List<DataRs>>(){});

//        List<Posistions> list = (List<Posistions>) new Gson().fromJson(String.valueOf(responseEntity),Posistions.class);
//        Optional<Posistions> posistion = responseEntity.getBody().stream().filter(posistions -> posistions.getId().equals("32bf67e5-4971-47ce-985c-44b6b3860cdb")).findFirst();
//        return responseEntity.ok(maps);
        return responseEntity;
    }

    @GetMapping("/filter-by-id")
    public ResponseEntity<DataRs> positionsFilterById(){

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("http://dev3.dansmultipro.co.id/api/recruitment/positions.json").build().encode();
        ResponseEntity<List<DataRs>> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET,entity, new ParameterizedTypeReference<List<DataRs>>(){});

//        List<Posistions> list = (List<Posistions>) new Gson().fromJson(String.valueOf(responseEntity),Posistions.class);

        Optional<DataRs> posistion = responseEntity.getBody().stream().filter(posistions -> posistions.getId().equals("32bf67e5-4971-47ce-985c-44b6b3860cdb")).findFirst();


        return responseEntity.ok(posistion.get());
//        return responseEntity;
    }
}
