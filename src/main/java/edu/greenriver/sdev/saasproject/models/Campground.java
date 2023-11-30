package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a Campground object.  It includes a name, website url,
 * location, and information on campsites.
 *
 * @author Paul Woods
 * @version 0.1
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String url;
    private double latitude;        // 999 = no latitude value
    private double longitude;       // 999 = no longitude value
    private int tentOnlySites;      // number of tent-only sites
    private int totalCampsites;     // number of total campsites

    /**
     * Constructor containing name of Campground
     * @param name String value of campground name
     */
    public Campground(String name)
    {
        this.name = name;
    }

    /**
     * Constructor containing id of campground
     * @param campgroundId int value for campground id
     */
    public Campground(int campgroundId)
    {
        this.id = campgroundId;
    }

    public Campground(String name, String url, double latitude, double longitude) {
        this.name = name;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
