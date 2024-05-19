import {Component, OnInit, ViewChild} from '@angular/core';
import {QuestionsComponent} from "../questions/questions.component";
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDrawerContainer} from "@angular/material/sidenav";
import {MatDrawer} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-main-wrapper',
  standalone: true,
  imports: [
    QuestionsComponent,
    MatToolbarModule, MatButtonModule, MatIconModule, MatDrawerContainer, MatDrawer, MatMenuModule, RouterOutlet, RouterLink, NgIf
  ],
  templateUrl: './main-wrapper.component.html',
  styleUrl: './main-wrapper.component.scss'
})
export class MainWrapperComponent implements OnInit{
  @ViewChild('drawer') drawer!: MatDrawer;
  activeUser: any;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.fetchActiveUserData();
  }

  toggleDrawer() {
    this.drawer.toggle();
  }

  getActiveUser(): string {
    if (typeof localStorage !== 'undefined' && localStorage !== null) {
      return "User: " + localStorage.getItem('activeUser');
    }
    return 'User: none';
  }

  logout() {
    localStorage.removeItem('activeUser');
    localStorage.setItem('moderator', 'false');
    this.router.navigate(['/login']);
  }

  openProfile() {
    this.router.navigate(['/main/profile'], {queryParams: {user: localStorage.getItem('activeUser')}});
  }

  activeUserIsModerator(): boolean {
    return this.activeUser && this.activeUser.role === 'MODERATOR';
  }

  fetchActiveUserData(): any {
    fetch('http://localhost:8080/users/user/' + localStorage.getItem('activeUser'))
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch user data');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        this.activeUser = data;
        if (data.role === 'MODERATOR') {
          localStorage.setItem('moderator', 'true');
        } else {
          localStorage.setItem('moderator', 'false');
        }
        return data;
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
        throw error;
      });
  }
}
