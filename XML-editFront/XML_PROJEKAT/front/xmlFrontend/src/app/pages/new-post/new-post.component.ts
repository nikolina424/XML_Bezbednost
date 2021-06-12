import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzUploadChangeParam } from 'ng-zorro-antd/upload';
import { ImageService } from 'src/app/services/image.service';
import { PostStoryService } from 'src/app/services/post-story.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  validateForm!: FormGroup; 
  image: any;
  file!: File;
  location!: string;
  caption!: string;

  constructor(private postStory: PostStoryService ,private fb: FormBuilder, private http: HttpClient) { }

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

    this.caption = this.validateForm.value.caption;
    this.location = this.validateForm.value.location;

    // Instantiate a FormData to store form fields and encode the file
    let body = new FormData();
    // Add file content to prepare the request
    body.append("file", this.file);
    console.log(this.file);
    body.append("location", this.location);
    body.append("caption", this.caption);
    body.append("userInfoId", "1");
    // Launch post request
    if(this.validateForm.valid){
      this.postStory.createPostStory(body)
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

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      location: [null,[Validators.required]],
      caption: [null,[Validators.required]],
      file: [null,[Validators.required]]
    });
  }

}
