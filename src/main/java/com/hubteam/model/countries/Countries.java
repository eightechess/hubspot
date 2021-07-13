package com.hubteam.model.countries;

import com.hubteam.model.partners.Partners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Countries {
    private int attendeeCount;
    private List<String> attendees;
    private String name;
    private Date startDate;
}
