import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';
import { ViewChild } from '@angular/core';
import { MatMenuTrigger } from '@angular/material';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;

  constructor(private authService: AuthenticationService, private route: Router) { }

  logout() {
    this.authService.logout();
    console.log('Header logout');
    this.route.navigate(['/Login']);
  }

  displayTrackMenuList() {
    console.log('displayTrackMenuList');
    if (!this.authService.isTokenActive()) {
      console.log('Inside if condition');
      this.trigger.closeMenu();
      this.route.navigate(['/Login']);
    }
  }
  
  ngOnInit() {
  }

}
