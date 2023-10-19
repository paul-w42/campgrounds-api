package edu.greenriver.sdev.saasproject.controllers;

import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.Campsite;
import edu.greenriver.sdev.saasproject.services.CampgroundService;
import edu.greenriver.sdev.saasproject.services.CampsiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This class presents public API endpoints through Spring and handles these
 * calls by using CampgroundService
 */
@RestController
public class CampgroundApi {

    private CampgroundService campgroundService;
    private CampsiteService campsiteService;

    /**
     * Constructor that takes a campgroundService object
     * @param campgroundService
     */
    public CampgroundApi(CampgroundService campgroundService, CampsiteService campsiteService) {
        this.campgroundService = campgroundService;
        this.campsiteService = campsiteService;
    }

    /**
     * GET mapping to return a List of campgrounds
     * @return List of Campgrounds
     */
    @GetMapping("campgrounds")
    public ResponseEntity<List<Campground>> allCampgrounds()
    {
        System.out.println("returning list of campgrounds");
        return new ResponseEntity<List<Campground>>(
                campgroundService.getAllCampgrounds(), HttpStatus.OK);
    }

    /**
     * GET mapping to return a specific Campground by id
     * @param campgroundId
     * @return
     */
    @GetMapping("campgrounds/{campgroundId}")
    public Campground getCampgroundById(@PathVariable int campgroundId)
    {
        return campgroundService.getCampgroundById(campgroundId);
    }

    /**
     * POST mapping to save a Campground to the database
     * @param campground Campground to save to database
     * @return Campground object just added to database
     */
    @PostMapping("campgrounds")
    public ResponseEntity<Campground> addCampground(@RequestBody Campground campground)
    {
//        System.out.println("Adding a campground");
        return new ResponseEntity<>(
                campgroundService.addCampground(campground),HttpStatus.CREATED);
    }

    /**
     * DELETE mapping to delete a Campground from the database
     * @param campground Campground to delete
     */
    @DeleteMapping("campgrounds")
    public void deleteCampground(@RequestBody Campground campground)
    {
//        System.out.println("Deleting a campground");
//        System.out.println("Received campground with ID: " + campground.getId());
        campgroundService.deleteCampgroundById(campground.getId());
    }

    /**
     * PUT mapping to update a campground w/ current information
     * @param campground Campground to update db record with
     * @return Campground object representing updated campground
     */
    @PutMapping("campgrounds")
    public Campground editCampground(@RequestBody Campground campground)
    {
        return campgroundService.updateCampground(campground);
    }

    /**
     * Returns a Campsite given its ID value
     * @param campsiteId ID of campsite to return,
     * @return Campsite with matching ID
     */
    @GetMapping("campgrounds/{campgroundId}/campsites/{campsiteId}")
    public Campsite getCampsiteById(@PathVariable int campsiteId)
    {
        return campsiteService.getCampsiteById(campsiteId);
    }

    /**
     * Returns a list of Campsites in a given campground
     * @param campgroundId ID of campground to return campsites for
     * @return Returns List of Campsites for given Campground ID
     */
    @GetMapping("campgrounds/{campgroundId}/campsites")
    public ResponseEntity<List<Campsite>> campsitesByCampground(@PathVariable int campgroundId)
    {
        return new ResponseEntity<List<Campsite>>(
            campsiteService.getCampsitesByCampground(campgroundId), HttpStatus.OK);
    }

    /**
     * Adds a Campsite to a Campground
     * @param campgroundId ID of Campground to add Campsite to
     * @param campsite Campsite to add to database
     * @return Just created Campsite
     */
    @PostMapping("campgrounds/{campgroundId}/campsites")
    public ResponseEntity<Campsite> addCampsite(
            @PathVariable int campgroundId, @RequestBody Campsite campsite)
    {

        return new ResponseEntity<>(
                campsiteService.addCampsite(campsite, campgroundId), HttpStatus.CREATED);
    }

    /**
     * Called to save changes to / update a Campsite information
     * @param campgroundId ID of Campground this campsite belongs to
     * @param campsite Updated campsite information to save
     * @return Updated Campsite
     */
    @PutMapping("campgrounds/{campgroundId}/campsites")
    public ResponseEntity<Campsite> editCampsite(@PathVariable int campgroundId, @RequestBody Campsite campsite)
    {
        if (campgroundId != campsite.getCampground().getId())
        {
//            System.out.println("PathVariable ID: " + campgroundId);
//            System.out.println("Campground ID: " + campsite.getCampground().getId());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        System.out.println("Campsite successfully updated");
        return new ResponseEntity<>(campsiteService.updateCampsite(campsite), HttpStatus.OK);
    }

    /**
     * Delete the given campsite.  Requires that the Campground ID in the path
     * matches what is present inside the Campground object.
     * @param campgroundId ID of Campground Campsite belongs to
     * @param campsite Campsite to remove
     * @return Null
     */
    @DeleteMapping("campgrounds/{campgroundId}/campsites")
    public ResponseEntity<Campsite> deleteCampground(@PathVariable int campgroundId, @RequestBody Campsite campsite)
    {
        if (campgroundId != campsite.getCampground().getId())
        {
//            System.out.println("PathVariable ID: " + campgroundId);
//            System.out.println("Campground ID: " + campsite.getCampground().getId());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else
        {
            campsiteService.deleteCampsiteById(campsite.getId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

    }

/*
    @PutMapping("campgrounds")
    public Campground editCampground(@RequestBody Campground campground)
    {
        return campgroundService.updateCampground(campground);
    }

    @PostMapping("campgrounds")
    public ResponseEntity<Campground> addCampground(@RequestBody Campground campground)
    {
        return new ResponseEntity<>(
                campgroundService.addCampground(campground),HttpStatus.CREATED);
    }

    @GetMapping("campgrounds")
    public ResponseEntity<List<Campground>> allCampgrounds()
    {
        return new ResponseEntity<List<Campground>>(
                campgroundService.getAllCampgrounds(), HttpStatus.OK);
    }

    @GetMapping("campgrounds/{campgroundId}")
    public Campground getCampgroundById(@PathVariable int campgroundId)
    {
        return campgroundService.getCampgroundById(campgroundId);
    }

 */

}
