package com.example.unit_test_practice.realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ApartmentRaterTest {
    private Apartment apartment;

    private static final BigDecimal CHEAP_THRESHOLD = new BigDecimal(6000.0);
    private static final BigDecimal MODERATE_THRESHOLD = new BigDecimal(8000.0);

    @BeforeEach
    void setup() {
        apartment = new Apartment();
    }

    @Nested
    class TestRateApartment {

        @Test
        void should_ReturnMinusOne_When_ApartmentAreaIsZero() {

            // given
            apartment.setArea(0);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(-1, rating, "Rating should be -1 when apartment area is zero");
        }

        @ParameterizedTest
        @CsvSource(value = {"100000, 80.0", "150000, 94,3", "500000, 124.2"})
        void should_ReturnZero_When_RatioLessThan6000(BigDecimal price, Float area) {

            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(rating, 0, "Rating should be 0 when price/area ratio less than 6000");
        }

        @ParameterizedTest
        @CsvSource(value = {"600000, 100", "500000, 80.0", "700000, 100.0"})
        void should_ReturnOne_When_RatioGreaterThanEqual6000AndLessThan8000(BigDecimal price, Float area) {

            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(rating, 1, "Rating should be 1 when price/area ratio between 6000 and 8000");
        }

        @ParameterizedTest
        @CsvSource(value = {"500000, 50.0", "1000000, 100.0", "700000, 85.0"})
        void should_ReturnTwo_When_RatioGreaterThan8000(BigDecimal price, Float area) {

            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(rating, 2, "Rating should be 2 when price/area ratio greater than 8000");
        }
    }

    @Nested
    class CalculateAverageRatingApartmentTest {

        List<Apartment> apartmentList;

        @Test
        void should_ThrowRuntimeException_When_ApartmentListEmpty() {

            // given
            apartmentList = new ArrayList<>();

            // when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

            //then
            assertThrows(RuntimeException.class, executable);
        }

        @Test
        void should_ReturnCorrectAverage_When_CorrectApartmentList() {

            // given
            apartmentList = new ArrayList<>();
            apartmentList.add(new Apartment(100.0, new BigDecimal(600000)));
            apartmentList.add(new Apartment(80.0, new BigDecimal(100000)));
            apartmentList.add(new Apartment(120.0, new BigDecimal(1300000)));

            // when
            double average = ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertEquals(average, 1, "Average rating should be 1.0 for the given apartment list");
        }
    }
}


/*
public class ApartmentRaterTest {
    private Apartment apartment;

    private static final BigDecimal CHEAP_THRESHOLD = new BigDecimal(6000.0);
    private static final BigDecimal MODERATE_THRESHOLD = new BigDecimal(8000.0);

    @BeforeEach
    void createNewApartmentInstance() {
        apartment = new Apartment();
    }

    @Nested
    class TestRateApartment {

        @Test
        void should_ReturnMinusOne_When_ApartmentAreaZero() {
            // given
            apartment.setArea(0);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(-1, rating);
        }

        @ParameterizedTest
        @CsvSource(value = {"100000, 80.0", "150000, 94.3", "500000, 124.2"})
        void should_ReturnZero_When_RatioLessThan_6000(BigDecimal price, Float area) {
            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(0, rating);
        }

        @ParameterizedTest
        @CsvSource(value = {"600000, 100", "500000, 80.0", "700000, 100.0"})
        void should_ReturnOne_When_RatioBetween_6000_And_8000(BigDecimal price, Float area) {
            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(1, rating);
        }

        @ParameterizedTest
        @CsvSource(value = {"500000, 50.0", "1000000, 100.0", "700000, 85.0"})
        void should_ReturnTwo_When_RatioGreaterThan_8000(BigDecimal price, Float area) {
            // given
            apartment.setArea(area);
            apartment.setPrice(price);

            // when
            int rating = ApartmentRater.rateApartment(apartment);

            // then
            assertEquals(2, rating);
        }
    }

    @Nested
    class CalculateAverageRatingApartmentTest {

        List<Apartment> apartmentList;

        @Test
        void should_ThrowRuntimeException_When_ApartmentListEmpty() {
            // given
            apartmentList = new ArrayList<>();

            // when
            Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertThrows(RuntimeException.class, executable);
        }

        @Test
        void should_ReturnCorrectAverage_When_ValidApartmentList() {
            // given
            apartmentList = new ArrayList<>();
            apartmentList.add(new Apartment(100.0, new BigDecimal(600000)));
            apartmentList.add(new Apartment(80.0, new BigDecimal(100000)));
            apartmentList.add(new Apartment(120.0, new BigDecimal(1300000)));

            // when
            double average = ApartmentRater.calculateAverageRating(apartmentList);

            // then
            assertEquals(1, average);
        }
    }
}

* */
