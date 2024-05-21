import {Component, inject} from '@angular/core';
import {MatCard} from "@angular/material/card";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {FormControl, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NotificationService} from "../../service/notificationService";
import {
  MatChipEditedEvent,
  MatChipGrid,
  MatChipInput,
  MatChipInputEvent,
  MatChipRemove,
  MatChipRow
} from "@angular/material/chips";
import {MatIcon} from "@angular/material/icon";
import {NgForOf} from "@angular/common";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {LiveAnnouncer} from "@angular/cdk/a11y";

@Component({
  selector: 'app-add-question-view',
  standalone: true,
  imports: [MatCard, MatFormFieldModule, MatInputModule, MatButtonModule, FormsModule, MatChipGrid, MatChipInput, MatChipRemove, MatChipRow, MatIcon, NgForOf, ReactiveFormsModule],
  templateUrl: './add-question-view.component.html',
  styleUrl: './add-question-view.component.scss'
})

export class AddQuestionViewComponent {
  formData = {
    questionDTO: {
      title: "",
      text: ""
    },
    userEmail: "",
    tags: [] as any
  }
  tagsCtrl = new FormControl();

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

    console.log(this.formData);
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
        this.formData.tags = [];
      } else {
        this.notificationService.show("Failed to add question", "OK");
      }
    });
  }

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  fruits: any = [];

  announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    const exists = this.formData.tags.some((tag: any) => tag.name === value);

    if (value && !exists) {
      this.formData.tags.push({id: -1, name: value});
    }

    event.chipInput!.clear();
  }


  remove(fruit: any): void {
    const index = this.formData.tags.indexOf(fruit);

    if (index >= 0) {

      this.formData.tags.splice(index, 1);

      this.announcer.announce(`Removed ${fruit}`);
    }
  }

  edit(tag: any, event: MatChipEditedEvent) {
    const value = event.value.trim();

    if (!value) {
      this.remove(tag);
      return;
    }

    const index = this.formData.tags.indexOf(tag);
    if (index >= 0) {
      this.formData.tags[index] = {id: -1, name: value};
    }
  }

  protected readonly FormData = FormData;
}
