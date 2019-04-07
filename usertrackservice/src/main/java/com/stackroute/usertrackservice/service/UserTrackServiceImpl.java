package com.stackroute.usertrackservice.service;

import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.repository.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrackServiceImpl implements UserTrackService{

    private UserTrackRepository userTrackRepository;

    @Autowired
    public UserTrackServiceImpl(UserTrackRepository userTrackRepository) {
        this.userTrackRepository = userTrackRepository;
    }


    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        User fetchedUserObj = userTrackRepository.findByUsername(user.getUsername());
        if(fetchedUserObj != null) {
            throw new UserAlreadyExistsException();
        } else {
            userTrackRepository.save(user);
        }
        return user;
    }


    @Override
    public User saveUserTrackToWishList(String username, Track track) throws TrackAlreadyExistsException {

        User fetchUser = userTrackRepository.findByUsername(username);
        List<Track> fetchTracks = fetchUser.getTrackList();

        if(fetchTracks != null) {
            for (Track trackObj: fetchTracks) {
                if(trackObj.getTrackId().equals(track.getTrackId())) {
                    throw new TrackAlreadyExistsException();
                }
            }
            fetchTracks.add(track);
            fetchUser.setTrackList(fetchTracks);
            userTrackRepository.save(fetchUser);
        } else {

            fetchTracks = new ArrayList<Track>();
            fetchTracks.add(track);
            fetchUser.setTrackList(fetchTracks);
            userTrackRepository.save(fetchUser);
        }
        return fetchUser;

    }


    @Override
    public User deleteUserTrackFromWishList(String username, String trackId) throws TrackNotFoundException {

        User fetchUser = userTrackRepository.findByUsername(username);
        List<Track> fetchTracks = fetchUser.getTrackList();

        if(fetchTracks.size() > 0) {
            for(int i=0; i < fetchTracks.size(); i++) {

                if(trackId.equals(fetchTracks.get(i).getTrackId())) {
                    fetchTracks.remove(i);
                    fetchUser.setTrackList(fetchTracks);
                    userTrackRepository.save(fetchUser);
                    break;
                }

            }
        } else {
            throw new TrackNotFoundException();
        }

        return fetchUser;
    }


    @Override
    public User updateCommentForTrack(String username, String trackId, String comments) throws TrackNotFoundException {

        User fetchUser = userTrackRepository.findByUsername(username);
        List<Track> fetchTracks = fetchUser.getTrackList();

        if(fetchTracks.size() > 0) {
            for(int i=0; i < fetchTracks.size(); i++) {

                if(trackId.equals(fetchTracks.get(i).getTrackId())) {
                    fetchTracks.get(i).setComments(comments);
                    userTrackRepository.save(fetchUser);
                    break;
                }

            }
        } else {
            throw new TrackNotFoundException();
        }

        return fetchUser;
    }


    @Override
    public List<Track> getAllUserTracksFromWishList(String username) throws Exception {
        User user = userTrackRepository.findByUsername(username);
        return user.getTrackList();
    }

}
