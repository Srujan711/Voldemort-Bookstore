package business.order;

import api.ApiException;
import business.book.Book;
import business.book.BookDao;
import business.cart.ShoppingCart;
import business.customer.CustomerForm;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DefaultOrderService implements OrderService {

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public OrderDetails getOrderDetails(long orderId) {
		// NOTE: THIS METHOD PROVIDED NEXT PROJECT
		return null;
	}

	@Override
    public long placeOrder(CustomerForm customerForm, ShoppingCart cart) {

		validateCustomer(customerForm);
		validateCart(cart);

		// NOTE: MORE CODE PROVIDED NEXT PROJECT

		return -1;
	}


	private void validateCustomer(CustomerForm customerForm) {
		String name = customerForm.getName();
		String phone = customerForm.getPhone();
		String address = customerForm.getAddress();
		String email = customerForm.getEmail();
		String ccNumber = customerForm.getCcNumber();

		if (name == null || name.isEmpty() || name.length() < 4 || name.length() > 45) {
			throw new ApiException.ValidationFailure("name", "Invalid name field");
		}

		if (address == null || address.isEmpty() || address.length() < 4 || address.length() > 45) {
			throw new ApiException.ValidationFailure("address", "Invalid address field");
		}

		// TODO: Validation checks for address, phone, email, ccNumber

		if (phone == null || phone.isEmpty()) {
			throw new ApiException.ValidationFailure("phone", "Invalid phone field");
		}

		String phoneDigits = phone.replaceAll("\\D", "");

		if (phoneDigits.length() != 10) {
			throw new ApiException.ValidationFailure("phone", "Invalid phone field");
		}

		if (email == null || email.isEmpty() || !isValidEmail(email)) {
			throw new ApiException.ValidationFailure("email", "Invalid email field");
		}

		if (ccNumber == null || ccNumber.isEmpty() || !isValidCreditCardNumber(ccNumber)) {
			throw new ApiException.ValidationFailure("ccNumber", "Invalid credit card number");
		}

		if (expiryDateIsInvalid(customerForm.getCcExpiryMonth(), customerForm.getCcExpiryYear())) {
			throw new ApiException.ValidationFailure("Please enter a valid expiration date.");
		}
	}

	private boolean isValidEmail(String email) {
		// Implement email validation logic here
		return email.matches("^[A-Za-z0-9+_.-]+@(.+)$") && !email.endsWith(".");
	}

	private boolean isValidCreditCardNumber(String ccNumber) {
		String cleanedCcNumber = ccNumber.replaceAll("[\\s-]", "");
		int length = cleanedCcNumber.length();
		return length >= 14 && length <= 16;
	}

	private boolean expiryDateIsInvalid(String ccExpiryMonth, String ccExpiryYear) {
		try {
			int providedMonth = Integer.parseInt(ccExpiryMonth);
			int providedYear = Integer.parseInt(ccExpiryYear);
			YearMonth currentYearMonth = YearMonth.now();
			YearMonth providedYearMonth = YearMonth.of(providedYear, providedMonth);
			return providedYearMonth.isBefore(currentYearMonth);
		} catch (NumberFormatException | DateTimeParseException e) {
			return true;
		}
	}

	private void validateCart(ShoppingCart cart) {

		if (cart.getItems().size() <= 0) {
			throw new ApiException.ValidationFailure("Cart is empty.");
		}

		cart.getItems().forEach(item-> {
			if (item.getQuantity() < 0 || item.getQuantity() > 99) {
				throw new ApiException.ValidationFailure("Invalid quantity");
			}
			Book databaseBook = bookDao.findByBookId(item.getBookId());
			// TODO: complete the required validations
			if (databaseBook == null) {
				throw new ApiException.ValidationFailure("Book not found in the database for item with ID " + item.getBookId());
			}

			if (!Double.valueOf(item.getPrice()).equals(Double.valueOf(databaseBook.getPrice()))) {
				throw new ApiException.ValidationFailure("Price mismatch for item with ID " + item.getBookId());
			}


			if (!item.getCategory().equals(databaseBook.getCategory())) {
				throw new ApiException.ValidationFailure("Category mismatch for item with ID " + item.getBookId());
			}
		});
	}

}
