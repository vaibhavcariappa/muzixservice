package com.stackroute.muzixservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixservice.domain.Artist;
import com.stackroute.muzixservice.domain.Image;
import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exception.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exception.TrackNotFoundException;
import com.stackroute.muzixservice.service.MuzixService;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(MuzixController.class)
public class MuzixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MuzixService muzixService;

    private Image image;
    private Artist artist;
    private Track track;
    private List<Track> trackList;



    @Before
    public void setUp() throws Exception {

        trackList = new ArrayList<>();

        image = new Image(9,"http:url","large");
        artist = new Artist(9, "Avicii", "new url", image);
        track = new Track("track009","Wake Me Up", "Rest in Peace!","123","new track url", artist);
        trackList.add(track);

        image = new Image(10,"http:url","medium");
        artist = new Artist(10, "Poets of the fall", "new url", image);
        track = new Track("track010","Carnival of Rust", "Soulful!","123","new track url", artist);
        trackList.add(track);



    }

    @After
    public void tearDown() throws Exception {

        image = null;
        artist = null;
        track = null;
    }

    @Test
    public void testSaveTrackSuccess() throws Exception {

        when(muzixService.saveTrackToWishList(any())).thenReturn(track);
        mockMvc.perform(post("/api/v1/muzixservice/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(muzixService, times(1)).saveTrackToWishList(any());

    }


    @Test
    public void testSaveTrackFailure() throws Exception {

        when(muzixService.saveTrackToWishList(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(post("/api/v1/muzixservice/track")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isConflict())
                .andDo(print());
        verify(muzixService, times(1)).saveTrackToWishList(any());

    }


    @Test
    public void testUpdateCommentSuccess() throws Exception {

        when(muzixService.updateCommentForTrack(track.getComments(), track.getTrackId())).thenReturn(track);
        mockMvc.perform(put("/api/v1/muzixservice/track/track010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(muzixService, times(1)).updateCommentForTrack(any(), any());

    }

    @Test
    public void testUpdateCommentFailure() throws Exception {

        when(muzixService.updateCommentForTrack(track.getComments(), track.getTrackId())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(put("/api/v1/muzixservice/track/track010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isNotFound())
                .andDo(print());
        verify(muzixService, times(1)).updateCommentForTrack(any(), any());

    }


    @Test
    public void testDeleteTrackSuccess() throws Exception {

        when(muzixService.deleteTrackFromWishList(track.getTrackId())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/muzixservice/track/track010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(muzixService, times(1)).deleteTrackFromWishList(track.getTrackId());

    }


    @Test
    public void testDeleteTrackFailure() throws Exception {

        when(muzixService.deleteTrackFromWishList(track.getTrackId())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(delete("/api/v1/muzixservice/track/track010")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isNotFound())
                .andDo(print());
        verify(muzixService, times(1)).deleteTrackFromWishList(track.getTrackId());

    }

    @Test
    public void testGetAllTracksSuccess() throws Exception {

        when(muzixService.getAllTrackFromWishList()).thenReturn(trackList);
        mockMvc.perform(get("/api/v1/muzixservice/tracks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(muzixService, times(1)).getAllTrackFromWishList();

    }



    private static String jsonToString (final Object ob) throws JsonProcessingException {

        String result;

        try{
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(ob);
        }catch(JsonProcessingException e) {
            result = "Json processing error";
        }

        return result;
    }
}