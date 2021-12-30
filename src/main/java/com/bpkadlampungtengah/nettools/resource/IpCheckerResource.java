package com.bpkadlampungtengah.nettools.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class IpCheckerResource {

    @GetMapping("/check")
    public ResponseEntity<String> check()  {
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
            return ResponseEntity.ok(s.next());
        } catch (java.io.IOException e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().body(e.toString());
        }
    }
}
