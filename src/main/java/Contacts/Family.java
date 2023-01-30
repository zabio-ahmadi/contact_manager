package Contacts;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Family extends Contacts {
  
  public static ObservableList<String> contactRelation = FXCollections.observableArrayList("father", "mother",
      "brother",
      "sister",
      "daugther", "sun");

  private String relation;

  public Family(String name, List<String> lastname, String type, String relation, String address,
      List<String> telelphoneNumbers,
      List<String> emailAddresses,
      List<String> socialAcounts, String profession) {
    super(name, lastname, type, address, telelphoneNumbers, emailAddresses, socialAcounts, profession);

    this.relation = relation;
  }

  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }

}
