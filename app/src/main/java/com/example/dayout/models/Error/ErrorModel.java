package com.example.dayout.models.Error;

import android.util.Pair;

import com.example.dayout.models.LoginModel;
import com.google.gson.Gson;

import java.io.Serializable;

public class ErrorModel implements Serializable {

    public class Data {
        public String error;

        @Override
        public String toString() {
            return "Data{" +
                    "error='" + error + '\'' +
                    '}';
        }
    }

    private boolean success;
    private String message;
    private Data data;

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "ErrorBody{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
