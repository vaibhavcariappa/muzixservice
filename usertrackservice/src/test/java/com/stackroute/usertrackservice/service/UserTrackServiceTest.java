package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.config.Producer;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserTrackServiceTest {

    @Mock
    private UserTrackRepository userTrackRepository;

    @Mock
    private Producer producer;

    private User user;
    private Track track;
    private Artist artist;
    private List<Track> trackList;

    @InjectMocks
    UserTrackServiceImpl userTrackService;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        Image image  = new Image(1,"http://url.com","large" );
        artist = new Artist(1,"Five for Fighting","http://newurl.com", image);
        track = new Track("track001", "Superman", "Nice Song", "track listener", "http://trackurl.com", artist);
        trackList = new ArrayList<>();
        trackList.add(track);
        user = new User("John","john@example.com", trackList);
        userTrackRepository.deleteAll();

    }

    @After
    public void tearDown() throws Exception {

        userTrackRepository.deleteAll();
        trackList = null;
        artist = null;
        track = null;
        user = null;

    }

    @Test
    public void testSaveUserTrackToWishListSuccess() throws TrackAlreadyExistsException {
        user = new User("Mary","mary@example.com", null);
        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser = userTrackService.saveUserTrackToWishList(user.getUsername(), track);
        Assert.assertEquals(fetchedUser, user);
        verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
        verify(userTrackRepository, times(1)).save(user);
    }


    @Test
    public void testDeleteUserTrackFromWishListSuccess() throws TrackNotFoundException {

        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser = userTrackService.deleteUserTrackFromWishList(user.getUsername(), track.getTrackId());
        Assert.assertEquals(fetchedUser, user);
        verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
        verify(userTrackRepository, times(1)).save(user);
    }


    @Test
    public void testUpdateCommentForTrackSuccess() throws TrackNotFoundException {

        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        User fetchedUser = userTrackService.updateCommentForTrack(user.getUsername(), track.getTrackId(), "New Comments");
        Assert.assertEquals(fetchedUser.getTrackList().get(0).getComments(), "New Comments");
        verify(userTrackRepository, times(1)).findByUsername(user.getUsername());
        verify(userTrackRepository, times(1)).save(user);
    }


    @Test
    public void testAllUserTracksFromWishListSuccess() throws Exception {

        when(userTrackRepository.findByUsername(user.getUsername())).thenReturn(user);
        List<Track> fetchedTrackList= userTrackService.getAllUserTracksFromWishList(user.getUsername());
        Assert.assertEquals(fetchedTrackList, trackList);
        verify(userTrackRepository, times(1)).findByUsername(user.getUsername());

    }
}
