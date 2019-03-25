import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardComponent } from 'src/app/modules/muzix/components/card/card.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { AppRoutingModule } from '../../app-routing.module';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { LogoComponent } from './components/logo/logo.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogComponent } from './components/dialog/dialog.component';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';


@NgModule({
  declarations: [
    CardContainerComponent,
    CardComponent,
    HeaderComponent,
    WishListComponent,
    FooterComponent,
    LogoComponent,
    DialogComponent
  ],
  
  imports: [
    CommonModule,
    MatCardModule, 
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatMenuModule,
    AppRoutingModule,
    MatSnackBarModule,
    MatDialogModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule
  ],

  exports: [
    CardContainerComponent,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    HeaderComponent,
    AppRoutingModule,
    FooterComponent,
    WishListComponent,
    MatSnackBarModule
  ],

  entryComponents: [
    DialogComponent
  ],
})
export class MuzixModule { }
