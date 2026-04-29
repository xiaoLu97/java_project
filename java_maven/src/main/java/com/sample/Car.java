package com.sample;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Car {
    @Value("Toyota")
    private String brand;
    @Value("Red")
    private String color;
}
