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
        this.questions = this.questions.sort((a: any, b: any) => {
          return new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime();
        });
        return data;
      })
      .catch(error => {
        console.error('Error fetching questions:', error);
        throw error;
      });
  }
}
