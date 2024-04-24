import {Component, Input, OnInit} from '@angular/core';
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatMenuModule} from '@angular/material/menu';
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";
import {NotificationService} from "../../service/notificationService";

interface registerFormData {
  name: string;
  email: string;
  password: string;
  bio?: string;
  role?: number;
  avatar?: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel, MatFormFieldModule, MatInputModule, MatIconModule, MatButtonModule, MatDividerModule, MatIconModule, MatCardModule, NgIf, FormsModule, MatMenuModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  @Input() registerForm: boolean = false;
  formData: registerFormData = {
    name: '',
    email: '',
    password: '',
    bio: '',
    role: 0,
    avatar: 'darkgrey'
  };

  constructor(private route: ActivatedRoute, private router: Router, private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.route.data.subscribe(data => {
      this.registerForm = data['registerForm'] || false;
    });
  }

  toggleForm() {
    this.registerForm = !this.registerForm;
  }

  selectAvatar(color: string) {
    this.formData.avatar = color;
    console.log(this.formData)
  }

  submitForm() {
    switch (this.registerForm) {
      case true:
        console.log('Register form');
        fetch('http://localhost:8080/users/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            email: this.formData.email,
            name: this.formData.name,
            password: this.formData.password,
            bio: '',
            role: 0,
            avatar: this.formData.avatar
          })
        })
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
              this.notificationService.show('Registration failed');
            }
            this.notificationService.show('Registration successful');
            this.formData = {
              name: '',
              email: '',
              password: '',
              bio: '',
              role: 0,
              avatar: 'darkgrey'
            };
            this.router.navigate(['/login']).then(r => console.log(r));
            return response;
          })
          .then(data => console.log(data))
          .catch(error => console.error('Error:', error));
        break;
      case false:
        console.log('Login form');
        fetch('http://localhost:8080/users/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            email: this.formData.email,
            password: this.formData.password
          })
        })
          .then(response => {
            console.log("here")
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            if (response.status === 200) {
              console.log('Login successful');
              localStorage.setItem('activeUser', this.formData.email);
              this.router.navigate(['/main/questions']).then(r => console.log(r));
            }
            return response;
          })
          .then(data => console.log(data))
          .catch(error => console.error('Error:', error));
        break;
    }
  }
}
