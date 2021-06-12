import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const post_url = environment.post_url;
const story_url = environment.story_url;

@Injectable({
  providedIn: 'root'
})
export class PostStoryService {

  constructor(private http: HttpClient) { }

  public createPostStory(body: FormData): Observable<any>{
    return this.http.post(post_url+'/create',body);
  }
}
