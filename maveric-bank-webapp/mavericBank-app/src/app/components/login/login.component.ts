import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../modals/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email!: string;
  password!: string;

  constructor(private userService: UserService, private _snackBar: MatSnackBar,private router: Router) { }

  login() {
    if (!this.email) {
      this.openSnackBar('Email is required.', 'Error');
      return;
    }

    this.userService.getUserByEmail(this.email).subscribe(
      (user: User| null) => {
        if (user && user.password === this.password) {
          console.log('Login successful');
          this.router.navigate(['/user', user.id]);
        } else {
          this.openSnackBar('Invalid email or password. Please try.', 'Error');
         }
      },
      error => {
        console.error('Error:', error);
        this.openSnackBar('User not found. Please create a new account.', 'Error');
      }
    );
  }

  createUser() {
   
    this.router.navigate(['/adduser']); 
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }
}
