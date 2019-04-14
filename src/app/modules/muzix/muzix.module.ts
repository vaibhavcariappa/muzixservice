import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardComponent } from 'src/app/modules/muzix/components/card/card.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { AppRoutingModule } from '../../app-routing.module';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { LogoComponent } from './components/logo/logo.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { AngularmaterialModule } from '../angularmaterial/angularmaterial.module';
import { MuzixService } from 'src/app/modules/muzix/muzix.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from 'src/app/modules/muzix/interceptor.service';




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
    BrowserAnimationsModule,
    AppRoutingModule,
    AngularmaterialModule
  ],

  exports: [
    CardContainerComponent,
    HeaderComponent,
    AppRoutingModule,
    FooterComponent,
    WishListComponent

  ],
  providers: [
    MuzixService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }

  ],
  entryComponents: [
    DialogComponent
  ],
})
export class MuzixModule { }
