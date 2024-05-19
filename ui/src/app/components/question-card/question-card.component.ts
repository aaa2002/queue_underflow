import {Component, Input, Output, EventEmitter} from '@angular/core';
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatButtonModule} from "@angular/material/button";
import {MatChip} from "@angular/material/chips";
import {Router} from "@angular/router";
import {format} from 'date-fns';
import {NgIf} from "@angular/common";
import {NotificationService} from "../../service/notificationService";

@Component({
  selector: 'app-question-card',
  standalone: true,
  imports: [
    MatCardContent,
    MatCard,
    MatCardTitle,
    MatIcon,
    MatCardActions, MatButtonModule, MatDividerModule, MatIconModule, MatChip, NgIf
  ],
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.scss']
})
export class QuestionCardComponent {
  @Input()
  questionObject: any;

  @Output()
  refresh: EventEmitter<void> = new EventEmitter<void>();

  constructor(private router: Router, private notificationService: NotificationService) {
  }

  formattedDate = (date: any) => format(date, 'MM/dd/yyyy');

  ngOnInit() {
  }

  goToQuestion = () => {
    this.router.navigate(['/main/question', this.questionObject.id]);
  }

  incrementScore = async () => {
    const response = await fetch(`http://localhost:8080/questions/like/${this.questionObject.id}/${localStorage.getItem('activeUser')}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    });

    if (response.ok) {
      const text = await response.text();
      console.log(text);
      this.notificationService.show('Question liked', '🥳');
      this.refresh.emit();
    } else if (response.status === 409) {
      const text = await response.text();
      console.log(text);
      this.notificationService.show(text, '🥶');
    } else {
      console.log('Error liking question');
    }
  }


  decrementScore = () => {
    fetch(`http://localhost:8080/questions/dislike/${this.questionObject.id}/${localStorage.getItem('activeUser')}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then(async (response) => {
      if (response.ok) {
        this.notificationService.show('Question disliked', '🥳');
        this.refresh.emit();
      } else if (response.status === 409) {
        const text = await response.text();
        console.log(text);
        this.notificationService.show(text, '🥶');
      } else {
        console.log('Error disliking question');
      }
    });
  }


  createdByCrtUser = () => {
    return this.questionObject.user.email === localStorage.getItem('activeUser');
  }

  deleteQuestion = () => {
    fetch(`http://localhost:8080/questions/delete/${this.questionObject.id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      }
    }).then((response) => {
      if (response.ok) {
        this.refresh.emit();
      } else {
        console.log('Error deleting question');
      }
    });
  }
}
