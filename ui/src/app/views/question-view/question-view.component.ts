import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatCardModule} from '@angular/material/card';
import {NgForOf, NgIf} from "@angular/common";
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatButton, MatMiniFabButton} from "@angular/material/button";
import {MatChip} from "@angular/material/chips";
import {NotificationService} from "../../service/notificationService";
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-question-view',
  standalone: true,
  imports: [MatCardModule, NgIf, MatIconModule, MatProgressSpinnerModule, NgForOf, MatButton, MatChip, MatMiniFabButton, MatFormField, MatInput, ReactiveFormsModule, MatButtonModule, FormsModule],
  templateUrl: './question-view.component.html',
  styleUrl: './question-view.component.scss'
})
export class QuestionViewComponent implements OnInit {
  questionId: string = '';
  questionData: any = {};
  authorData: any = {};
  answers: any[] = [];
  formData: any = {
    answerDTO: {
      text: ""
    },
    userEmail: "",
    questionId: -1
  }


  constructor(private route: ActivatedRoute, private notificationService: NotificationService) {
  }

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
        this.answers = this.answers.sort((a, b) => b.score - a.score);
      })
      .catch(error => {
        console.error('Error fetching answers:', error);
      });
  }

  incrementScore = (answerId: any) => {
    fetch(`http://localhost:8080/answers/like/${answerId}/${localStorage.getItem('activeUser')}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then(async (response) => {
      if (response.ok) {
        this.notificationService.show('Answer liked', '🥳');
        this.fetchAnswers();
      } else if (response.status === 409) {
        const text = await response.text();
        console.log(text);
        this.notificationService.show(text, '🥶');
      } else {
        console.log('Error disliking answer');
      }
    });
  }

  decrementScore = (answerId: any) => {
    fetch(`http://localhost:8080/answers/dislike/${answerId}/${localStorage.getItem('activeUser')}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then(async (response) => {
      if (response.ok) {
        this.notificationService.show('Answer disliked', '🥳');
        this.fetchAnswers();
      } else if (response.status === 409) {
        const text = await response.text();
        console.log(text);
        this.notificationService.show(text, '🥶');
      } else {
        console.log('Error disliking answer');
      }
    });
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
        this.fetchAnswers();
      } else {
        console.log('Error deleting question');
      }
    });
  }

  getActiveUser() {
    if (localStorage.getItem('activeUser') == null) {
      this.notificationService.show("Please login to ask a question", "OK");
    } else {
      this.formData.userEmail = localStorage.getItem('activeUser') ?? "";
    }
  }

  addAnswer = (/*answerText: string*/) => {
    this.getActiveUser();
    this.formData.questionId = this.questionId;
    fetch('http://localhost:8080/answers/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(this.formData)
    }).then(response => {
      if (response.status == 200) {
        this.notificationService.show("Answer added successfully", "OK");
        this.formData.answerDTO.text = "";
        this.fetchAnswers();
      } else {
        this.notificationService.show("Failed to add answer", "OK");
      }
    });
  }
}
