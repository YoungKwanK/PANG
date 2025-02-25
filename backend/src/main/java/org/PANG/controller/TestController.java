package org.PANG.controller;

import org.PANG.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello() {
        System.out.println("접속!!");
        return "테스트입니다.";
    }

    @PostMapping("/login")
    public Response login(@RequestBody Code code) {
        System.out.println("접속!!");

        return Response.ok(code.getCredential());
    }

    static class Code {

        String clientId;
        String credential;
        String select_by;

        public Code(String clientId, String credential, String select_by) {
            this.clientId = clientId;
            this.credential = credential;
            this.select_by = select_by;
        }

        public String getCredential() {
            return credential;
        }
    }
}


