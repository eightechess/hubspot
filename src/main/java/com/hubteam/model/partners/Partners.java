package com.hubteam.model.partners;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Partners {
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private List<Date> availableDates;
}
