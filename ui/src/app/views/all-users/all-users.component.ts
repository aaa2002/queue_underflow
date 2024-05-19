import {Component, OnInit} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {NgForOf, NgIf} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {Router} from '@angular/router';
import {NotificationService} from "../../service/notificationService";

@Component({
  selector: 'app-all-users',
  standalone: true,
  imports: [MatCardModule, NgForOf, MatButtonModule, NgIf],
  templateUrl: './all-users.component.html',
  styleUrls: ['./all-users.component.scss']
})
export class AllUsersComponent implements OnInit {
  usersList: any = [];

  constructor(private router: Router, private notificationService: NotificationService) {
  }

  ngOnInit() {
    const isModerator = localStorage.getItem('moderator') === 'true';
    if (!isModerator) {
      this.router.navigate(['/login']);
      this.notificationService.show('You are not authorized to view this page');
    } else {
      this.fetchAllUsers();
    }
  }

  fetchAllUsers() {
    fetch('http://localhost:8080/users/all')
      .then((response) => response.json())
      .then((data) => {
        this.usersList = data;
      });
  }

  isCurrentUser(user: any) {
    console.log(user.email, localStorage.getItem('activeUser'));
    return user.email === localStorage.getItem('activeUser');
  }

  banUser(user: any) {
  }

  unbanUser(user: any) {}

  deleteUser(user: any) {
    fetch(`http://localhost:8080/users/delete/${user.id}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
      });
    this.fetchAllUsers();
  }
}
