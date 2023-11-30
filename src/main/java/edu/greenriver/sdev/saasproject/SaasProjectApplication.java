package edu.greenriver.sdev.saasproject;

import edu.greenriver.sdev.saasproject.db.ICampgroundRepository;
import edu.greenriver.sdev.saasproject.db.ICampsiteRepository;
import edu.greenriver.sdev.saasproject.db.ISuggestedCampgroundRepository;
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
                new Campground("Teannaway Campground"),
                new Campground("Alta Lake State Park",  // location correct
                        "https://parks.wa.gov/find-parks/state-parks/alta-lake-state-park",
                        48.0280498, -119.939267),
                new Campground("Bay View State Park",       // location correct
                        "https://parks.wa.gov/find-parks/state-parks/bay-view-state-park",
                        48.487968, -122.479461),
                new Campground("Beacon Rock State Park",    // location correct
                        "https://parks.wa.gov/find-parks/state-parks/beacon-rock-state-park",
                        45.62847, -122.02231),
                new Campground("Belfair State Park",    // location correct
                        "https://parks.wa.gov/find-parks/state-parks/belfair-state-park",
                        47.429748, -122.878157),
                new Campground("Birch Bay State Park",  // location correct
                        "https://parks.wa.gov/find-parks/state-parks/birch-bay-state-park",
                        48.903518, -122.766028),
                new Campground("Bogachiel State Park",  // location correct
                        "https://parks.wa.gov/find-parks/state-parks/bogachiel-state-park",
                        47.894586, -124.36430),
                new Campground("Bridgeport State Park", // location correct
                        "https://parks.wa.gov/find-parks/state-parks/bridgeport-state-park",
                        48.01592, -119.60882),
                new Campground("Brooks Memorial State Park",
                        "https://parks.wa.gov/find-parks/state-parks/brooks-memorial-state-park",
                        45.94997, -120.666147),
                new Campground("Camano Island State Park",      // location correct
                        "https://parks.wa.gov/find-parks/state-parks/camano-island-state-park",
                        48.128244, -122.499185),
                new Campground("Columbia Hills Historical State Park",  // location correct
                        "https://parks.wa.gov/find-parks/state-parks/columbia-hills-historical-state-park",
                        45.643036, -121.106793),
                new Campground("Conconully State Park",     // location correct
                        "https://parks.wa.gov/find-parks/state-parks/conconully-state-park",
                        48.5568, -119.7512),
                new Campground("Lake Easton State Park",
                        "https://parks.wa.gov/find-parks/state-parks/lake-easton-state-park",
                    47.244156, -121.18592),
                new Campground("Lake Wenatchee State Park",
                        "https://parks.wa.gov/find-parks/state-parks/lake-wenatchee-state-park",
                        47.805176, -120.724404),
                new Campground("Kalaloch Campground",
                        "https://www.recreation.gov/camping/campgrounds/232464/campsites",
                        47.61274, -124.374872),
                new Campground("Grayland Beach State Park",
                        "https://parks.wa.gov/find-parks/state-parks/grayland-beach-state-park",
                        46.79324, -124.091674),
                new Campground("Told-Macdonald Park & Campground",
                        "https://kingcounty.gov/en/dept/dnrp/nature-recreation/parks-recreation/king-county-parks/parks/tolt-macdonald",
                        47.64422, -121.92455),
                new Campground("Wenatchee River County Park",
                        "https://wenatcheeriverpark.org/",
                        47.484820, -120.409516),
                new Campground("Wawawai County Park",
                        "https://whitmancounty.org/Facilities/Facility/Details/Wawawai-County-Park-8",
                        46.636015, -117.375073),
                new Campground("Fort Spokane Campground",
                        "https://www.recreation.gov/camping/campgrounds/234062",
                        47.90993, -118.306435),
                new Campground("Newport / Little Diamond Lake KOA",
                        "https://koa.com/campgrounds/newport-little-diamond/",
                    48.14277, -117.22202),
                new Campground("Dragoon Creek Campground",
                        "https://www.dnr.wa.gov/Campsites",
                        47.888134, -117.441746),
                new Campground("Riverside State Park - Bowl & Pitcher",
                        "https://parks.wa.gov/find-parks/state-parks/riverside-state-park",
                        47.69778, -117.495154),
                new Campground("Lake Spokane Campground",
                        "https://parks.wa.gov/find-parks/state-parks/riverside-state-park",
                    47.835836, -117.762496),
                new Campground("Klipchuck Campground",
                        "https://www.fs.usda.gov/recarea/okawen/recarea/?recid=59281",
                        48.598073, -120.513597)
//                new Campground("",
//                        "",
//                        ),


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
        teannaway.setLatitude(47.25822);
        teannaway.setLongitude(-120.89599);
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
