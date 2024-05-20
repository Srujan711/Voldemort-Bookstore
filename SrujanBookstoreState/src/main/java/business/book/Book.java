package business.book;

/*
 * TODO: Create a record constructor with fields corresponding to the fields in the
 * book table of your database.
 */

public record Book(long bookId,
				   String title,
				   String author,
				   String description,
				   int price,
				   int rating,
				   boolean isPublic,
				   boolean isFeatured,
				   long categoryId) {}
