import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track } from 'src/app/modules/muzix/track';
import { USER_NAME } from 'src/app/modules/authentication/authentication.service';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MuzixService {

  thirdPartyApi: string;
  apiKey: string;
  springEndPoint: string;
  username: string;

  constructor(private httpClient: HttpClient) { 
    this.thirdPartyApi = "http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=";
    this.apiKey = "&api_key=98903418fc1d6aa48061226cc49aee58&format=json";
    //this.springEndPoint = "http://localhost:8083/api/v1/muzixservice/";
    this.springEndPoint = "http://localhost:8085/api/v1/usertrackservice/";
  }

  getTrackDetails(country: string): Observable<any> {
    const url = `${this.thirdPartyApi}${country}${this.apiKey}`;
    return this.httpClient.get(url);
  }


  addTrackToWishList(track: Track){
    this.username = sessionStorage.getItem(USER_NAME);
    //const url = this.springEndPoint + "track";
    const url = this.springEndPoint + "user/" + this.username + "/track";
    console.log("New url: " + url);
    return this.httpClient.post(url, track, {observe: "response"});
  }

  getAllTracksFromWishList(){
    this.username = sessionStorage.getItem(USER_NAME);
    //const url = this.springEndPoint + "tracks";
    const url = this.springEndPoint + "user/" + this.username + "/tracks";
    return this.httpClient.get<Track[]>(url);
  }

  deleteTrackFromWishList(track: Track) {
    this.username = sessionStorage.getItem(USER_NAME);
    //const url = this.springEndPoint + "track/" + `${track.trackId}`;
    const url = this.springEndPoint + "user/" + this.username + "/track";
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: track
    };
    return this.httpClient.delete(url, options);
  }  

  updateComments(track) {
    this.username = sessionStorage.getItem(USER_NAME);
    //const id = track.trackId;
    //const url = this.springEndPoint + "track/" + `${id}`;
    //console.log("url", url);
    console.log("track.comments", track.comments);
    const url = this.springEndPoint + "user/" + this.username + "/track";
    return this.httpClient.patch(url, track, {observe: "response"});
  }  

  filterArtists(tracks: Array<Track>, artistName: string) {
    const results = tracks.filter(track =>{
      return track.artist.name.match(artistName);
    });
    console.log("Filtered Artists" , results);
    return results;
  }

  

}
