package com.example.dayout.models.Error;

import java.io.Serializable;

public class ErrorBody implements Serializable {

    public static class Data{
        public String error;

        @Override
        public String toString() {
            return "Data{" +
                    "error='" + error + '\'' +
                    '}';
        }
    }

    public boolean success;
    public String message;
    public Data data;


    @Override
    public String toString() {
        return "ErrorBody{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
