package edu.wpi.teamg;

public interface DAO<T> {
    public void setup() throws Exception;
    public void connection() throws Exception;
    public void close() throws Exception;


}
