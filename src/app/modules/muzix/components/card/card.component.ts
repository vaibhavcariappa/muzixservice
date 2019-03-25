import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Track } from 'src/app/modules/muzix/track';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { DialogComponent } from 'src/app/modules/muzix/components/dialog/dialog.component';


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

@Input()
track: Track; 

@Input()
wishData: boolean;

@Output()
addToWishList = new EventEmitter();

@Output()
deleteFromWishList = new EventEmitter();

@Output()
updateComments = new EventEmitter();
  
  constructor(private dialog: MatDialog) {

  }

  addButtonClick(track) {
    console.log('Track Details:', track);
    this.addToWishList.emit(track);
  }

  deleteButtonClick(track) {
    this.deleteFromWishList.emit(track);
  }

  addComments(){
    const dialogRef = this.dialog.open(DialogComponent, {
      width: "250px",
      data: {comments: this.track.comments}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog close result:', result);
      if(typeof result!='undefined' && result) {
        this.track.comments = result;
        this.updateComments.emit(this.track);
      }
    });
  }



  ngOnInit() {
    console.log('wishData', this.wishData);
  }

}
