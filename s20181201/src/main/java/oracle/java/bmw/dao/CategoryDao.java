package oracle.java.bmw.dao;

import java.util.List;


import oracle.java.bmw.model.Category;

public interface CategoryDao {

	int total();
	List<Category> CategoryListView(Category cate);
	Category datail(int categoryNo);
	int CategoryUpdatePro(Category cate);
	List<Category> CategoryParentNameList(Category cate);
	int CategoryDelete(int categoryNo);
	int CategoryWritePro(Category cate);
	Category detail(String name);
	List<Category> CategoryParentNameList2();
	int cateNameCheck(String name);
	int CateNameSearchTotal(Category cate);
	List<Category> CategoryListbySearch(Category cate);
	List<Category> headerCateView();
	Category headerCateSel(String cateSel);

}
