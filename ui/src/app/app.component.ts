import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {QuestionsComponent} from "./views/questions/questions.component";
import {NgIf} from "@angular/common";
import {MainWrapperComponent} from "./views/main-wrapper/main-wrapper.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, QuestionsComponent, NgIf, MainWrapperComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  isUserActive(): boolean {
    return !!localStorage.getItem('activeUser');
  }
}
