package org.csu.mypetstore_spring.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.csu.mypetstore_spring.domain.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategoryList();

    Category getCategory(String categoryID);
}
