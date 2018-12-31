package dao;

public class Ingredient {
	private int ingredientNo;
	private String name;
	private String grade;
	private String danger20;
	private String dangerAllergy;
	private String specialyType;
	private String functional;

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
	
}