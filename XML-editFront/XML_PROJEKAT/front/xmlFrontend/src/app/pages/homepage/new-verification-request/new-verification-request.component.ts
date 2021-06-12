import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VerificationRequestServiceService } from 'src/app/services/verification-request-service.service';

@Component({
  selector: 'app-new-verification-request',
  templateUrl: './new-verification-request.component.html',
  styleUrls: ['./new-verification-request.component.css']
})
export class NewVerificationRequestComponent implements OnInit {
  validateForm!: FormGroup; 
  image: any;
  file!: File;
  surname!: string;
  name!: string;
  category!: string;


  constructor(private fb: FormBuilder, private verificationRequestService : VerificationRequestServiceService) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      surname: [null,[Validators.required]],
      name: [null,[Validators.required]],
      file: [null,[Validators.required]],
      category: [null,[Validators.required]]
    });
  }

  fileChange(event: any) {
    // Instantiate an object to read the file content
    let reader = new FileReader();
    // when the load event is fired and the file not empty
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.file = event.target.files[0];
    }
  }


  submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    this.category = this.validateForm.get('category')?.value;
    this.surname = this.validateForm.value.surname;
    this.name = this.validateForm.value.name;

    // Instantiate a FormData to store form fields and encode the file
    let body = new FormData();
    // Add file content to prepare the request
    body.append("file", this.file);
    body.append("surname", this.surname);
    body.append("name", this.name);
    body.append("category", this.category);
    console.log(this.validateForm.valid);
    // Launch post request
    if(this.validateForm.valid){
      this.verificationRequestService.newVerificationRequest(body)
      .subscribe(
        // Admire results
        (data) => {console.log(data)},
        // Or errors :-(
        error => console.log(error),
        // tell us if it's finished
        () => { console.log("completed") }
      );
      
    }

  }

}
