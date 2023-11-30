package edu.greenriver.sdev.saasproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * This class contains campground 'suggestions' passed in by users.  It is a sub-class of
 * Campground, and in addition to basic Campground information also holds basic information
 * on the person suggesting this campground.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestedCampground {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String url;
    private double latitude;        // 999 = no latitude value
    private double longitude;       // 999 = no longitude value
    private int tentOnlySites;      // number of tent-only sites
    private int totalCampsites;     // number of total campsites
    private String userName;
    private String userEmail;
    private LocalDateTime dateSuggested;

    /**
     * Constructor utilizing name, userName, and userEmail
     * @param name
     * @param userName
     * @param userEmail
     */
    public SuggestedCampground(String name, String userName, String userEmail) {
        this.name = name;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    /**
     * Constructor containing id of campground
     * @param campgroundId int value for campground id
     */
    public SuggestedCampground(int campgroundId)
    {
        this.id = campgroundId;
    }


}
