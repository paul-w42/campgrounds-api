package edu.greenriver.sdev.saasproject.services;

import edu.greenriver.sdev.saasproject.db.ICampgroundRepository;
import edu.greenriver.sdev.saasproject.models.Campground;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CampgroundService {

    ICampgroundRepository repo;

    public CampgroundService(ICampgroundRepository repo)
    {
        this.repo = repo;
    }

//    public Campground getCampgroundByName(String name)
//    {
//        //Optional<Campground> found = repo.findByName(name);
//    }


    /*
    public Joke getJokeById(int id)
    {
        //the filter() method receives a lambda method
        Optional<Joke> found = repo.findById(id);
        return found.orElse(null);
    }
    */
}
