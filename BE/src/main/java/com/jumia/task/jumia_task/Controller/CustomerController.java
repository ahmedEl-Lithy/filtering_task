package com.jumia.task.jumia_task.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.jumia.task.jumia_task.Repository.CustomerRepository;
import com.jumia.task.jumia_task.model.Customer;
import com.jumia.task.jumia_task.pojo.FiltersPojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository repo;

    @GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<List<Customer>>(repo.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/filter_customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> filterCustomers(@RequestBody FiltersPojo body) {

        String country = body.getCountry();
        String state = body.getState();
        List<Customer> customersList = new ArrayList<>();

        if (country != null && state != null) {
            String regex = getCountryRegexFromMap(country);
            String[] parts = regex.split("\\?");
            String countryCode = parts[0].replace("\\", "");

            if (body.getState().equalsIgnoreCase("valid")) {
                customersList = repo.findAll().stream().filter(s -> s.getPhone().matches(regex))
                        .collect(Collectors.toList());
            } else if (body.getState().equalsIgnoreCase("not_valid")) {
                customersList = repo.findAll().stream().filter(s -> s.getPhone().contains(countryCode))
                        .filter(s -> !s.getPhone().matches(regex)).collect(Collectors.toList());
            }
        }else if (country != null && state == null) {
            // filter by country only
            String regex = getCountryRegexFromMap(country);
            String[] parts = regex.split("\\?");
            String countryCode = parts[0].replace("\\", "");
            customersList = repo.findAll().stream().filter(s -> s.getPhone().contains(countryCode))
            .collect(Collectors.toList());
        }

        return new ResponseEntity<List<Customer>>(customersList, HttpStatus.OK);
    }

    public String getCountryRegexFromMap(String country) {

        HashMap<String, String> countriesMap = new HashMap<>();
        countriesMap.put("Cameroon", "\\(237\\)\\ ?[2368]\\d{7,8}$");
        countriesMap.put("Ethiopia", "\\(251\\)\\ ?[1-59]\\d{8}$");
        countriesMap.put("Morocco", "\\(212\\)\\ ?[5-9]\\d{8}$");
        countriesMap.put("Mozambique", "\\(258\\)\\ ?[28]\\d{7,8}$");
        countriesMap.put("Uganda", "\\(256\\)\\ ?\\d{9}$");

        return countriesMap.get(country);

    }

}