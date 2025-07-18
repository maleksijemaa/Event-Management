package org.example.serviceeventmanagement.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Mood {
    New, Rap, Rock, ENERGIQUE, ROMANTIQUE, Pop, Autre, MEZWED, TRISTE, HIPHOP, FESTIF, Hyperpop, Wave;

    @JsonCreator
    public static Mood fromValue(String value) {
        for (Mood m : values()) {
            if (m.name().equalsIgnoreCase(value)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Invalid mood value: " + value);
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
