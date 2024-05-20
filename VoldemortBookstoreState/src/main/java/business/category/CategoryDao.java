package business.category;

import java.util.List;

public interface CategoryDao {

    public List<Category> findAll();

    public Category findByCategoryId(long categoryId);

    public long findCategoryIdByName(String name);


    public Category findByName(String categoryName);
}
