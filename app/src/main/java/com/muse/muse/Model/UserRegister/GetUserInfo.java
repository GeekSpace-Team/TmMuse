package com.muse.muse.Model.UserRegister;

import java.util.ArrayList;

public class GetUserInfo {
    private Boolean error;
    private ArrayList <GetUserInfoBody> body = new ArrayList<>();

    public GetUserInfo(Boolean error, ArrayList<GetUserInfoBody> body) {
        this.error = error;
        this.body = body;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public ArrayList<GetUserInfoBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<GetUserInfoBody> body) {
        this.body = body;
    }
}
