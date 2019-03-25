package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MuzixServiceTest {

    @Mock
    private MuzixRepository muzixRepository;

    private Image image;
    private Artist artist;
    private Track track;

    private Optional optional;
    private List<Track> trackList;

    @InjectMocks
    private MuzixServiceImpl muzixService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        image = new Image(1,"http:url","large");
        artist = new Artist(9, "Avicii", "new url", image);
        track = new Track("track009","Wake Me Up", "Rest in Peace!","123","new track url", artist);

        trackList = new ArrayList<>();
        trackList.add(track);
        optional = Optional.of(track);
    }

    @After
    public void tearDown() {

        image = null;
        artist = null;
        track = null;

    }

    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException {

        when(muzixRepository.insert(track)).thenReturn(track);

        Track fetchTrack = muzixService.saveTrackToWishList(track);
        Assert.assertEquals(track, fetchTrack);
        verify(muzixRepository, times(1)).insert(track);
        verify(muzixRepository, times(1)).findById(track.getTrackId());

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException {

        when(muzixRepository.insert(track)).thenReturn(track);
        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);

        Track fetchTrack = muzixService.saveTrackToWishList(track);
        Assert.assertEquals(track, fetchTrack);
        verify(muzixRepository, times(1)).insert(track);
        verify(muzixRepository, times(0)).findById(track.getTrackId());
    }

    @Test
    public void testUpdateCommentSuccess() throws TrackNotFoundException {

        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);
        track.setComments("Testing the comments update");

        Track fetchTrack = muzixService.updateCommentForTrack(track.getComments(), track.getTrackId());
        System.out.println("Fetching Track Info\n" + fetchTrack.toString());
        Assert.assertEquals("Testing the comments update", fetchTrack.getComments());

        verify(muzixRepository, times(1)).save(track);
        verify(muzixRepository, times(2)).findById(track.getTrackId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void testUpdateCommentFailure() throws TrackNotFoundException {

        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.empty());
        track.setComments("Testing the comments update");

        Track fetchTrack = muzixService.updateCommentForTrack(track.getComments(), track.getTrackId());
        System.out.println("Fetching Track Info\n" + fetchTrack.toString());
        Assert.assertEquals("Testing the comments update", fetchTrack.getComments());

        verify(muzixRepository, times(0)).save(track);
        verify(muzixRepository, times(1)).findById(track.getTrackId());

    }


    @Test
    public void testDeleteTrackSuccess() throws TrackNotFoundException {

        when(muzixRepository.findById(track.getTrackId())).thenReturn(optional);

        boolean fetchTrack = muzixService.deleteTrackFromWishList(track.getTrackId());
        Assert.assertEquals(true, fetchTrack);

        verify(muzixRepository, times(1)).deleteById(track.getTrackId());
        verify(muzixRepository, times(1)).findById(track.getTrackId());

    }

    @Test(expected = TrackNotFoundException.class)
    public void testDeleteTrackFailure() throws TrackNotFoundException {

        when(muzixRepository.findById(track.getTrackId())).thenReturn(Optional.empty());

        boolean fetchTrack = muzixService.deleteTrackFromWishList(track.getTrackId());
        Assert.assertEquals(true, fetchTrack);

        verify(muzixRepository, times(1)).findById(track.getTrackId());

    }

    @Test
    public void testGetAllTracks() throws Exception {

        when(muzixRepository.findAll()).thenReturn(trackList);

        List<Track> allTracks = muzixService.getAllTrackFromWishList();
        Assert.assertEquals(allTracks, trackList);

        verify(muzixRepository, times(1)).findAll();

    }

}
