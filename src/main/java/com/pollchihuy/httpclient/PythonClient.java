package com.pollchihuy.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "python-server", url = "localhost:8989/send-to-python/")
public interface PythonClient {

    @GetMapping("/users/{id}")
    ResponseEntity<Object> getUserById(@PathVariable("id") Long id);

    @PostMapping(path="/1/{v1}/{v2}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Object> getDataByParam(
                                             @PathVariable(value = "v1") Long v1,
                                             @PathVariable(value = "v2") String v2,
                                              @RequestParam(value = "p1") String param1,
                                              @RequestParam(value = "p2") String param2,
                                              @RequestPart(value = "file") MultipartFile file
    );
}