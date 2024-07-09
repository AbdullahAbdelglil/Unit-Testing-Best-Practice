package com.example.unit_test_practice.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RandomApartmentGeneratorTest {

    private RandomApartmentGenerator generator;
    private Apartment apartment;

    @BeforeEach
    void setUp() {
        generator = new RandomApartmentGenerator();
    }

    @RepeatedTest(10)
    void should_ReturnCorrectApartment_When_UsingDefaultAreaPrice() {

        // given
        generator = new RandomApartmentGenerator();

        double minArea = 30.0;
        double maxArea = minArea * 4.0;
        BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(4.0));

        // when
        apartment = generator.generate();

        // then
        assertAll(
                () -> assertTrue(apartment.getArea() >= minArea && apartment.getArea() <= maxArea,
                        "Generated apartment area should be between " + minArea + " and " + maxArea),
                () -> assertTrue(apartment.getPrice().compareTo(minPricePerSquareMeter.multiply(new BigDecimal(minArea))) >= 0 &&
                                apartment.getPrice().compareTo(maxPricePerSquareMeter.multiply(new BigDecimal(maxArea))) <= 0,
                        "Generated apartment price should be between " +
                                minPricePerSquareMeter.multiply(new BigDecimal(minArea)) + " and " +
                                maxPricePerSquareMeter.multiply(new BigDecimal(maxArea)))
        );
    }

    @RepeatedTest(10)
    void should_ReturnCorrectApartment_When_UsingCustomAreaPrice() {

        // given
        double minArea = 50.0;
        double maxArea = minArea * 4.0;
        BigDecimal minPricePerSquareMeter = new BigDecimal(50000);
        BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(4.0));
        generator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);

        // when
        apartment = generator.generate();

        // then
        assertAll(
                () -> assertTrue(apartment.getArea() >= minArea && apartment.getArea() <= maxArea,
                        "Generated apartment area should be between " + minArea + " and " + maxArea),
                () -> assertTrue(apartment.getPrice().compareTo(minPricePerSquareMeter.multiply(new BigDecimal(minArea))) >= 0 &&
                                apartment.getPrice().compareTo(maxPricePerSquareMeter.multiply(new BigDecimal(maxArea))) <= 0,
                        "Generated apartment price should be between " +
                                minPricePerSquareMeter.multiply(new BigDecimal(minArea)) + " and " +
                                maxPricePerSquareMeter.multiply(new BigDecimal(maxArea)))
        );
    }
}
