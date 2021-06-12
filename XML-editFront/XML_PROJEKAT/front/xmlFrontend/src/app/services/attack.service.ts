import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const attack_url = environment.attack_url;

@Injectable({
  providedIn: 'root'
})
export class AttackService {

  constructor(private http: HttpClient) { }

  public escaping(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/escape`, body);
  }

  public password(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/password`, body);
  }

  public name(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/name`, body);
  }

  public email(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/email`, body);
  }

  public string(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/organization`, body);
  }

  public phoneNumber(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/phoneNumber`, body);
  }

  public date(body: any) : Observable<any>{ 
    return this.http.post(attack_url + `/date`, body);
  }
}
