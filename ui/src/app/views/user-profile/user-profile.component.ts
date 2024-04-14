import { Component } from '@angular/core';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.scss'
})
export class UserProfileComponent {
  activeUser: any;
  constructor() {
  }

  ngOnInit() {
    this.fetchActiveUserData();
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
        return data;
      })
      .catch(error => {
        console.error('Error fetching user data:', error);
        throw error;
      });
  }
}
