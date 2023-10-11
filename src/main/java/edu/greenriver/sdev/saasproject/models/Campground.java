package edu.greenriver.sdev.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Campground object.  It includes a name, website url,
 * location, and information on campsites.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campground {
    private int id;
    private String name;
    private String url;
    private double latitude;
    private double longitude;
    private int tentOnlySites;
    private int totalCampsites;
}
