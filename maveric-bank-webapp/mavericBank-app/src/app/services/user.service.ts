import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../modals/user';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${environment.userServiceBaseUrl}/user`, user);
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${environment.userServiceBaseUrl}/user/${email}`);
  }

  getUserById(userId: string): Observable<User> {
    return this.http.get<User>(`${environment.userServiceBaseUrl}/userid/${userId}`);
  }
}
