package ru.vilgor.businkabackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import ru.vilgor.businkabackend.jsonview.CategoryView;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @JsonView(CategoryView.Public.class)
    private Integer id;

    @Column(name = "category_name")
    @JsonView(CategoryView.Public.class)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonView(CategoryView.WithParent.class)
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.REMOVE})
    @JsonView(CategoryView.WithChildren.class)
    private List<Category> childCategoryList;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> productList;

    public Category() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getChildCategoryList() {
        return childCategoryList;
    }

    public void setChildCategoryList(List<Category> childCategoryList) {
        this.childCategoryList = childCategoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
