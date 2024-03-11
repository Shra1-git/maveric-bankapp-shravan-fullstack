import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../modals/transaction';
// import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrl: './add-transaction.component.css'
})
export class AddTransactionComponent {

  transactionForm!: FormGroup;

  mytransaction!: Transaction[];

  constructor(
    private fb: FormBuilder,
    private transactionService: TransactionService,
    // private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.transactionForm = this.fb.group({
      type: ['', Validators.required],
      amount: [null, Validators.required],
      accountId: ['', Validators.required]
    });

    this.transactionService.getTransactionsByAccountId('123456789').subscribe((data) => {
this.mytransaction = data;
    })
  }

  onSubmit() {
    // if (this.transactionForm.valid) {
    //   const transactionData: Transaction = this.transactionForm.value;
    //   // Assuming TransactionService has a method to save transaction data
    //   this.transactionService.saveTransaction(transactionData).subscribe(
    //     () => {
    //       this.snackBar.open('Transaction saved successfully', 'Close', {
    //         duration: 3000
    //       });
    //       this.transactionForm.reset();
    //     },
    //     (error) => {
    //       console.error('Error saving transaction:', error);
    //       this.snackBar.open('Error saving transaction. Please try again.', 'Close', {
    //         duration: 3000
    //       });
    //     }
    //   );
    // }
  }
}
