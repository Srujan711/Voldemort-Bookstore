// Contains all the custom types we want to use for our application
export interface BookItem {
    bookId: number;
    title: string;
    author: string;
    price: number;
    isPublic: boolean;
}

export interface CategoryItem {
    categoryId: number;
    name: string;
}

export interface CustomerForm {
    name: string;
    address: string;
    phone: string;
    email: string;
    ccNumber: string;
    ccExpiryMonth: number;
    ccExpiryYear: number;
}

export interface ServerErrorResponse {
    reason: string;
    message: string;
    fieldName: string;
    error: boolean;
}
export interface Order {
    orderId: number;
    amount: number;
    dateCreated: number;
    confirmationNumber: number;
    customerId: number;
  }
  
  export interface LineItem {
    bookId: number;
    orderId: number;
    quantity: number;
  }
  export interface customer {
    customerName: string;
    address: string;
    phone: string;
    email: string;
    ccNumber: string;
    ccExpDate: number;
  }
  
  export interface OrderDetails {
    order: Order;
    customer: customer;
    books: BookItem[];
    lineItems: LineItem[];
  }