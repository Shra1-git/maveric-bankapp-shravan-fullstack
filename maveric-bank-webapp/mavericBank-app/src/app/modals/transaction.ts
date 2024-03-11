export interface Transaction {
    id: string;
    type: string;
    amount: number;
    accountId: string;
    createdAt: Date;
}