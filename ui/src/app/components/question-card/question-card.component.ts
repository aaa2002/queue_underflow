import {Component, Input} from '@angular/core';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatChip} from "@angular/material/chips";

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

  constructor() {

  }

  ngOnInit() {
    console.log(this.questionObject);
  }
}
