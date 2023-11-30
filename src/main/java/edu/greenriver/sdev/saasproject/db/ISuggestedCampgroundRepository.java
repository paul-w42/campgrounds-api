package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.SuggestedCampground;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ISuggestedCampgroundRepository extends JpaRepository<SuggestedCampground, Integer> {
    //void deleteByCampground(SuggestedCampground suggestedCampground);
}
