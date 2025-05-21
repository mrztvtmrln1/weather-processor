-- changeset temirlan:2025-04-29-02

CREATE TABLE weather_anomalies (
        id BIGSERIAL PRIMARY KEY,
        city TEXT NOT NULL,
        old_temperature DOUBLE PRECISION NOT NULL,
        new_temperature DOUBLE PRECISION NOT NULL,
        difference DOUBLE PRECISION NOT NULL,
        detected_at TIMESTAMP NOT NULL
);
