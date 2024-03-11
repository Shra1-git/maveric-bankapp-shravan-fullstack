
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Validators } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../modals/user';
import { Balance } from '../../modals/balance';
import { BalanceService } from '../../services/balance.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent implements OnInit {
  userForm!: FormGroup;
  today: Date = new Date();
  myuser!: User;
  mybalance!: Balance;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private balanceService: BalanceService,
    private snackBar: MatSnackBar,
    private router: Router 
  ) { }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      middleName: [''],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(15)]],
      address: ['', Validators.required],
      dateOfBirth: ['', [Validators.required,this.minimumAgeValidator(18)]],
      password: ['', Validators.required],
      role: ['User', Validators.required]
    });

    this.userService.getUserByEmail('mate@example.com').subscribe((data) => {
      this.myuser = data
    })

    this.balanceService.getBalanceByAccountId('124').subscribe((data) => {
      this.mybalance = data
    })
  }

  onSubmit() {
    if (this.userForm.valid) {
      const userData: User = this.userForm.value;
      this.userService.createUser(userData).subscribe(
        () => {
          this.snackBar.open('User saved successfully', 'Close', {
            duration: 3000
          });
          this.userForm.reset();
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Error saving user:', error);
          this.snackBar.open('Error saving user. Please try again.', 'Close', {
            duration: 3000
          });
        }
      );
    }
  }

  minimumAgeValidator(minimumAge: number) {
    return (control: FormControl) => {
      const currentDate = new Date();
      const selectedDate = new Date(control.value);
      const age = currentDate.getFullYear() - selectedDate.getFullYear();
      if (age < minimumAge) {
        return { minimumAge: true };
      }
      return null;
    };
  }
}
