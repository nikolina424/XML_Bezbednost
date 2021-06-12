import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const image_url = environment.image_url;

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  fileName = '';
  
  constructor(private http: HttpClient) { }

  public uploadImage(image: File): Observable<any> {
    const formData = new FormData();

    formData.append('image', image);
    console.log(formData);
    return this.http.post(image_url+'/upload', image);
  }

  /*onFileSelected(event) {

    const file:File = event.target.files[0];

    if (file) {

        this.fileName = file.name;

        const formData = new FormData();

        formData.append("thumbnail", file);

        const upload$ = this.http.post("/api/thumbnail-upload", formData);

        upload$.subscribe();
    }
}*/
}
