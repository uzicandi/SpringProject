package oracle.java.bmw.model;

public class Ingredient {
	private int ingredientNo;
    private String name;
    private String grade;
    private String danger20;
    private String dangerAllergy;
    private String specialyType;
    private String functional;
    
    // 조회용
 	private String search;
 	private String keyword;
 	private String pageNum;
 	private int start;
 	private int end;
    
	public int getIngredientNo() {
		return ingredientNo;
	}
	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDanger20() {
		return danger20;
	}
	public void setDanger20(String danger20) {
		this.danger20 = danger20;
	}
	public String getDangerAllergy() {
		return dangerAllergy;
	}
	public void setDangerAllergy(String dangerAllergy) {
		this.dangerAllergy = dangerAllergy;
	}
	public String getSpecialyType() {
		return specialyType;
	}
	public void setSpecialyType(String specialyType) {
		this.specialyType = specialyType;
	}
	public String getFunctional() {
		return functional;
	}
	public void setFunctional(String functional) {
		this.functional = functional;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
    
    
}
