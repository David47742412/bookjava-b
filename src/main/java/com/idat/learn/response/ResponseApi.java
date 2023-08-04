package com.idat.learn.response;

import java.util.List;

public class ResponseApi<T> {
    public int statusCode;
    public String message;
    public List<T> body;
}
