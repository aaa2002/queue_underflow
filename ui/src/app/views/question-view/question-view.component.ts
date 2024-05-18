import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatCardModule} from '@angular/material/card';
import {NgForOf, NgIf} from "@angular/common";
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatButton, MatMiniFabButton} from "@angular/material/button";
import {MatChip} from "@angular/material/chips";


@Component({
  selector: 'app-question-view',
  standalone: true,
    imports: [MatCardModule, NgIf, MatIconModule, MatProgressSpinnerModule, NgForOf, MatButton, MatChip, MatMiniFabButton],
  templateUrl: './question-view.component.html',
  styleUrl: './question-view.component.scss'
})
export class QuestionViewComponent implements OnInit{
  questionId: string ='';
  questionData: any = {};
  authorData: any = {};
  answers: any[] = [];


  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.questionId = params.get('question_id') || '';
      if (this.questionId) {
        this.fetchQuestionData();
        this.fetchAnswers();
      }
    });
  }

  fetchQuestionData(): void {
    fetch(`http://localhost:8080/questions/${this.questionId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        this.questionData = data;
        this.fetchAuthorData();
      })
      .catch(error => {
        console.error('Error fetching question data:', error);
      });
  }

  fetchAuthorData(): void {
    fetch(`http://localhost:8080/users/${this.questionData.user}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        this.authorData = data;
      })
      .catch(error => {
        console.error('Error fetching author data:', error);
      });
  }

  fetchAnswers(): void {
    fetch(`http://localhost:8080/answers/getAnswersPerQuestion/${this.questionId}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        this.answers = data;
      })
      .catch(error => {
        console.error('Error fetching answers:', error);
      });
  }

  incrementScore = () => {
    this.questionData.score++;
  }

  decrementScore = () => {
    this.questionData.score--;
  }

  createdByCrtUser = (answer: any) => {
    return answer.user.email === localStorage.getItem('activeUser');
  }

  deleteAnswer = (answerId: any) => {
    fetch(`http://localhost:8080/answers/delete/${answerId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then((response) => {
      if (response.ok) {
        //this.refresh.emit();
      } else {
        console.log('Error deleting question');
      }
    });
  }
}
