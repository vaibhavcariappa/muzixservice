package com.stackroute.usertrackservice.repository;


import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserTrackRepositoryTest {

    @Autowired
    private UserTrackRepository userTrackRepository;

    private Track track;
    private User user;

    @Before
    public void setUp() throws Exception {

        Image image  = new Image(1,"http://url.com","large" );
        Artist artist = new Artist(1,"Five for Fighting","http://newurl.com", image);
        track = new Track("track001", "Superman", "Nice Song", "track listerner", "http://trackurl.com", artist);
        List<Track> trackList = new ArrayList<>();
        trackList.add(track);
        user = new User("John","john@example.com", trackList);


    }

    @After
    public void tearDown() throws Exception {
        userTrackRepository.deleteAll();

    }

    @Test
    public void testSaveUserTrack() {

        userTrackRepository.save(user);
        User fetchUser = userTrackRepository.findByUsername(user.getUsername());
        List <Track> trackList = fetchUser.getTrackList();
        Assert.assertEquals(trackList.get(0).getTrackId(), user.getTrackList().get(0).getTrackId());
    }

}
