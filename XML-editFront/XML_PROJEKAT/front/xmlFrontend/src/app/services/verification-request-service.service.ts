import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const verificationRequest_url = environment.verificationRequest_url;

@Injectable({
  providedIn: 'root'
})
export class VerificationRequestServiceService {

  constructor(private http : HttpClient) { }

  public newVerificationRequest(body: any) : Observable<any>{
    return this.http.post(verificationRequest_url+'/create', body);
  }

  public viewVerificationRequests() : Observable<any>{
    return this.http.get(verificationRequest_url+'/getAll');
  }
}
