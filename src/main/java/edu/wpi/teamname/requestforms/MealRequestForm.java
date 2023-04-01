package edu.wpi.teamname.requestforms;

public class MealRequestForm {
  public String mealName;
  public String mealEmployeeID;
  public String mealLocation;
  public String mealOrderer;
  public String mealNotes;
  public String mealFoodChoice;

  public MealRequestForm(
      String mealName,
      String mealEmployeeID,
      String mealLocation,
      String mealOrderer,
      String mealNotes,
      String mealFoodChoice) {
    this.mealName = mealName;
    this.mealEmployeeID = mealEmployeeID;
    this.mealLocation = mealLocation;
    this.mealOrderer = mealOrderer;
    this.mealNotes = mealNotes;
    this.mealFoodChoice = mealFoodChoice;
  }
}
