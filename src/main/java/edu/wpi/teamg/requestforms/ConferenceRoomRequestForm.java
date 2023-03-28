package edu.wpi.teamg.requestforms;

public class ConferenceRoomRequestForm {
  public String employeeName;
  public String employeeID;
  public String meetingPurpose;
  public String roomDate;
  public String roomTime;
  public String roomNumber;

  public ConferenceRoomRequestForm(
      String employeeName,
      String employeeID,
      String meetingPurpose,
      String roomDate,
      String roomTime,
      String roomNumber) {
    this.employeeName = employeeName;
    this.employeeID = employeeID;
    this.meetingPurpose = meetingPurpose;
    this.roomDate = roomDate;
    this.roomTime = roomTime;
    this.roomNumber = roomNumber;
  }
}
