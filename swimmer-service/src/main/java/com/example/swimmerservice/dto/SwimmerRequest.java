package com.example.swimmerservice.dto;

import com.example.swimmerservice.model.BestTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwimmerRequest {
    private String swimmerCode;
    private String firstName;
    private String lastName;
    private int birthYear;
    private String club;
}
