package com.phillips.noanalyticsdev.util.types;

public class Types {
    public enum types {
        STRING,
        INT,
        DECIMAL,
        CURRENCY,
        PERCENT,
        TIME,
        ENUM,
        ORDERED_ENUM
    }
    public enum polarity {
        positive,
        negative
    }
    public enum filterTypes {
        dateRange,
        breakdown,
        segment,
        excludeItemIds
    }
    public enum calcuTypes {
        CURRENCY,
        TIME,
        DECIMAL,
        PERCENT
    }
}
