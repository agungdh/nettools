package com.bpkadlampungtengah.nettools.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/pinger")
public class PingerResource {
    @GetMapping("/{path}")
    public static ResponseEntity<String> pinger(@PathVariable String path) {
        try {
            Instant before = Instant.now();

            InetAddress geek = InetAddress.getByName(path);
            if (geek.isReachable(5000)) {
                Instant after = Instant.now();
                long delta = Duration.between(before, after).toMillis();

                return ResponseEntity.ok().body(String.valueOf(delta));
            } else {
                return ResponseEntity.internalServerError().body("timeout");
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
