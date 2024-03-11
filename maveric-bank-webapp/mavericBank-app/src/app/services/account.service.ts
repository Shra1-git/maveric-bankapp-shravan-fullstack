import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../modals/account';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development'; 

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  

  constructor(private http: HttpClient) { }

  createAccount(account: Account): Observable<Account> {
    return this.http.post<Account>(`${environment.accountServiceBaseUrl}/account`, account);
  }

  getAccountById(accountId: string): Observable<Account> {
    return this.http.get<Account>(`${environment.accountServiceBaseUrl}/account/${accountId}`);
  }

  getAccountsByCustomerId(customerId: string): Observable<Account[]> {
    return this.http.get<Account[]>(`${environment.accountServiceBaseUrl}/account/getaccounts/${customerId}`);
  }

  deleteAccountById(accountId: string): Observable<void> {
    return this.http.delete<void>(`${environment.accountServiceBaseUrl}/account/${accountId}`);
  }

  getBalanceByAccountId(accountId: string): Observable<any> { // Assuming Balance type is not defined
    return this.http.get<any>(`${environment.accountServiceBaseUrl}/account/${accountId}/balance`);
  }
}
