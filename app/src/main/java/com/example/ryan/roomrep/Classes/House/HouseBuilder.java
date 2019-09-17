package com.example.ryan.roomrep.Classes.House;

import com.example.ryan.roomrep.Classes.House.Amenities.AirConditioningAmenity;
import com.example.ryan.roomrep.Classes.House.Amenities.LaundryAmenity;
import com.example.ryan.roomrep.Classes.House.Amenities.PetsAmenity;
import com.example.ryan.roomrep.Classes.House.Amenities.PublicTransitAmenity;
import com.example.ryan.roomrep.Classes.House.Amenities.SmokingAmenity;
import com.example.ryan.roomrep.Classes.House.Amenities.SnowRemovalAmenity;

import java.util.Map;

public class HouseBuilder {

    public House addAmenities(House house) {
        for (Map.Entry<String, Boolean> entry : house.getAmenities().entrySet() ) {
            switch (entry.getKey()){
                case "Pets":
                    if (entry.getValue()){
                        house.addAmenity(new PetsAmenity());
                    }
                    break;
                case "Smoking":
                    if (entry.getValue()){
                        house.addAmenity(new SmokingAmenity());
                    }
                    break;
                case "Public Transit":
                    if (entry.getValue()){
                        house.addAmenity(new PublicTransitAmenity());
                    }
                    break;
                case "Laundry":
                    if (entry.getValue()){
                        house.addAmenity(new LaundryAmenity());
                    }
                    break;
                case "Snow Removal":
                    if (entry.getValue()){
                        house.addAmenity(new SnowRemovalAmenity());
                    }
                    break;
                case "Air Conditioning":
                    if (entry.getValue()){
                        house.addAmenity(new AirConditioningAmenity());
                    }
                    break;
            }
        }
        return house;
    }







}
