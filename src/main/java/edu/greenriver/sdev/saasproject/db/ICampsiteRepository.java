package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.models.Campsite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICampsiteRepository extends JpaRepository<Campsite, Integer> {
    //
}
