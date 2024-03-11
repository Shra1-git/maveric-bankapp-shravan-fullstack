import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Balance } from '../modals/balance';
import { environment } from '../../environments/environment.development'; 


@Injectable({
  providedIn: 'root'
})
export class BalanceService {

  constructor(private http: HttpClient) { }

  getBalanceByAccountId(accountId: string): Observable<Balance> {
    return this.http.get<Balance>(`${environment.balanceServiceBaseUrl}/balance/${accountId}`);
  }

  createBalance(balance: Balance): Observable<Balance> {
    return this.http.post<Balance>(`${environment.balanceServiceBaseUrl}/balance`, balance);
  }

  updateBalanceAmount(accountId: string, amountToAdd: number): Observable<Balance> {
    const params = { accountId, amountToAdd: amountToAdd.toString() };
    return this.http.put<Balance>(`${environment.balanceServiceBaseUrl}/balance/update`, null, { params });
  }
}
