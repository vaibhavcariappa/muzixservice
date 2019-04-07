package com.stackroute.usertrackservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usertrackservice.domain.Artist;
import com.stackroute.usertrackservice.domain.Image;
import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserTrackController.class)
public class UserTrackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserTrackService userTrackService;

    private User user;
    private Track track;
    private Artist artist;
    private Image image;
    private List<Track> trackList;

    @Before
    public void setUp() throws Exception {

        trackList = new ArrayList<>();

        image  = new Image(1,"http://url.com","large" );
        artist = new Artist(1,"Five for Fighting","http://newurl.com", image);
        track = new Track("track001", "Superman", "Nice Song!", "track listener", "http://trackurl.com", artist);
        trackList.add(track);


        image  = new Image(2,"http://url.com","large" );
        artist = new Artist(2,"Zedd","http://newurl.com", image);
        track = new Track("track002", "Beautiful Now", "Nice Song too!", "track listener", "http://trackurl.com", artist);
        trackList.add(track);

        user = new User("John","john@example.com", trackList);
    }


    @After
    public void tearDown() throws Exception {
        image = null;
        artist = null;
        track = null;
        trackList = null;
        user = null;
    }


    @Test
    public void testSaveUserTrackToWishListSuccess() throws Exception{

        when(userTrackService.saveUserTrackToWishList(eq(user.getUsername()), any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/usertrackservice/user/{username}/track", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(userTrackService, times(1)).saveUserTrackToWishList(eq(user.getUsername()), any());
    }


    @Test
    public void testSaveUserTrackToWishListFailure() throws Exception{

        when(userTrackService.saveUserTrackToWishList(eq(user.getUsername()), any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/usertrackservice/user/{username}/track", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isConflict())
                .andDo(print());

        verify(userTrackService, times(1)).saveUserTrackToWishList(eq(user.getUsername()), any());
    }


    @Test
    public void testUpdateCommentForTrackSuccess() throws Exception{

        when(userTrackService.updateCommentForTrack(user.getUsername(),track.getTrackId(), track.getComments())).thenReturn(user);
        mockMvc.perform(patch("/api/v1/usertrackservice/user/{username}/track", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService, times(1)).updateCommentForTrack(user.getUsername(),track.getTrackId(), track.getComments());
    }


    @Test
    public void testDeleteUserTrackFromWishListSuccess() throws Exception{

        when(userTrackService.deleteUserTrackFromWishList(user.getUsername(),track.getTrackId())).thenReturn(user);
        mockMvc.perform(delete("/api/v1/usertrackservice/user/{username}/track", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService, times(1)).deleteUserTrackFromWishList(user.getUsername(),track.getTrackId());
    }


    @Test
    public void testGetAllUserTracksFromWishListSuccess() throws Exception{

        when(userTrackService.getAllUserTracksFromWishList(user.getUsername())).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/usertrackservice/user/{username}/tracks", user.getUsername())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(userTrackService, times(1)).getAllUserTracksFromWishList(user.getUsername());
    }


    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result;
        try{
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "Json Processing error";
        }
        return result;
    }
}
