import {Component, ViewChild} from '@angular/core';
import {QuestionsComponent} from "../questions/questions.component";
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDrawerContainer} from "@angular/material/sidenav";
import {MatDrawer} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {Router, RouterLink, RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-main-wrapper',
  standalone: true,
  imports: [
    QuestionsComponent,
    MatToolbarModule, MatButtonModule, MatIconModule, MatDrawerContainer, MatDrawer, MatMenuModule, RouterOutlet, RouterLink
  ],
  templateUrl: './main-wrapper.component.html',
  styleUrl: './main-wrapper.component.scss'
})
export class MainWrapperComponent {
  @ViewChild('drawer') drawer!: MatDrawer;

  constructor(private router: Router) {
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
    this.router.navigate(['/login']);
  }

  openProfile() {
    this.router.navigate(['/main/profile'], {queryParams: {user: localStorage.getItem('activeUser')}});
  }
}
