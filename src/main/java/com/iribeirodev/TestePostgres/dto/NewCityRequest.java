package com.iribeirodev.TestePostgres.dto;

import lombok.Data;

@Data
public class NewCityRequest {
    private String name;
    private int population;
}
