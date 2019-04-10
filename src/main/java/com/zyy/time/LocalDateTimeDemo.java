package com.zyy.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LocalDateTimeDemo {

    @Test
    public void testLocalDate() {
        System.out.println(LocalDate.now());

        LocalDate date = LocalDate.of(2017, 12, 31); // 2017-01-20
        System.out.println(date.getDayOfMonth()); // 31
        System.out.println(date.getDayOfYear()); // 365
        System.out.println(date.getDayOfWeek()); // DayOfWeek SUNDAY
        System.out.println(date.lengthOfMonth()); // 31

        System.out.println(date.withYear(2015)); // 2015-12-31
        System.out.println(date.plusMonths(1)); // 2018-01-31
        System.out.println(date.minusDays(1)); // 2017-12-30
    }

    @Test
    public void testLocalTime() {
        System.out.println(LocalTime.now());

        LocalTime time = LocalTime.of(10, 10, 10);
        System.out.println(time); // 10:10:10
        System.out.println(time.getSecond());
        System.out.println(time.getNano()); // from 0 to 999,999,999

        System.out.println(time.plusSeconds(60)); // 10:11:10
    }

    @Test
    public void testLocalDateTime() {
        System.out.println(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.DECEMBER, 31, 12, 12, 12); // 2017-12-31T12:12:12
        System.out.println(localDateTime);

        System.out.println(localDateTime.plusDays(1)); // 2018-01-01T12:12:12
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2017-12-31T12:12:12

        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // 2017-12-31 12:12:12
        System.out.println(LocalDateTime.parse("2017-12-31 12:12:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testZonedDateTime() {
        System.out.println(LocalDateTime.now()); // beijing

        ZonedDateTime time = ZonedDateTime.of(LocalDateTime.of(2017, Month.DECEMBER, 31, 12, 12, 12), ZoneId.of("Europe/Paris"));
        System.out.println(time); // 2017-12-31T12:12:12+01:00[Europe/Paris]
    }

}
