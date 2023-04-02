package edu.wpi.teamg.DAOs;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

  public List<T> getAll() throws SQLException;

  public void update(T obj) throws SQLException;

  public void insert(T obj) throws SQLException;

  public void delete(T obj) throws SQLException;
}
