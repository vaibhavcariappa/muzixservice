package com.stackroute.muzixservice.repository;


import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MuzixRepositoryTest {

    @Autowired
    private MuzixRepository muzixRepository;

    private Image image;
    private Artist artist;
    private Track track;

    @Before
    public void setup(){
        image = new Image(9,"http:url","large");
        artist = new Artist(9, "Avicii", "new url", image);
        track = new Track("track009","Wake Me Up", "Rest in Peace!","123","new track url", artist);
    }

    @After
    public void tearDown() {

        image = null;
        artist = null;
        track = null;
        muzixRepository.deleteAll();

    }

    @Test
    public void testSaveTrack() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(fetchTrack.getTrackName(), track.getTrackName());

    }

    @Test
    public void testUpdateComments() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        fetchTrack.setComments("Updating Comments");
        muzixRepository.save(fetchTrack);
        Track fetchTrackUpdated = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals("Updating Comments", fetchTrackUpdated.getComments());

    }

    @Test
    public void testDeleteTracks() {
        muzixRepository.insert(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        muzixRepository.delete(fetchTrack);
        Assert.assertEquals(Optional.empty(), muzixRepository.findById(fetchTrack.getTrackId()));
    }

    @Test
    public void testGetAllTrack() {
        muzixRepository.insert(track);
        image = new Image(10,"http:url","medium");
        artist = new Artist(10, "Poets of the fall", "new url", image);
        track = new Track("track010","Carnival of Rust", "Soulful!","123","new track url", artist);
        muzixRepository.insert(track);
        List<Track> trackList = muzixRepository.findAll();
        Assert.assertEquals(2, trackList.size());
        Assert.assertEquals("track010", trackList.get(1).getTrackId());

    }
}
