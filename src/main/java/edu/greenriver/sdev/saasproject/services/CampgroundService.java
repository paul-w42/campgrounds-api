package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.ICampgroundRepository;
import edu.greenriver.sdev.saasproject.models.Campground;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is the CampgroundService, and does the majority of the database work,
 * especially wrt to the Campground table.
 *
 * @author Paul Woods
 * @version 0.1
 */
@Service
public class CampgroundService {

    ICampgroundRepository repo;

    /**
     * Constructor that takes in ICampgroundRepository repo
     *
     * @param repo ICampgroundRepository object
     */
    public CampgroundService(ICampgroundRepository repo) {
        this.repo = repo;
    }

    /**
     * Returns a list of all campgrounds
     *
     * @return List of Campground objects
     */
    public List<Campground> getAllCampgrounds() {
        return repo.findAll();
    }

    /**
     * Retrieves the Campground w/ given int it
     *
     * @param campgroundId int value indicating Campground id
     * @return Campground object w/ given int id
     */
    public Campground getCampgroundById(int campgroundId) {
        Optional<Campground> found = repo.findById(campgroundId);
        return found.orElse(null);
    }

    /**
     * Adds a Campground to the database
     *
     * @param campground Campground to add to database
     * @return Campground object added to database including id
     */
    public Campground addCampground(Campground campground) {
        // insert the record
        campground = repo.save(campground);

        // return the campground w/ new id
        return campground;
    }

    /**
     * Delete Campground by id
     *
     * @param campgroundId int id of Campground to delete
     */
    public void deleteCampgroundById(int campgroundId)
    {
        System.out.println("deleteCampgroundById: " + campgroundId);
        repo.deleteById(campgroundId);
    }

    /**
     * Updates a Campground
     * @param updatedCampground Campground information to update with
     * @return updated Campground object from database
     */
    public Campground updateCampground(Campground updatedCampground)
    {
        Campground savedCampground = getCampgroundById(updatedCampground.getId());

        savedCampground.setTentOnlySites(updatedCampground.getTentOnlySites());
        savedCampground.setTotalCampsites(updatedCampground.getTotalCampsites());
        savedCampground.setUrl(updatedCampground.getUrl());
        savedCampground.setLatitude(updatedCampground.getLatitude());
        savedCampground.setLongitude(updatedCampground.getLongitude());
        savedCampground.setName(updatedCampground.getName());

        // save changes to Campground in the db
        savedCampground = repo.save(savedCampground);

        return savedCampground;
    }


}
