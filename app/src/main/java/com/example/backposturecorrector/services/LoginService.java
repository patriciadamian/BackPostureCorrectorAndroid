package com.example.backposturecorrector.services;

import com.example.backposturecorrector.communicator.Communicator;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginService {
    static Communicator communicator = new Communicator();

    public static String login(String sUrl, String email, String password) throws JSONException, IOException {
        Map<String, String> request = new HashMap<String, String>();

        sUrl = sUrl + "/bpc/login";
        System.out.println(sUrl);
        System.out.println(communicator.buildRequest(sUrl, request));
        return communicator.login(communicator.buildRequest(sUrl, request), email, password);
    }
}
