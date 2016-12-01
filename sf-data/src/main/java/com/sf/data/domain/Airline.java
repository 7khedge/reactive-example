package com.sf.data.domain;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class Airline {
    private final String id;
    private final String name;
    private final String alias;
    private final String IATACode;
    private final String ICAOCode;
    private final String callSign;
    private final String country;
    private final String active;

    public Airline(String id, String name, String alias, String IATACode, String ICAOCode, String callSign, String country, String active) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.IATACode = IATACode;
        this.ICAOCode = ICAOCode;
        this.callSign = callSign;
        this.country = country;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getIATACode() {
        return IATACode;
    }

    public String getICAOCode() {
        return ICAOCode;
    }

    public String getCallSign() {
        return callSign;
    }

    public String getCountry() {
        return country;
    }

    public String getActive() {
        return active;
    }

    public static final class AirlineBuilder {
        private String id;
        private String name;
        private String alias;
        private String IATACode;
        private String ICAOCode;
        private String callSign;
        private String country;
        private String active;

        private AirlineBuilder() {
        }

        public static AirlineBuilder anAirline() {
            return new AirlineBuilder();
        }

        public AirlineBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public AirlineBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AirlineBuilder withAlias(String alias) {
            this.alias = alias;
            return this;
        }

        public AirlineBuilder withIATACode(String IATACode) {
            this.IATACode = IATACode;
            return this;
        }

        public AirlineBuilder withICAOCode(String ICAOCode) {
            this.ICAOCode = ICAOCode;
            return this;
        }

        public AirlineBuilder withCallSign(String callSign) {
            this.callSign = callSign;
            return this;
        }

        public AirlineBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AirlineBuilder withActive(String active) {
            this.active = active;
            return this;
        }

        public Airline build() {
            Airline airline = new Airline(id, name, alias, IATACode, ICAOCode, callSign, country, active);
            return airline;
        }
    }
}
