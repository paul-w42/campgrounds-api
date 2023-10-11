package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.services.CampgroundService;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class presents public API endpoints through Spring and handles these
 * calls by using CampgroundService
 */
@RestController
public class CampgroundApi {
    private CampgroundService campgroundService;

    /**
     * Constructor that takes a campgroundService object
     * @param campgroundService
     */
    public CampgroundApi(CampgroundService campgroundService) {
        this.campgroundService = campgroundService;
    }


}
