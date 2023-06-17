package com.example.restapiproject.service;

import com.example.restapiproject.controller.DataResponse;
import com.example.restapiproject.model.User;
import com.example.restapiproject.repository.UserRepository;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;

    private RestTemplate restTemplate;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getListUserById(long id) {
//        return userRepository.findByIdAndInActiveFalse(id);
        return userRepository.findByIdAndInActive(id,"N");

    }

    public User create(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User updateUser(long id, User user) {
        User getDataUser=getListUserById(id);
        getDataUser.setUserName(user.getUserName());
        getDataUser.setPassword(user.getPassword());
        getDataUser.setInActive(user.getInActive());
        userRepository.saveAndFlush(getDataUser);
        return getDataUser;
    }

    public User delete(long id) {
        User getDataUser=getListUserById(id);
        getDataUser.setInActive("Y");
        userRepository.saveAndFlush(getDataUser);
        return getDataUser;
    }

   /* public JsonObject invitationGet(String idToken, String email) {
        String createPersonUrl = accountsApi + "/authentication/secured/admin/invitation-get?email="+email;


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", idToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>( headers);

        ResponseEntity<String> personResultAsJsonStr = restTemplate.exchange(
                createPersonUrl, HttpMethod.GET, request, String.class);

        JsonObject result = null;
        if(personResultAsJsonStr != null && personResultAsJsonStr.getBody() != null) {
            Gson gson = new Gson();
            result = gson.fromJson(personResultAsJsonStr.getBody(), JsonObject.class);
        }

        return result;
    }*/

    public DataResponse test(){
        restTemplate = new RestTemplate();
        String api="http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
        DataResponse dataResponse = restTemplate.getForObject(api, DataResponse.class);
        System.out.println("dataResponse : []"+dataResponse);
        return dataResponse;
    }
}
