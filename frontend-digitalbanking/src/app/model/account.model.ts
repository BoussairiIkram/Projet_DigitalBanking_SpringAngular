export interface AccountOperation {
  id:            number;
  dateOperation: Date;
  amount:        number;
  opType:        string;
  description:   string;
  idBankAccount: string;
}

export interface OperationRequest {
  amount:        number;
  description:   string;
  idBankAccountDestination: string;
  idBankAccountSource: string;
}

export interface BankAccount {
  id:        string;
  status: string;
  balance:   number;
  currency: string;
  interestRate: string;
  overDraft: string;
  createdAt: Date;
  type: string;
}





