package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents a campsite object.  A campground contains from 1 to n campsites
 *
 * @author Paul Woods
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campsite {
    private static int campsiteIds = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Campground campground;

    private String campsiteNumber; // 1 ... 99, or A1, North10, etc.
    private int maxTrailerLength;   // 0 = no trailer/rv allowed
    private boolean tentPadPresent;

    public Campsite(String campsiteNumber)
    {
        this.campsiteNumber = campsiteNumber;
    }

}
