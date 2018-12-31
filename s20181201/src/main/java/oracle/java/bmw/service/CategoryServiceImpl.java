package oracle.java.bmw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.java.bmw.dao.CategoryDao;
import oracle.java.bmw.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao cd;
	
	@Override
	public int total() {
		return cd.total();
	}

	@Override
	public List<Category> CategoryListView(Category cate) {
		return cd.CategoryListView(cate);
	}

	@Override
	public Category detail(int categoryNo) {
		return cd.datail(categoryNo);
	}

	@Override
	public int CategoryUpdatePro(Category cate) {
		return cd.CategoryUpdatePro(cate);
	}

	@Override
	public List<Category> CategoryParentNameList(Category cate) {
		return cd.CategoryParentNameList(cate);
	}

	@Override
	public int CategoryDelete(int categoryNo) {
		return cd.CategoryDelete(categoryNo);
	}

	@Override
	public int CategoryWritePro(Category cate) {
		return cd.CategoryWritePro(cate);
	}

	@Override
	public Category detail(String name) {
		return cd.detail(name);
	}

	@Override
	public List<Category> CategoryParentNameList2() {
		return cd.CategoryParentNameList2();
	}

	@Override
	public int cateNameCheck(String name) {
		return cd.cateNameCheck(name);
	}

	@Override
	public int CateNameSearchTotal(Category cate) {
		return cd.CateNameSearchTotal(cate);
	}

	@Override
	public List<Category> CategoryListbySearch(Category cate) {
		return cd.CategoryListbySearch(cate);
	}

	@Override
	public List<Category> headerCateView() {
		return cd.headerCateView();
	}

	@Override
	public Category headerCateSel(String cateSel) {
		return cd.headerCateSel(cateSel);
	}



	
}
