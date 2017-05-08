package ua.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ua.server.model.abstr.IEntity;

@Entity(name = "CATEGORY")
public class Category implements IEntity<Long>{

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name="CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_DESCRIPTION")
    private String categoryDescription;

    @Column(name="PARENT_CATEGORY_ID")
    private Long parentCategoryId;

    public Category() {}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!categoryId.equals(category.categoryId)) return false;
        if (!categoryName.equals(category.categoryName)) return false;
        if (categoryDescription != null ? !categoryDescription.equals(category.categoryDescription) : category.categoryDescription != null)
            return false;
        return !(parentCategoryId != null ? !parentCategoryId.equals(category.parentCategoryId) : category.parentCategoryId != null);

    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }

    @Override
    public Long getId() {
        return categoryId;
    }

    //@Override
    public void setId(Long id) {
        setCategoryId(id);
    }
}
