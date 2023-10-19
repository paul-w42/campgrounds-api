package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.ICampgroundRepository;
import edu.greenriver.sdev.saasproject.db.ICampsiteRepository;
import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.Campsite;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CampsiteService {

    ICampsiteRepository campsiteRepo;
    ICampgroundRepository campgroundRepo;

    /**
     * Constructor for interface CampsiteService
     * @param campgroundRepo
     * @param campsiteRepo
     */
    public CampsiteService(ICampgroundRepository campgroundRepo,ICampsiteRepository campsiteRepo)
    {
        this.campgroundRepo = campgroundRepo;
        this.campsiteRepo = campsiteRepo;
    }

    /**
     * Return a specific campsite by campsiteId
     * @param campsiteId
     * @return Campsite object w/ this campsiteId
     */
    public Campsite getCampsiteById(@PathVariable int campsiteId)
    {
        Optional<Campsite> found = campsiteRepo.findById(campsiteId);
        return found.orElse(null);
    }


    /**
     * Return all campsites in a specific campground
     * @param campgroundId int id value for campground
     * @return List of Campsites in this Campground
     */
    public List<Campsite> getCampsitesByCampground(@PathVariable int campgroundId)
    {
        Optional<Campground> found = campgroundRepo.findById(campgroundId);

        // if campground exists
        if (found.isPresent())
        {
            return campsiteRepo.findAllByCampground(found.get());
        }
        else
        {
            return null;
        }
    }

    /**
     * Adds a campsite to a specific campground
     * @param campsite Campsite to add
     * @param campgroundId id of campground to add campsite to
     * @return just created Campsite object
     */
    public Campsite addCampsite(Campsite campsite, int campgroundId) {
        // retrieve campground
        Optional<Campground> campground = campgroundRepo.findById(campgroundId);

        if (campground.isPresent())
        {
            // set campground for this campsite
            campsite.setCampground(campground.get());

            // save campsite
            campsite = campsiteRepo.save(campsite);

            return campsite;
        }
        else
        {
            return null;
        }
    }

    /**
     * Updates a campsites information. We cannot update the campground a
     * campsite belongs to, that would require removing the campsite and
     * creating a new one in the second campground.
     * @param updatedCampsite Updated campsite to save
     * @return The just updated Campsite from the database
     */
    public Campsite updateCampsite(Campsite updatedCampsite)
    {
        Campsite savedCampsite = getCampsiteById(updatedCampsite.getId());

        savedCampsite.setCampsiteNumber(updatedCampsite.getCampsiteNumber());
        savedCampsite.setTentPadPresent(updatedCampsite.isTentPadPresent());
        savedCampsite.setMaxTrailerLength(updatedCampsite.getMaxTrailerLength());

        // save changes to Campsite in db
        savedCampsite = campsiteRepo.save(savedCampsite);

        return savedCampsite;
    }

    /**
     * Remove the campsite with the given ID
     * @param campsiteId
     */
    public void deleteCampsiteById(int campsiteId)
    {
        campsiteRepo.deleteById(campsiteId);
    }

    /*
        public void deleteCampgroundById(int campgroundId) {
        repo.deleteById(campgroundId);
    }

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



    public Campground addCampground(Campground campground) {
        // insert the record
        campground = repo.save(campground);

        // return the campground w/ new id
        return campground;
    }

    public List<Campground> getAllCampgrounds() {
        return repo.findAll();
    }

    public Campground getCampgroundById(int id) {
        Optional<Campground> found = repo.findById(id);
        return found.orElse(null);
    }*/

}
