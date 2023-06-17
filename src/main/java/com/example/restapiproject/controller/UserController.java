package com.example.restapiproject.controller;

import com.example.restapiproject.model.User;
import com.example.restapiproject.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping(path = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        List<User> users;
        try {
            users = userService.getAll();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping(path = "/getAllDans", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsersDans() {
        List<DataResponse> users;
        try {
            users = (List<DataResponse>) userService.test();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/getAll/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsersById(@PathVariable("id") long id) {
        User users;
        try {
            users = userService.getListUserById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.create(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") long id,@RequestBody User user) throws Exception {
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("id not found");
        }
        return userService.updateUser(id,user);
    }

    @PutMapping("/delete/{id}")
    public User deleteUser(@PathVariable("id") long id) throws Exception {
        if(ObjectUtils.isEmpty(id)){
            throw new Exception("id not found");
        }
        return userService.delete(id);
    }

    @GetMapping("/getDataDans")
    public ResponseEntity<Object> getDans() {
        return ResponseEntity.ok(dans());
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Map>> getJobs() {

        List<Map> result = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String url = null;
        try {
            url = getPage("http://dev3.dansmultipro.co.id/api/recruitment/positions.json");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Map> request = restTemplate.getForObject(url, List.class);
        for (Map map : request) {
            Map m = new HashMap<>();
            m.put("id", map.get("id"));
            m.put("title", map.get("title"));
            result.add(m);
        }
        return new ResponseEntity<List<Map>>(result, HttpStatus.OK);
    }

    public static String getPage(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {  // LINE 24
            sb.append(line);
        }
        return sb.toString();
    }

    @GetMapping("/job-detail/{id}")
    public ResponseEntity<Map> getJobDetail(@PathVariable String id) {

        List<Map> result = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + id;

        Map request = restTemplate.getForObject(url, Map.class);
        return new ResponseEntity<Map>(request, HttpStatus.OK);
    }

    private ResponseEntity<DataResponse> dans() {
        String createPersonUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", idToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>( headers);

/*        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.readValue(restTemplate.exchange(
                createPersonUrl, HttpMethod.GET, request, DataResponse.class);*/
        List<Map> requestNew = restTemplate.getForObject(createPersonUrl, List.class);


        ResponseEntity<List<DataRs>> personResultAsJsonStr = restTemplate.exchange(
                createPersonUrl, HttpMethod.GET, request, new ParameterizedTypeReference<List<DataRs>>(){});

        /*List<Object> result = null;
        if(personResultAsJsonStr != null && personResultAsJsonStr.getBody() != null) {
            Gson gson = new Gson();
            result = gson.fromJson(personResultAsJsonStr.getBody(), List.class);
        }*/

        return null;
    }

}
