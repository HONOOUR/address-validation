package com.example.addressvalidation.address.service;

import com.example.addressvalidation.address.vo.Address;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class AddressService {
    private static final String specialChar = "[^\uAC00-\uD7A30-9a-zA-Z]";
    private static final String roadNameDivider = "(로|길)";
    public String getRoadName(String address) {
        // 정규식
        address = getFilteredAddress(address);

        try {
            return getRoadNameByJuso(address);
        } catch (Exception e) {
            throw new RuntimeException("Not found");
        }
    }

    private String getFilteredAddress(String address) {
        address = address.replaceAll(specialChar, "");
        String roadNameFront = address.split(roadNameDivider)[0];
        String roadNameBack = address.split(roadNameDivider)[1].replaceFirst("[^0-9]", " ").split(" ")[0]; // 숫자를 뒤에 넣는 경우 e.g. 백 현 로 265
        int roadNameSize = roadNameFront.length()+1;
        address = address.substring(0, roadNameSize) + roadNameBack;
        System.out.println(address);
        return address;
    }

    private String getRoadNameByJuso(String address) throws IOException {
        // -로 찾기
        String apiUrl = "https://business.juso.go.kr/addrlink/addrLinkApi.do?currentPage=0&countPerPage=10&keyword="+address+"&confmKey=devU01TX0FVVEgyMDIyMTEyODE0NDcwMjExMzI2NDQ=&resultType=json";
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String line = br.readLine();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader objectReader = objectMapper.readerFor(Address.class).withRootName("results");
        Address addresses = objectReader.readValue(line);
        String roadName = addresses.getAddresses().get(0).get("rn").toString();
        System.out.println(roadName);
        return roadName;
    }
}