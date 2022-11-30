package com.example.addressvalidation.address.controller;

import com.example.addressvalidation.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping(value = "/address/{address}")
    ResponseEntity<?> getValidAddress(@PathVariable String address) throws IOException {
        return ResponseEntity.ok(addressService.getRoadName(address));
    }
}