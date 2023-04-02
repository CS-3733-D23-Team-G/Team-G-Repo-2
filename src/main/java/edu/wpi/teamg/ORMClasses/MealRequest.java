package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class MealRequest extends Request{

  @Getter @Setter private String recipient;
  @Getter @Setter private String order;
  @Getter @Setter private String note;

  public MealRequest() {
  }
}

// AL<Request>
// AL<MealRequest>
// AL<Request> = AL<MealRequest> + AL<FlowerRequest>
