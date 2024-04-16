import {Component} from '@angular/core';
import {QuestionCardComponent} from "../../components/question-card/question-card.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-questions',
  standalone: true,
  imports: [
    QuestionCardComponent,
    NgForOf
  ],
  templateUrl: './questions.component.html',
  styleUrl: './questions.component.scss'
})
export class QuestionsComponent {
  mockQuestions = [
    {
      "id": 1,
      "user": {
        "id": 1,
        "name": "Test user 1",
        "email": "dsdsadsadd",
        "password": "$2a$10$eO74b2tPFuNeApDDWnls0uzOAMC8glzq.J.NOAPfCb4tx1JFMz0g6",
        "bio": "Hello! I'm a user",
        "score": 0,
        "role": "USER",
        "accountStatus": "ACTIVE"
      },
      "title": "Q1 Test",
      "text": "Uh, this is my first question, hope it's good.",
      "createdAt": "2024-04-04T11:11:11.792557Z",
      "image": null,
      "score": 0
    },
    {
      "id": 1,
      "user": {
        "id": 1,
        "name": "Test user 1",
        "email": "dsdsadsadd",
        "password": "$2a$10$eO74b2tPFuNeApDDWnls0uzOAMC8glzq.J.NOAPfCb4tx1JFMz0g6",
        "bio": "Hello! I'm a user",
        "score": 0,
        "role": "USER",
        "accountStatus": "ACTIVE"
      },
      "title": "Q1 Test",
      "text": "Uh, this is my first question, hope it's good.",
      "createdAt": "2024-04-04T11:11:11.792557Z",
      "image": null,
      "score": 0
    }
  ];

  questions: any;

  constructor() {
  }

  ngOnInit() {
    this.fetchQuestions();
  }

  fetchQuestions(): any {
    fetch('http://localhost:8080/questions/all')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch questions');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        this.questions = data;
        return data;
      })
      .catch(error => {
        console.error('Error fetching questions:', error);
        throw error;
      });
  }
}
