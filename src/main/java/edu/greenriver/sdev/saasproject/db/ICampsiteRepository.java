package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.Campsite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICampsiteRepository extends JpaRepository<Campsite, Integer> {

    /**
     * Find all campsites in a specific campground
     * @param campground
     * @return List of Campsites
     */
    List<Campsite> findAllByCampground(Campground campground);

    void deleteByCampground(Campground campground);

//    void deleteByCamggroundId(Integer id);

}
