package com.unitTest.Constants.Dto.Response;

import lombok.Data;

@Data
public class ApiResponse {
    private String Description;
    private boolean success;
    private Object data;

}
