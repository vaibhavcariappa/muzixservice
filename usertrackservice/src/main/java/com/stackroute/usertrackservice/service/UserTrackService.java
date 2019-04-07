package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserTrackService {

    User saveUserTrackToWishList(String username, Track track) throws TrackAlreadyExistsException;

    User deleteUserTrackFromWishList(String username, String trackId) throws TrackNotFoundException;

    User updateCommentForTrack(String username, String trackId, String comments) throws TrackNotFoundException;

    List<Track> getAllUserTracksFromWishList(String username) throws Exception;

    User registerUser(User user) throws UserAlreadyExistsException;

}
