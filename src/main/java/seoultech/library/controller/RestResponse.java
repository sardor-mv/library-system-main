package seoultech.library.controller;

import com.google.common.base.Strings;

import java.util.HashMap;

public class RestResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 1l;

    public static RestResponse ok() {
        return new RestResponse(200, "success");
    }

    public static RestResponse result(int code, String message) {
        return new RestResponse(code, message);
    }
    private RestResponse(int code, String message) {
        this.put("code", code);

        if(!Strings.isNullOrEmpty(message))
            this.put("message", message);
    }
}
