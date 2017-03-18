package com.example.lwl.retrofit2;

import java.util.List;

/**
 * Created by LWL on 2017/2/20.
 */

public class ResultDataResponse {
    private String resultcode;
    private String reason;
    private List<Result> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
