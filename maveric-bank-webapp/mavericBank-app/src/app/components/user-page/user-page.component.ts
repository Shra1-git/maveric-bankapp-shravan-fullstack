import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../modals/user';
import { UserService } from '../../services/user.service';
import { Account } from '../../modals/account';
import { AccountService } from '../../services/account.service';
import { BalanceService } from '../../services/balance.service';
import { Observable, Subject, interval, switchMap, takeUntil } from 'rxjs';
import { Balance } from '../../modals/balance';
import { Transaction } from '../../modals/transaction';
import { TransactionService } from '../../services/transaction.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BalanceComponent } from '../balance/balance.component';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrl: './user-page.component.css'
})
export class UserPageComponent {
  userId: string ='';
  user!: User;
  accounts: Account[] = [];
  balanceAmount: number = 0 ;
  // transactions!: Transaction[] ;
  displayedColumns: string[] = ['type', 'referenceNumber', 'date', 'amount'];
  transactionForms: { [key: string]: FormGroup } = {};
  showTransactionForm: { [key: string]: boolean } = {};
  showBalanceForm: boolean = false;
  balanceForm!: FormGroup;
  myaccountid!: string;
  transactions: { [accountId: string]: Transaction[] } = {};
  mybalanceAmounts: { [accountId: string]: number } = {};



  logout(): void {
    
    this.router.navigate(['/login']);
  }

  constructor(private route: ActivatedRoute,private router: Router, private userService: UserService, private accountService:AccountService, private balanceService: BalanceService,
    private transactionService: TransactionService,private formBuilder: FormBuilder) { }

  ngOnInit(): void {


    this.route.params.subscribe((params) => {
      
      this.userId= params['id'];
  });


    this.userService.getUserById(this.userId).subscribe(
      (user: User) => {
        this.user = user;
      },
      (error) => {
        console.error('Error fetching user details:', error);
      }
    );

    this.accountService.getAccountsByCustomerId(this.userId).subscribe(accounts => {
      this.accounts = accounts;

      accounts.forEach(maccount => {
        this.fetchTransactions(maccount.id);
        this.getmyBalances(maccount.id);
        this.initializeTransactionForms(maccount.id);
      });
    });
  

  // getBalancesAndTransactions(index: number = 0) {
  //   if (index >= this.accounts.length) {
  //     return; // Exit the function if all accounts have been processed
  //   }
  
  //   const myaccountiId: string = this.accounts[index].id;
  
  //   this.balanceService.getBalanceByAccountId(myaccountiId)
  //     .subscribe((balance: Balance) => {
  //       this.balanceAmounts[myaccountiId] = balance.amount;
  //       console.log(balance.amount);
  //     });
  
  //   this.transactionService.getTransactionsByAccountId(myaccountiId)
  //     .subscribe((transactions: Transaction[]) => {
  //       this.transactions[myaccountiId] = transactions;
  
  //       // Move to the next account
  //       this.getBalancesAndTransactions(index + 1);
  //     });
  // }
  }

  // getBalances() {
  //   // this.balanceAmount = 0; // Reset the balanceAmount
    
  //   this.accounts.forEach(acc => {
  //     if (acc && acc.id) {
  //       this.balanceService.getBalanceByAccountId(acc.id)
  //         .subscribe((balance: Balance) => {
  //           this.mybalanceAmounts[acc.id] = balance.amount;
  //           console.log(this.mybalanceAmounts[acc.id]);
  //         });
  //     }
  //   });
  // }
  // toggleBalanceForm(): void {
  //   this.showBalanceForm = !this.showBalanceForm;
  // }

  // // addBalance(accountId: string): void {
  // //   this.myaccountid = accountId;
  // //   if (this.balanceForm.invalid) {
  // //     return;
  // //   }

    

  //   const amount = this.balanceForm.value.amount;
  //   const currency = this.balanceForm.value.currency;
    
  //   const balanceData: Balance= {
  //     id: '1',
  //     accountId: accountId,
  //     amount: amount,
  //     currency: currency,
  //     createdAt: new Date(),
  //   updatedAt: new Date()
  //   };

    // this.balanceService.createBalance(balanceData).subscribe(
    //   (response) => {
       
    //     console.log('Balance created successfully');
        
    //     this.balanceForm.reset();
       
    //     this.showBalanceForm = false;
    //   },
    //   (error) => {
        
    //     console.error('Error creating balance:', error);
    //   }
    // );
  // }


  addAccount(): void {
    
    this.router.navigate(['/addaccount', this.userId]); 
  }

  onSubmit(accountId: string) {
    const transactionForm = this.transactionForms[accountId];
    const type = transactionForm.value.type;
    let amount = +transactionForm.value.amount; 

    
    if (type === 'Withdrawal') {
      amount = -amount;
    }

    
    const transactionData: Transaction = {
      id: '', 
      type: type,
      amount: amount,
      accountId: accountId,
      createdAt: new Date() 
    };

    
    this.transactionService.addTransaction(transactionData).subscribe(
      (transaction: Transaction) => {
        console.log('Transaction added successfully:', transaction);
        this.fetchTransactions(accountId);
        this.showTransactionForm[accountId] = false;
            transactionForm.reset();
        this.balanceService.updateBalanceAmount(accountId, amount).subscribe(
          (balance) => {
            console.log('Balance updated successfully:', balance);
            this.getmyBalances(accountId);
            this.showTransactionForm[accountId] = false;
            transactionForm.reset();
          },
          (error) => {
            console.error('Error updating balance:', error);
           
          }
        );
      },
      (error) => {
        console.error('Error adding transaction:', error);
      }
    );
  }

  initializeTransactionForms(accountId: string) {
    this.transactionForms[accountId] = this.formBuilder.group({
      type: ['', Validators.required],
      amount: ['', Validators.required],
      accountId: [accountId]
    });
  }

  toggleTransactionForm(accountId: string) {
    this.showTransactionForm[accountId] = !this.showTransactionForm[accountId];
  }

  // getBalance(accountId: string): void {
  //   this.balanceService.getBalanceByAccountId(accountId).subscribe(balance => {
  //     this.balanceAmount = balance.amount;
  //     // Now you can use this.balanceAmount in your transaction logic
  //   });
  // }

  fetchTransactions(accountId: string):void{
    
        this.transactionService.getTransactionsByAccountId(accountId).subscribe(
          (thetransactions) => {
           
            this.transactions[accountId] = thetransactions;
            console.log(this.transactions[accountId]);
            
            
          },
        );
      }

      getmyBalances(accountId: string):void {
        this.balanceService.getBalanceByAccountId(accountId).subscribe(
          (balance) => {
            this.mybalanceAmounts[accountId] = balance.amount;
            
            console.log("see" + this.mybalanceAmounts[accountId]);
            
          }
        )
      }
  
  // fetchTransactions(accountId: string): void { 
    
  //   this.transactionService.getTransactionsByAccountId(accountId).subscribe((thetransactions) => {
  //     console.log(this.transactions);
  //     this.transactions = thetransactions;
      
  //   });
  // }

  // fetchTransactions(accountId: string): Observable<Transaction[]> {
  //   return this.transactionService.getTransactionsByAccountId(accountId);
  // }

//   ngOnDestroy(): void {
//   this.unsubscribe$.next();
//   this.unsubscribe$.complete();
// }

  
}
