import { Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {QuestionsComponent} from "./views/questions/questions.component";
import {MainWrapperComponent} from "./views/main-wrapper/main-wrapper.component";
import {UserProfileComponent} from "./views/user-profile/user-profile.component";
import {QuestionViewComponent} from "./views/question-view/question-view.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent, pathMatch: 'full'},
  {path: 'register', component: LoginComponent, pathMatch: 'full', data: {registerForm: true}},
  {path: 'main', component: MainWrapperComponent,
    children: [
      {path: 'questions', component: QuestionsComponent, pathMatch: 'full'},
      {path: 'profile', component: UserProfileComponent, pathMatch: 'full'},
      {path: '', redirectTo: 'questions', pathMatch: 'full'},
      {path: 'question/:question_id', component: QuestionViewComponent, pathMatch: 'full'}
    ]
  },
];
