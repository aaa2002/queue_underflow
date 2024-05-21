import { Component, Inject, OnInit, inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogClose } from '@angular/material/dialog';
import { MatButtonModule } from "@angular/material/button";
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { MatCard } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from '@angular/material/input';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipEditedEvent, MatChipInputEvent, MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Observable, of } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-edit-question-dialog',
  standalone: true,
  imports: [
    MatDialogClose, MatButtonModule, FormsModule, MatButtonModule, MatCard, MatFormFieldModule, MatInputModule, MatChipsModule, MatIconModule, ReactiveFormsModule, NgForOf
  ],
  templateUrl: './edit-question-dialog.component.html',
  styleUrls: ['./edit-question-dialog.component.scss']
})
export class EditQuestionDialogComponent implements OnInit {
  formData: any = {};
  tagsCtrl = new FormControl();

  constructor(
    public dialogRef: MatDialogRef<EditQuestionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder
  ) {
  }

  ngOnInit() {
    this.formData = this.data;
    this.fruits = this.data.question.tags.map((tag: any) => ({ name: tag.name }));

    this.tagsCtrl.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(value => this.fetchTags(value))
    ).subscribe(tags => {
      console.log('Fetched tags:', tags);
      // Handle the fetched tags as needed
    });
  }


  fetchTags(query: string): any {
    return fetch('http://localhost:8080/tags/all')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch tags');
        }
        return response.json();
      })
      .then(data => {
        console.log('Fetched tags:', data);
        return of(data);
      })
      .catch(error => {
        console.error('Error fetching tags:', error);
        return of([]);
      });
  }

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  fruits: any = [];

  announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Check if the value already exists
    const exists = this.formData.question.tags.some((tag: any) => tag.name === value);

    // Add our fruit if it doesn't already exist
    if (value && !exists) {
      this.formData.question.tags.push({ id: -1, name: value });
    }

    // Clear the input value
    event.chipInput!.clear();
  }


  remove(fruit: any): void {
    const index = this.formData.question.tags.indexOf(fruit);

    if (index >= 0) {

      this.formData.question.tags.splice(index, 1);

      this.announcer.announce(`Removed ${fruit}`);
    }
  }

  edit(fruit: any, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(fruit);
      return;
    }

    // Edit existing fruit
    const index = this.fruits.indexOf(fruit);
    if (index >= 0) {
      this.fruits[index].name = value;
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    console.log('Form data:', this.formData);
    fetch('http://localhost:8080/questions/update', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(this.formData.question)
    }).then(response => {
      if (response.status === 200) {
        console.log('Question updated successfully');
      } else {
        console.error('Failed to update question');
      }
    });

    // console.log('Form data:', this.formData)


    this.dialogRef.close(this.formData);
  }
}
