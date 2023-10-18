package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.Campground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICampgroundRepository extends JpaRepository<Campground, Integer> {

    /*
     * This allows finding campgrounds by name
     */
    Campground findCampgroundByName(String name);
}
