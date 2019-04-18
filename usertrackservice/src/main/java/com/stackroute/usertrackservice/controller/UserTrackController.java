package com.stackroute.usertrackservice.controller;


import com.stackroute.usertrackservice.domain.Track;
import com.stackroute.usertrackservice.domain.User;
import com.stackroute.usertrackservice.exception.TrackAlreadyExistsException;
import com.stackroute.usertrackservice.exception.TrackNotFoundException;
import com.stackroute.usertrackservice.exception.UserAlreadyExistsException;
import com.stackroute.usertrackservice.service.UserTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usertrackservice")
public class UserTrackController {

    private UserTrackService userTrackService;
    private ResponseEntity responseEntity;

    public UserTrackController() {
    }

    @Autowired
    public UserTrackController(UserTrackService userTrackService) {
        this.userTrackService = userTrackService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {

        try{
            userTrackService.registerUser(user);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @PostMapping("/user/{username}/track")
    public ResponseEntity<?> saveUserTrackToWishList(@PathVariable ("username") String username, @RequestBody Track track) throws TrackAlreadyExistsException {

        try{
            User user = userTrackService.saveUserTrackToWishList(username, track);
            responseEntity = new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            throw new TrackAlreadyExistsException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @DeleteMapping("/user/{username}/{trackId}")
    public ResponseEntity<?> deleteUserTrackToWishList(@PathVariable ("username") String username, @PathVariable ("trackId") String trackId) throws TrackNotFoundException {

        try{
            User user = userTrackService.deleteUserTrackFromWishList(username, trackId);
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }


    @PatchMapping("/user/{username}/track")
    public ResponseEntity<?> updateCommentForUserTrack(@PathVariable ("username") String username, @RequestBody Track track) throws TrackNotFoundException {

        try{
            userTrackService.updateCommentForTrack(username, track.getTrackId(), track.getComments());
            responseEntity = new ResponseEntity<>(track, HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("/user/{username}/tracks")
    public ResponseEntity<?> getAllUserTracksFromWishList(@PathVariable ("username") String username) {

        try{
            responseEntity = new ResponseEntity<>(userTrackService.getAllUserTracksFromWishList(username), HttpStatus.OK);

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
