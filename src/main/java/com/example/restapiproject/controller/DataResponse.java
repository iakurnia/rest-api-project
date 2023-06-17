package com.example.restapiproject.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DataResponse {

/*    private String message;
    private String status;*/
//    private List<DataRs> data;
    private List<DataRs> data;
}
