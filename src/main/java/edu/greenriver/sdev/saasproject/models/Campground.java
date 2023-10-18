package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * This class represents a Campground object.  It includes a name, website url,
 * location, and information on campsites.
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

    public Campground(String name)
    {
        this.name = name;
    }



}
