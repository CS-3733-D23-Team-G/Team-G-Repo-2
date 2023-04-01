package edu.wpi.teamg;

public interface DAO<T> {
    public void setConnection();
    public void closeConnection();

}
