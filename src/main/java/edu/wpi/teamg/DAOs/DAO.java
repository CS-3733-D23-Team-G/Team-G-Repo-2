package edu.wpi.teamg.DAOs;

import java.sql.SQLException;
import java.util.HashMap;

public interface DAO<T> {

  public HashMap<T, T> getAll() throws SQLException;

  public void update(Object old, Object update) throws SQLException;

  public void insert(T obj) throws SQLException;

  public void delete(T obj) throws SQLException;
}
