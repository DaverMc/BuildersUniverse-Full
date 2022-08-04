package buun.category;

import buun.BuildersUniverse;
import buun.lang.LangueKeys;
import buun.util.WorkResult;

import java.util.List;

public class CategoryManager {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MAX_PREFIX_LENGTH = 5;

    private final List<Category> categories;

    public CategoryManager(){
        this.categories = loadCategories();
        createCategory("default", "D");
    }

    private List<Category> loadCategories(){
        return null; //TODO SQL Command für das selecten aller Categories
    }

    public WorkResult<Category> createCategory(String name, String prefix){
        if(getCategory(name) == null) return new WorkResult<>(null, true, LangueKeys.CATEGORY_ALREADY_EXISTS, name);
        if(name == null || name.length() > MAX_NAME_LENGTH) return new WorkResult<>(null, true, LangueKeys.CATEGORY_INVALID_NAME, name);
        if(prefix == null || prefix.length() > MAX_PREFIX_LENGTH) return new WorkResult<>(null, true, LangueKeys.CATEGORY_INVALID_PREFIX, prefix);
        Category category = new BuunCategory(name, prefix);
        BuildersUniverse.getServiceManager().getSqlCommandService().enqueue(category, null, true); //TODO SQL Command für Category Insert
        categories.add(category);
        return new WorkResult<>(category, false, null);
    }

    public Category getCategory(String name){
        List<Category> filtered = categories.stream().filter(category -> category.getName().equalsIgnoreCase(name)).toList();
        return (filtered.isEmpty()) ? null : filtered.get(0);
    }
}
