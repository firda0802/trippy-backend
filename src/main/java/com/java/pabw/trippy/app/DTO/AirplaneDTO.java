package com.java.pabw.trippy.app.dto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AirplaneDTO {
    private int airplaneId;
    private String type;
    private int luggageCapacity;
    private int airportId;

    public AirplaneDTO(int airplaneId, String type, int luggageCapacity, int airportId ) {
        this.airplaneId = airplaneId;
        this.type = type;
        this.luggageCapacity = luggageCapacity;
        this.airportId = airportId;
    }
    
}
