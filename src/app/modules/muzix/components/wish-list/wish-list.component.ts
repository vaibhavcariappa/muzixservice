import { Component, OnInit } from '@angular/core';
import { MuzixService } from 'src/app/modules/muzix/muzix.service';
import { Track} from './../../track';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-wish-list',
  templateUrl: './wish-list.component.html',
  styleUrls: ['./wish-list.component.css']
})
export class WishListComponent implements OnInit {

  tracks: Array<Track>;
  wishData = true;

  constructor(
    private muzixService: MuzixService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {

    const message = "WishList is empty!";
    this.muzixService.getAllTracksFromWishList().subscribe(data=>{
      this.tracks = data;
      if(data.length === 0) {
        this.snackBar.open(message,"",{duration: 2000});
      }
    });
  }

  deleteFromWishList(track) {
    this.muzixService.deleteTrackFromWishList(track).subscribe(data=>{
      console.log("Deleted:",data);
      const index = this.tracks.indexOf(track);
      this.tracks.splice(index, 1);
      this.snackBar.open(data, "", {duration: 2000});

    });
    return this.tracks;
  }

  updateComments(track) {
    this.muzixService.updateComments(track).subscribe(
      data=>{
      console.log("comments:",data);
      this.snackBar.open("Comments successfully updated!", "", {duration: 2000});
      },
      error=>{
        console.log("error during update in wish-listcomponent",error);
      }
    );
  }

}
