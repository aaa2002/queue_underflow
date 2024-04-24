import {Component, Input} from '@angular/core';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatChip} from "@angular/material/chips";
import {Router} from "@angular/router";
import { format } from 'date-fns';

@Component({
  selector: 'app-question-card',
  standalone: true,
  imports: [
    MatCardContent,
    MatCard,
    MatCardTitle,
    MatIcon,
    MatCardActions, MatButtonModule, MatDividerModule, MatIconModule, MatChip
  ],
  templateUrl: './question-card.component.html',
  styleUrl: './question-card.component.scss'
})
export class QuestionCardComponent {
  @Input()
  questionObject: any;

  constructor( private router: Router ) {

  }

  formattedDate = (date:any) => format(date, 'MM/dd/yyyy');

  ngOnInit() {
  }

  goToQuestion = () => {
    this.router.navigate(['/main/question', this.questionObject.id]);
  }

  incrementScore = () => {
    this.questionObject.score++;
  }

  decrementScore = () => {
    this.questionObject.score--;
  }

  protected readonly console = console;
}
