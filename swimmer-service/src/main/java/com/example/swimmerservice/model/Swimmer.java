package com.example.swimmerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Array;
import java.util.List;

@Document(value = "swimmer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Swimmer {
    private String id;
    private String firstName;
    private String lastName;
    private String club;
    private String swimmerCode;
    private int birthYear;
    private Array bestTimes;

}
