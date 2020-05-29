import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, ErrorStateMatcher} from '@angular/material';
import { Field } from '../../model/field';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { FormGroup } from '@angular/forms/src/model';
import { FormBuilder, FormControl, FormGroupDirective, NgForm } from '@angular/forms';
import { DynamicField } from '../../model/dynamic-form/dynamic-field';
import { AbstractClassPart } from '@angular/compiler/src/output/output_ast';

/**
 * @title Dialog Overview
 */
@Component({
  // tslint:disable-next-line:component-selector
  selector: 'app-question-modal-component',
  templateUrl: './question-modal.component.html',
  styleUrls: ['./question-modal.component.css'],
})

export class QuestionModalComponent  implements OnInit {
  form: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<QuestionModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.form = this.data.form;
    }

  ngOnInit(): void {
    
  }

  onReject(): void {
    if (this.data.action.onReject) {
      this.data.action.onReject();
    }
    this.dialogRef.close();
  }

  onSubmit(): void {
    this.data.action.onSubmit();
    this.dialogRef.close();
  }

}
