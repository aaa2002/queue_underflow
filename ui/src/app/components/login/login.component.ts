import {Component, Input} from '@angular/core';
import {MatFormField, MatFormFieldModule, MatLabel} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

interface registerFormData {
  name: string;
  email: string;
  password: string;
  bio?: string;
  role?: number;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatFormField,
    MatLabel, MatFormFieldModule, MatInputModule, MatIconModule, MatButtonModule, MatDividerModule, MatIconModule, MatCardModule, NgIf, FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @Input() registerForm: boolean = false;
  formData: registerFormData = {
    name: '',
    email: '',
    password: '',
    bio: '',
    role: 0
  };

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
            role: 0
          })
        })
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
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
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            if (response.status === 200) {
              console.log('Login successful');
              localStorage.setItem('activeUser', this.formData.email);
            }
            return response;
          })
          .then(data => console.log(data))
          .catch(error => console.error('Error:', error));
        break;
    }
  }
}