import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {QuestionsComponent} from "./views/questions/questions.component";
import {MainWrapperComponent} from "./views/main-wrapper/main-wrapper.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'register', component: LoginComponent, pathMatch: 'full', data: {registerForm: true}},
  {path: 'main', component: MainWrapperComponent, pathMatch: 'full'}
];
