-- changeset temirlan:2025-04-29-01

CREATE TABLE weather (
        id BIGSERIAL PRIMARY KEY,
        city TEXT NOT NULL,
        temperature DOUBLE PRECISION NOT NULL,
        humidity INTEGER,
        pressure INTEGER,
        wind_speed DOUBLE PRECISION,
        description TEXT,
        timestamp TIMESTAMP NOT NULL
);
