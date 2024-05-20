import {Component} from '@angular/core';
import {QuestionCardComponent} from "../../components/question-card/question-card.component";
import {NgForOf} from "@angular/common";
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-questions',
  standalone: true,
  imports: [
    QuestionCardComponent,
    NgForOf,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    MatIconModule,
    MatSelectModule
  ],
  templateUrl: './questions.component.html',
  styleUrl: './questions.component.scss'
})
export class QuestionsComponent {
  questions: any;
  tags: any;
  selectedTag: any;

  constructor() {
  }

  ngOnInit() {
    this.fetchQuestions();
    this.fetchTags();
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

  fetchTags(): any {
    fetch('http://localhost:8080/tags/all')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch tags');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        this.tags = data;
        return data;
      })
      .catch(error => {
        console.error('Error fetching tags:', error);
        throw error;
      });
  }

  filterQuestions(event: any) {
    if (!event.target.value) {
      this.fetchQuestions();
      return;
    }
    const filterValue = event.target.value;
    this.questions = this.questions.filter((question: any) => {
      return question.title.toLowerCase().includes(filterValue.toLowerCase());
    });
  }

  applyTag(tagId: any) {
    if (tagId === 'all') {
      this.fetchQuestions();
      return;
    } else
    fetch(`http://localhost:8080/questions/tag/${tagId.id}`)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch questions by tag');
        }
        return response.json();
      })
      .then(data => {
        console.log(data);
        this.questions = data;
        return data;
      })
      .catch(error => {
        console.error('Error fetching questions by tag:', error);
        throw error;
      });
  }
}
