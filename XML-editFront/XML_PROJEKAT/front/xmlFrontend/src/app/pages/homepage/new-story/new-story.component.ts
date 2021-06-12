import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-new-story',
  templateUrl: './new-story.component.html',
  styleUrls: ['./new-story.component.css']
})
export class NewStoryComponent implements OnInit {

  form!: FormGroup;
  file!: File;
  
  constructor(private fb: FormBuilder, private http: HttpClient) {}
  
  ngOnInit() {
      this.createForm();
  }
  
  // Instantiate an AbstractControl from a user specified configuration
    createForm() {
      this.form = this.fb.group({
        file_upload: null
      });
    }
  
    // Check for changes in files inputs via a DOMString reprsenting the name of an event
    fileChange(event: any) {
      // Instantiate an object to read the file content
      let reader = new FileReader();
      // when the load event is fired and the file not empty
      if(event.target.files && event.target.files.length > 0) {
        // Fill file variable with the file content
        this.file = event.target.files[0];
      }
    }
  
    // Upload the file to the API
    upload() {
      // Instantiate a FormData to store form fields and encode the file
      let body = new FormData();
      // Add file content to prepare the request
      body.append("file", this.file);
      console.log(this.file)
      // Launch post request
      this.http.post('http://localhost:8120/image/upload', body)
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