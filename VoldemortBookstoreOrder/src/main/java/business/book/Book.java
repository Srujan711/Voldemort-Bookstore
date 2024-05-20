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
				   long categoryId) {
	public Integer getPrice() {
		return price;
	}

	public Object getCategory() {
		return categoryId;
	}

	@Override
	public long bookId() {
		return bookId;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public String author() {
		return author;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public int price() {
		return price;
	}

	@Override
	public int rating() {
		return rating;
	}

	@Override
	public boolean isPublic() {
		return isPublic;
	}

	@Override
	public boolean isFeatured() {
		return isFeatured;
	}

	@Override
	public long categoryId() {
		return categoryId;
	}
}
