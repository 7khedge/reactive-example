package com.sf.data.domain;

import com.sf.data.service.MessageStringCleaner;

import java.math.BigDecimal;

import static com.sf.data.service.MessageStringCleaner.cleanString;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class Airport {
    private final String id;
    private final String name;
    private final String city;
    private final String country;
    private final String IATACode;
    private final String ICAOCode;
    private final BigDecimal latitude;
    private final BigDecimal longitude;
    private final Integer altitude;
    private final Integer timeOffset;
    private final String dstCode;
    private final String timeZone;

    public Airport(String id, String name, String city, String country, String IATACode, String ICAOCode, BigDecimal latitude, BigDecimal longitude, Integer altitude, Integer timeOffset, String dstCode, String timeZone) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.IATACode = IATACode;
        this.ICAOCode = ICAOCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timeOffset = timeOffset;
        this.dstCode = dstCode;
        this.timeZone = timeZone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIATACode() {
        return IATACode;
    }

    public String getICAOCode() {
        return ICAOCode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public Integer getTimeOffset() {
        return timeOffset;
    }

    public String getDstCode() {
        return dstCode;
    }

    public String getTimeZone() {
        return timeZone;
    }


    public static final class AirportBuilder {
        private String id;
        private String name;
        private String city;
        private String country;
        private String IATACode;
        private String ICAOCode;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private Integer altitude;
        private Integer timeOffset;
        private String dstCode;
        private String timeZone;

        private AirportBuilder() {
        }

        public static AirportBuilder anAirport() {
            return new AirportBuilder();
        }

        public AirportBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public AirportBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AirportBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AirportBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AirportBuilder withIATACode(String IATACode) {
            this.IATACode = IATACode;
            return this;
        }

        public AirportBuilder withICAOCode(String ICAOCode) {
            this.ICAOCode = ICAOCode;
            return this;
        }

        public AirportBuilder withLatitude(BigDecimal latitude) {
            this.latitude = latitude;
            return this;
        }

        public AirportBuilder withLongitude(BigDecimal longitude) {
            this.longitude = longitude;
            return this;
        }

        public AirportBuilder withAltitude(Integer altitude) {
            this.altitude = altitude;
            return this;
        }

        public AirportBuilder withTimeOffset(Integer timeOffset) {
            this.timeOffset = timeOffset;
            return this;
        }

        public AirportBuilder withDstCode(String dstCode) {
            this.dstCode = dstCode;
            return this;
        }

        public AirportBuilder withTimeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public AirportBuilder from(String line) {
            String[] split = line.split(",");
            withId(split[0]);
            withName(cleanString(split[1]));
            withCity(cleanString(split[2]));
            withCountry(cleanString(split[3]));
            withIATACode(cleanString(split[4]));
            withICAOCode(cleanString(split[5]));
            withLatitude(new BigDecimal(split[6]));
            withLongitude(new BigDecimal(split[7]));
            withAltitude(Integer.valueOf(split[8]));
            withTimeOffset(Integer.valueOf(split[9]));
            withDstCode(cleanString(split[10]));
            withTimeZone(cleanString(split[11]));
            return this;
        }

        public Airport build() {
            Airport airport = new Airport(id, name, city, country, IATACode, ICAOCode, latitude, longitude, altitude, timeOffset, dstCode, timeZone);
            return airport;
        }

    }
}
