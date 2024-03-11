import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddTransactionComponent } from './components/add-transaction/add-transaction.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AddAccountComponent } from './components/add-account/add-account.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { UserPageComponent } from './components/user-page/user-page.component';


const routes: Routes = [
  // { path: 'addtransaction/:userId', component: AddTransactionComponent },
  { path: 'addtransaction', component: AddTransactionComponent },
  { path: 'adduser', component: AddUserComponent },
  { path: 'user/:id', component: UserPageComponent },
  { path: 'user', component: UserPageComponent  },
  // { path: 'addbalance', component: BalanceComponent },
  { path: 'addaccount/:userId', component: AddAccountComponent },
  { path: 'addaccount', component: AddAccountComponent },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', component: NotFoundComponent }, // Wildcard route to display error message
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
