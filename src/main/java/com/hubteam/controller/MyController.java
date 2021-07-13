package com.hubteam.controller;

import com.hubteam.model.countries.Countries;
import com.hubteam.model.countries.MyCountries;
import com.hubteam.model.partners.MyPartners;
import com.hubteam.model.partners.Partners;
import com.hubteam.services.PartnerRequest;
import com.hubteam.services.PartnerResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class MyController {

    @Autowired
    private PartnerResponse partnerResponse;

    @Autowired
    private PartnerRequest partnerRequest;

    @Scheduled(fixedDelay = 50000)
    public void getPartners() throws IOException {
        MyPartners myPartners = partnerRequest.request_Message();
      //  log.info("Requested Data: " +myPartners);
      //  log.info("Requested Data Size: " +myPartners.getPartners().size());
        List<Partners> countrieslist = myPartners.getPartners().stream().filter(distinctByKey(Partners::getCountry)).collect(Collectors.toList());
        int countrySize = myPartners.getPartners().stream().filter(distinctByKey(Partners::getCountry)).collect(Collectors.toList()).size();

//        log.info("Country Size: "+countrySize);
//        log.info("Country List: "+countrieslist);

        List<Countries>  myCountries = new ArrayList<>();
        Countries countries = new Countries();
        for(int i = 1; i < countrySize; i++){
           // countries.setAttendeeCount(myPartners.getPartners().stream().filter(partners -> partners.getCountry().equals("United States")).collect(Collectors.toList()).size());
             countries.setAttendeeCount(myPartners.getPartners().get(i).getCountry().length());
           // countries.setAttendees(myPartners.getPartners().stream().filter(partners -> partners.getCountry().equals("United States")).map(partners -> partners.getEmail()).collect(Collectors.toList()));
       //     countries.setAttendees((myPartners.getPartners().stream().filter(partners -> partners.getCountry().equals(i  myPartners.getPartners().get(i).getEmail())));
           // List<String> mylist = myPartners.getPartners().get(i).getEmail();
            log.info("Email is: "+myPartners.getPartners().stream());
          //  countries.setAttendees((myPartners.getPartners().get(i).getEmail()));


            countries.setName(myPartners.getPartners().get(i).getCountry());
            countries.setStartDate(myPartners.getPartners().get(i).getAvailableDates().get(1));

        }
         myCountries.add(countries);
     //   log.info("Print countries:"+ countries);

       // log.info("Print countries:"+ myCountries);
    }



    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

}
