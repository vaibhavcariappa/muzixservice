import { Component, OnInit } from '@angular/core';
import { Track} from './../../track';
import { Artist} from './../../artist';
import { Image} from './../../image';
import { MuzixService } from 'src/app/modules/muzix/muzix.service';
import { ActivatedRoute }  from '@angular/router';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {

  tracks: Array<Track>;
  trackObj: Track;
  artistObj: Artist;
  imageObj: Image;
  countryName: string;
  id: number;
  statusCode: number;
  errorStatus: string;
  artistName: string;
  searchTracks: Array<Track>;



  constructor(
     private muzixService: MuzixService,
     private routes: ActivatedRoute,
     private matSnackBar: MatSnackBar
     ) {
    this.tracks = [];
   }

  ngOnInit() {

    const tempData = this.routes.data.subscribe(newdata => {
      this.countryName = newdata.country;
      console.log('CountryName ', this.countryName);
    });

    this.muzixService.getTrackDetails(this.countryName).subscribe(tracks => 
      {console.log(tracks);
      this.tracks = [];

      const data =  tracks['tracks']['track'];
      this.id = 0;

      data.forEach(trackBlock => {
        this.id ++; 
        this.imageObj = new Image();
        this.artistObj = new Artist();
        this.trackObj = new Track();
        this.imageObj.text = trackBlock["image"][2]["#text"];
        this.imageObj.size = trackBlock["image"][2]["size"];
        this.artistObj = trackBlock["artist"];
        this.trackObj = trackBlock;
        this.artistObj.image = this.imageObj;
        this.trackObj.artist = this.artistObj;
        this.trackObj.trackId = this.countryName.slice(0,3) + this.id;

        this.tracks.push(this.trackObj);

        this.searchTracks = this.tracks;
      });
    });
    
  }


  onKey(event: any) {
    this.artistName = event.target.value;
    console.log("artistName", this.artistName);

    const result = this.searchTracks.filter(track => {
      return track.artist.name.match(this.artistName);
    });
    console.log(result, "Filtered Data");
    this.tracks = result;
  }





  addToWishList(track) {
    console.log('Inside the card container',track);
    this.muzixService.addTrackToWishList(track).subscribe(
      data=>{ 
        console.log(data);
        this.statusCode = data.status;
        if(this.statusCode === 201) {
          console.log("Success", this.statusCode);
          this.matSnackBar.open("Track successfully added!","",{duration: 2000});
        }


      },
      error=>{
        this.errorStatus = `${error.status}`;
        const errorMsg = `${error.error.message}`;
        console.log("errorStatus:", this.errorStatus);
        console.log("errorMsg:", errorMsg);
        this.statusCode = parseInt(this.errorStatus, 10);
        if(this.statusCode === 409) {
          this.matSnackBar.open(errorMsg,"",{duration: 2000});
        }
        this.statusCode = 0;


      }
    );
  }

}
