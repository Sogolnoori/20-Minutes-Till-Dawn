package com.TillDawn.Model;

public class Result {
    boolean result;
    String message;

    public Result(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResult() {
        return result;
    }
}
