package org.PANG.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Location {

    private String location_name;
    private double latitude;
    private double longitude;
}