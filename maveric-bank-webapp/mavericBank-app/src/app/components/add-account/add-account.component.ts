import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../../services/account.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Account } from '../../modals/account';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrl: './add-account.component.css'
})
export class AddAccountComponent {
  accountForm!: FormGroup;

  myaccount!: Account;
  userId: string | null= '';
  

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private router: Router,
   
   
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('userId');
    this.accountForm = this.fb.group({
      type: ['', Validators.required],
      customerId: [ this.userId , Validators.required]
    });

    // this.accountService.getAccountById().subscribe((data)=>{
    //   this.myaccount = data;
    // }

    // );
  }

  onSubmit() {
    if (this.accountForm.valid) {
      const accountData = this.accountForm.value;
      
     
      this.accountService.createAccount(accountData).subscribe(
        (response) => {
          this.snackBar.open('Account created successfully', 'Close', {
            duration: 3000
          });
          this.accountForm.reset();
          this.router.navigate(['/user', this.userId]); 
        },
        (error) => {
          console.error('Error saving account:', error);
          this.snackBar.open('Error saving account. Please try again.', 'Close', {
            duration: 3000
          });
        }
      );
    }
  }
}

