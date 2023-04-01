package edu.wpi.teamg;

import java.util.List;

public interface RequestDAO extends DAO {

  public <T> List<T> getAll();

  public void insert();

  public void update();

  public void delete();
}
