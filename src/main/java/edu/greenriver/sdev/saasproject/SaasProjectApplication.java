package edu.greenriver.sdev.saasproject;

import edu.greenriver.sdev.saasproject.db.ICampgroundRepository;
import edu.greenriver.sdev.saasproject.db.ICampsiteRepository;
import edu.greenriver.sdev.saasproject.models.Campground;
import edu.greenriver.sdev.saasproject.models.Campsite;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SaasProjectApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SaasProjectApplication.class, args);
        ICampgroundRepository campgroundRepo = context.getBean(ICampgroundRepository.class);
        ICampsiteRepository campsiteRepo = context.getBean((ICampsiteRepository.class));

        List<Campground> campgrounds = new ArrayList<>(List.of(
                new Campground("Fort Flagler State Park"),
                new Campground("Fort Worden State Park"),
                new Campground("Cape Disappointment State Park"),
                new Campground("Salmon La Sac Campground"),
                new Campground("Teannaway Campground")
        ));

        List<Campsite> campsites;

        System.out.println("Saving campgrounds to database");

        // save campgrounds to database
        for (Campground camp: campgrounds)
        {
            campgroundRepo.save(camp);
        }

        campsites = new ArrayList<>(List.of(
                new Campsite("48"),
                new Campsite("50"),
                new Campsite("51"),
                new Campsite("52"),
                new Campsite("53"),
                new Campsite("54"),
                new Campsite("55"),
                new Campsite("56"),
                new Campsite("57"),
                new Campsite("58"),
                new Campsite("59"),
                new Campsite("60"),
                new Campsite("61"),
                new Campsite("62"),
                new Campsite("63"),
                new Campsite("65"),
                new Campsite("66"),
                new Campsite("67"),
                new Campsite("68"),
                new Campsite("69"),
                new Campsite("70"),
                new Campsite("71"),
                new Campsite("73"),
                new Campsite("74"),
                new Campsite("75")
            ));

        // Save campsites to Fort Flagler
        Campground flagler = campgroundRepo.findCampgroundByName("Fort Flagler State Park");

        // Update lat/lon for Fort Flagler, 48.093303750038324, -122.69665099785261
        flagler.setLatitude(48.0933037);
        flagler.setLongitude(-122.6966509);
        flagler.setUrl("https://www.parks.wa.gov/508/Fort-Flagler");
        flagler.setTotalCampsites(114);
        campgroundRepo.save(flagler);

        // for each site at Fort Flagler, add to that campground
        for (Campsite site: campsites)
        {
            site.setCampground(flagler);
            campsiteRepo.save(site);
        }

        // Save Campsites to Fort Worden
        campsites = new ArrayList<>(List.of(
            new Campsite("1"),
            new Campsite("2"),
            new Campsite("3"),
            new Campsite("4"),
            new Campsite("5"),
            new Campsite("6"),
            new Campsite("7"),
            new Campsite("8"),
            new Campsite("9"),
            new Campsite("10"),
            new Campsite("11"),
            new Campsite("12"),
            new Campsite("13"),
            new Campsite("14"),
            new Campsite("15"),
            new Campsite("16"),
            new Campsite("17"),
            new Campsite("18"),
            new Campsite("19"),
            new Campsite("20"),
            new Campsite("21"),
            new Campsite("22"),
            new Campsite("23"),
            new Campsite("24"),
            new Campsite("25")
        ));

        Campground worden = campgroundRepo.findCampgroundByName("Fort Worden State Park");

        // Update lat/lon for Fort Worden, 48.13474789655182, -122.76851429478285
        worden.setLatitude(48.1347478);
        worden.setLongitude(-122.7685142);
        worden.setUrl("https://www.parks.wa.gov/511/Fort-Worden");
        worden.setTotalCampsites(80);
        campgroundRepo.save(worden);

        // for each site at Fort Worden, add to that campground
        for (Campsite site: campsites)
        {
            site.setCampground(worden);
            campsiteRepo.save(site);
        }

        // Save Campsites to Cape Disappointment State Park
        campsites = new ArrayList<>(List.of(
                new Campsite("1"),
                new Campsite("2"),
                new Campsite("3"),
                new Campsite("4"),
                new Campsite("5"),
                new Campsite("6"),
                new Campsite("7"),
                new Campsite("8"),
                new Campsite("9"),
                new Campsite("10"),
                new Campsite("11"),
                new Campsite("12"),
                new Campsite("13"),
                new Campsite("14"),
                new Campsite("15"),
                new Campsite("16"),
                new Campsite("17"),
                new Campsite("18"),
                new Campsite("19"),
                new Campsite("20"),
                new Campsite("21"),
                new Campsite("22"),
                new Campsite("23"),
                new Campsite("24"),
                new Campsite("25")
        ));

        Campground disappointment = campgroundRepo.findCampgroundByName("Cape Disappointment State Park");

        // Update lat/lon for disappointment, 46.28333266865655, -124.05599606254144
        disappointment.setLatitude(46.28333266);
        disappointment.setLongitude(-124.0559960);
        disappointment.setUrl("https://www.parks.wa.gov/486/Cape-Disappointment");
        disappointment.setTotalCampsites(210);
        disappointment.setTentOnlySites(5);
        campgroundRepo.save(disappointment);

        // for each site at disappointment, add to that campground
        for (Campsite site: campsites)
        {
            site.setCampground(disappointment);
            campsiteRepo.save(site);
        }


        // Save Campsites to Salmon La Sac
        campsites = new ArrayList<>(List.of(
                new Campsite("1"),
                new Campsite("2"),
                new Campsite("3"),
                new Campsite("4"),
                new Campsite("5"),
                new Campsite("6"),
                new Campsite("7"),
                new Campsite("8"),
                new Campsite("9"),
                new Campsite("10"),
                new Campsite("11"),
                new Campsite("12"),
                new Campsite("13"),
                new Campsite("14"),
                new Campsite("15"),
                new Campsite("16"),
                new Campsite("17"),
                new Campsite("18"),
                new Campsite("19"),
                new Campsite("20"),
                new Campsite("21"),
                new Campsite("22"),
                new Campsite("23"),
                new Campsite("24"),
                new Campsite("25")
        ));

        Campground salmonLaSac = campgroundRepo.findCampgroundByName("Salmon La Sac Campground");

        // Update lat/lon for salmonLaSac, 47.40347060868324, -121.0983936128522
        salmonLaSac.setLatitude(47.4034706);
        salmonLaSac.setLongitude(-121.0983936);
        salmonLaSac.setUrl("https://www.recreation.gov/camping/campgrounds/232094");
        salmonLaSac.setTotalCampsites(69);
        campgroundRepo.save(salmonLaSac);

        // for each site at salmonLaSac, add to that campground
        for (Campsite site: campsites)
        {
            site.setCampground(salmonLaSac);
            campsiteRepo.save(site);
        }


        // Save Campsites to Teannaway Campground
        campsites = new ArrayList<>(List.of(
                new Campsite("1"),
                new Campsite("2"),
                new Campsite("3"),
                new Campsite("4"),
                new Campsite("5"),
                new Campsite("6"),
                new Campsite("7"),
                new Campsite("8"),
                new Campsite("9"),
                new Campsite("10"),
                new Campsite("11"),
                new Campsite("12"),
                new Campsite("13"),
                new Campsite("14"),
                new Campsite("15"),
                new Campsite("16"),
                new Campsite("17"),
                new Campsite("18"),
                new Campsite("19"),
                new Campsite("20"),
                new Campsite("21"),
                new Campsite("22"),
                new Campsite("23"),
                new Campsite("24"),
                new Campsite("25")
        ));

        Campground teannaway = campgroundRepo.findCampgroundByName("Teannaway Campground");

        // Update lat/lon for Teannaway Campground, 47.25839976251815, -120.89602755065299
        teannaway.setLatitude(46.28333266);
        teannaway.setLongitude(-124.0559960);
        teannaway.setUrl("https://www.dnr.wa.gov/Teanaway");
        teannaway.setTotalCampsites(55);
        campgroundRepo.save(teannaway);

        // for each site at Teannaway Campground, add to that campground
        for (Campsite site: campsites)
        {
            site.setCampground(teannaway);
            campsiteRepo.save(site);
        }

        System.out.println("Finished adding campgrounds & campsites to db");

    }

}
