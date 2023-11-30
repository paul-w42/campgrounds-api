package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.ISuggestedCampgroundRepository;
import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.SuggestedCampground;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestedCampgroundService {
    ISuggestedCampgroundRepository repo;

    public SuggestedCampgroundService(ISuggestedCampgroundRepository repo) {
        this.repo = repo;
    }

    /**
     * Return a list of all campground suggestions
     * @return List of all campground suggestions
     */
    public List<SuggestedCampground> getAllSuggestedCampgrounds() {
        return repo.findAll();
    }

    /**
     * Return the SuggestedCampground associated with the given ID value
     * @param suggestedCampgroundId
     * @return
     */
    public SuggestedCampground getSuggestedCampgroundById(int suggestedCampgroundId) {
        Optional<SuggestedCampground> found = repo.findById(suggestedCampgroundId);
        return found.orElse(null);
    }

    /**
     * Add a  SuggestedCampground object to the database
     * @param suggestedCampground
     * @return
     */
    public SuggestedCampground addSuggestedCampground(SuggestedCampground suggestedCampground)
    {
        suggestedCampground = repo.save(suggestedCampground);
        return suggestedCampground;
    }

    /**
     * Delete the SuggestedCampground object from the database that corresponds
     * to the given id
     * @param suggestedCampgroundId
     */
    public void deleteSuggestedCampgroundById(int suggestedCampgroundId)
    {
        repo.deleteById(suggestedCampgroundId);
    }

}
