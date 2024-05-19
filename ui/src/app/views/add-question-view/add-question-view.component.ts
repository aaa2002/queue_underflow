import {Component} from '@angular/core';
import {MatCard} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from "@angular/forms";
import {NotificationService} from "../../service/notificationService";

@Component({
  selector: 'app-add-question-view',
  standalone: true,
  imports: [MatCard, MatFormFieldModule, MatInputModule, MatButtonModule, FormsModule],
  templateUrl: './add-question-view.component.html',
  styleUrl: './add-question-view.component.scss'
})

export class AddQuestionViewComponent {
  formData = {
    questionDTO: {
      title: "",
      text: ""
    },
    userEmail: ""
  }

  constructor(private notificationService: NotificationService) {
  }

  ngOnInit(): void {
  }

  getActiveUser() {
    if (localStorage.getItem('activeUser') == null) {
      this.notificationService.show("Please login to ask a question", "OK");
    } else {
      this.formData.userEmail = localStorage.getItem('activeUser') ?? "";
    }
  }

  addQuestion() {
    this.getActiveUser();

    fetch('http://localhost:8080/questions/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(this.formData)
    }).then(response => {
      if (response.status == 200) {
        this.notificationService.show("Question added successfully", "OK");
        this.formData.questionDTO.title = "";
        this.formData.questionDTO.text = "";
        this.formData.userEmail = "";
      } else {
        this.notificationService.show("Failed to add question", "OK");
      }
    });
  }
}
