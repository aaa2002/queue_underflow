import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatCardModule} from '@angular/material/card';
import {NgIf} from "@angular/common";
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';


@Component({
  selector: 'app-question-view',
  standalone: true,
  imports: [MatCardModule, NgIf, MatIconModule, MatProgressSpinnerModule],
  templateUrl: './question-view.component.html',
  styleUrl: './question-view.component.scss'
})
export class QuestionViewComponent {
  questionId: string = '';
  questionData: any = {};

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.questionId = params.get('question_id') || '';
      if (this.questionId) {
        fetch(`http://localhost:8080/questions/${this.questionId}`)
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then(data => {
            this.questionData = data;
          })
          .catch(error => {
            console.error('Error fetching question data:', error);
          });
      }
    });
  }
}
