
import Application.Application;
import Contacts.Contacts;
import Contacts.Family;
import Contacts.Friend;
import Contacts.Professional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.management.relation.Relation;

public class MainController implements Initializable {

  Application app = new Application();

  String searchKey = ""; // // for update base search element 1
  List<String> searchKey2 = List.of(""); // for update base search element 2
  @FXML
  private TableView<Contacts> contactTable;

  @FXML
  private TableColumn<Contacts, String> colName;
  @FXML
  private TableColumn<Contacts, List<String>> colLastName;
  @FXML
  private TableColumn<Contacts, String> colType;

  @FXML
  private TableColumn<Contacts, String> colRelation;

  @FXML
  private TableColumn<Contacts, String> colAddress;
  @FXML
  private TableColumn<Contacts, List<String>> colTelephone;

  @FXML
  private TableColumn<Contacts, List<String>> colEmail;

  @FXML
  private TableColumn<Contacts, List<String>> colSocialAcount;
  @FXML
  private TableColumn<Contacts, String> colProfession;

  @FXML
  private TextField name;
  @FXML
  private TextField lastName;
  @FXML
  private ComboBox<String> comboType;
  @FXML
  private ComboBox<String> comboRelation;

  @FXML
  private ImageView profilImage;

  @FXML
  private Text profilName;

  ObservableList<String> contactType = FXCollections.observableArrayList("", "Family", "Friend", "Professional");
  ObservableList<String> contactRelation = FXCollections.observableArrayList("");

  @FXML
  void changeComboType(ActionEvent event) {

    switch (comboType.getValue()) {
      case "Family":
        comboRelation.setItems(Family.contactRelation);
        break;
      case "Friend":
        comboRelation.setItems(Friend.contactRelation);
        break;
      case "Professional":
        comboRelation.setItems(Professional.contactRelation);
      default:

    }
  }

  @FXML
  private TextField address;
  @FXML
  private TextField telephone;
  @FXML
  private TextField email;
  @FXML
  private TextField socialAcount;
  @FXML
  private TextField profession;

  @FXML
  private Button allcontacts;
  @FXML
  private Button familyContacts;
  @FXML
  private Button friendsContacts;
  @FXML
  private Button professionalContacts;

  @FXML
  private Button add;
  @FXML
  private Button update;
  @FXML
  private Button search;
  @FXML
  private Button delete;

  @FXML
  private Button clearFormBtn;

  @FXML
  void clearForm(ActionEvent event) {

    name.clear();
    lastName.clear();
    address.clear();
    telephone.clear();
    email.clear();
    socialAcount.clear();
    profession.clear();
    comboType.setValue(contactType.get(0));
    comboRelation.setValue(null);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    showContacts();

    comboType.setItems(contactType);
    comboRelation.setItems(contactRelation);

  }

  public void showContacts() {

    colName.setCellValueFactory(new PropertyValueFactory<Contacts, String>("name"));

    colLastName.setCellValueFactory(new PropertyValueFactory<Contacts, List<String>>("lastname"));

    colLastName.setCellFactory(col -> {
      return new TableCell<Contacts, List<String>>() {
        @Override
        protected void updateItem(List<String> item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(String.join(", ", item));
          }
        }
      };
    });

    colType.setCellValueFactory(new PropertyValueFactory<Contacts, String>("type"));

    colRelation.setCellValueFactory(new PropertyValueFactory<Contacts, String>("relation"));

    colAddress.setCellValueFactory(new PropertyValueFactory<Contacts, String>("address"));

    colTelephone.setCellValueFactory(new PropertyValueFactory<Contacts, List<String>>("telelphoneNumbers"));
    colTelephone.setCellFactory(col -> {
      return new TableCell<Contacts, List<String>>() {

        @Override
        protected void updateItem(List<String> item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(String.join(", ", item));
          }
        }
      };
    });

    colEmail.setCellValueFactory(new PropertyValueFactory<Contacts, List<String>>("emailAddresses"));
    colEmail.setCellFactory(col -> {
      return new TableCell<Contacts, List<String>>() {
        @Override
        protected void updateItem(List<String> item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(String.join(", ", item));
          }
        }
      };
    });

    colSocialAcount.setCellValueFactory(new PropertyValueFactory<Contacts, List<String>>("socialAcounts"));
    colSocialAcount.setCellFactory(col ->

    {
      return new TableCell<Contacts, List<String>>() {
        @Override
        protected void updateItem(List<String> item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(String.join(", ", item));
          }
        }
      };
    });

    colProfession.setCellValueFactory(new PropertyValueFactory<Contacts, String>("profession"));

    contactTable.setItems(app.getContactList());
  }

  // checks all inputs
  public boolean allInputInvalid() {
    Predicate<String> isZero = (str) -> str.length() == 0;

    var combo1 = comboType.getSelectionModel().getSelectedItem();
    var combo2 = comboRelation.getSelectionModel().getSelectedItem();

    boolean comboInvalid = combo1 == null || combo2 == null;

    boolean inputInvalid = List
        .of(name.getText(), lastName.getText(), address.getText(), telephone.getText(), email.getText(),
            socialAcount.getText())
        .stream()
        .allMatch(isZero);

    return (inputInvalid || comboInvalid);
  }

  // checks input for search : at lease one shouldn't be empty or null
  public boolean searchInputvalid() {

    Predicate<String> isOk = (str) -> str.length() != 0;

    var combo1 = comboType.getSelectionModel().getSelectedItem();
    var combo2 = comboRelation.getSelectionModel().getSelectedItem();

    boolean comboInvalid = combo1 != null || combo2 != null;

    boolean inputInvalid = List
        .of(name.getText(), lastName.getText(), address.getText(), telephone.getText(), email.getText(),
            socialAcount.getText())
        .stream()
        .anyMatch(isOk);

    return (inputInvalid || comboInvalid);
  }

  @FXML
  void addNewConact(ActionEvent event) {

    if (allInputInvalid()) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("add error");
      alert.setContentText("All filed are required !");

      alert.showAndWait();

    } else {
      String contactName = name.getText();
      List<String> contactLastName = Arrays.asList(lastName.getText().split(","));
      String contactType = comboType.getValue();
      String contactRelation = comboRelation.getValue();
      String contactAddress = address.getText();
      List<String> contactTelephone = Arrays.asList(telephone.getText().split(","));
      List<String> contactEmail = Arrays.asList(email.getText().split(","));
      List<String> contactSocialAcount = Arrays.asList(socialAcount.getText().split(","));
      String contactProfession = profession.getText();

      switch (comboType.getValue()) {
        case "Family":
          Family familyContact = new Family(contactName, contactLastName, contactType, contactRelation,
              contactAddress, contactTelephone,
              contactEmail, contactSocialAcount, contactProfession);

          app.addToContactList(familyContact);
          break;
        case "Friend":
          Friend friendContact = new Friend(contactName, contactLastName, contactType, contactRelation,
              contactAddress, contactTelephone,
              contactEmail, contactSocialAcount, contactProfession);
          app.addToContactList(friendContact);
          break;
        case "Professional":
          Professional professionalContact = new Professional(contactName, contactLastName, contactType,
              contactRelation,
              contactAddress, contactTelephone,
              contactEmail, contactSocialAcount, contactProfession);
          app.addToContactList(professionalContact);
        default:
      }

      // save the data to json file
      app.saveContactList(app.getContactList());
      // showContacts();
    }

  }

  @FXML
  void displayForUpdate(MouseEvent event) {

    Contacts temp_contact = contactTable.getSelectionModel().getSelectedItem();
    if (temp_contact != null) {

      Image image = null;
      try {
        image = new Image(
            "https://randomuser.me/api/portraits/men/" + app.getContactList().indexOf(temp_contact) + ".jpg");
      } catch (Exception e) {
        e.printStackTrace();
      }

      profilImage.setImage(image);

      profilName.setText(temp_contact.getName() + " (" + temp_contact.getLastname().get(0) + ")");

      name.setText(temp_contact.getName());
      lastName.setText(String.join(",", temp_contact.getLastname()));
      comboType.setValue(temp_contact.getType());
      comboRelation.setValue(temp_contact.getRelation());

      address.setText(String.join(",", temp_contact.getAddress()));
      telephone.setText(String.join(",", temp_contact.getTelelphoneNumbers()));
      email.setText(String.join(",", temp_contact.getEmailAddresses()));
      socialAcount.setText(String.join(",", temp_contact.getSocialAcounts()));
      profession.setText(temp_contact.getProfession());
      searchKey = temp_contact.getName();
      searchKey2 = temp_contact.getLastname();
    }

  }

  @FXML
  void updateConact(ActionEvent event) {

    if (allInputInvalid()) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("update error");
      alert.setContentText("choose a contact from table !");

      alert.showAndWait();

    } else {
      // populate the contactsList with Contacts objects
      Optional<Contacts> searchedContact = app.getContactList()
          .stream()
          .filter(
              e -> e.getName().equals(searchKey))
          .filter(e -> e.getLastname().get(0).equals(searchKey2.get(0)))
          .findFirst();

      if (searchedContact.isPresent()) {
        int index = app.getContactList().indexOf(searchedContact.get());

        searchedContact.get().setName(name.getText());
        searchedContact.get().setLastname(Arrays.asList(lastName.getText().split(",")));
        searchedContact.get().setType(comboType.getValue());
        searchedContact.get().setRelation(comboRelation.getValue());
        searchedContact.get().setAddress(address.getText());
        searchedContact.get().setEmailAddresses(Arrays.asList(email.getText().split(",")));
        searchedContact.get().setTelelphoneNumbers(Arrays.asList(telephone.getText().split(",")));
        searchedContact.get().setSocialAcounts(Arrays.asList(socialAcount.getText().split(",")));
        searchedContact.get().setProfession(profession.getText());

        app.getContactList().set(index, searchedContact.get());
        searchKey = "";
        searchKey2 = List.of("");
        app.saveContactList(app.getContactList());

      }

    }

  }

  @FXML
  void deleteContact(ActionEvent event) {
    // populate the contactsList with Contacts objects
    app.delete(searchKey);
  }

  @FXML
  void searchContact(ActionEvent event) {

    if (!searchInputvalid()) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("update error");
      alert.setContentText("choose a contact from table !");

      alert.showAndWait();

    } else {
      ObservableList<Contacts> filteredList = app.getContactList().stream()
          .filter(
              e -> e.getName().equals(name.getText())
                  || e.getLastname().get(0).equals(Arrays.asList(lastName.getText().split(",")).get(0))
                  || (comboType.getValue() == null)
                      ? true
                      : e.getType().toLowerCase().equals(comboType.getValue().toLowerCase())

                          || e.getAddress().equals(address.getText())
                          || e.getTelelphoneNumbers().get(0)
                              .equals(Arrays.asList(Arrays.asList(telephone.getText().split(","))))
                          || e.getEmailAddresses().get(0)
                              .equals(Arrays.asList(Arrays.asList(email.getText().split(","))))
                          || e.getSocialAcounts().get(0)
                              .equals(Arrays.asList(Arrays.asList(socialAcount.getText().split(","))))
                          || e.getProfession().equals(profession.getText()))
          .collect(Collectors.toCollection(FXCollections::observableArrayList));

      contactTable.setItems(filteredList);
    }

  }

  @FXML
  void exitApplication(ActionEvent event) {
    app.saveContactList(app.getContactList());
    System.exit(0);
  }

  @FXML
  void showAllcontacts(ActionEvent event) {
    contactTable.setItems(app.getContactList());
  }

  @FXML
  void showFamilyContacts(ActionEvent event) {

    ObservableList<Contacts> filteredList = app.getContactList()
        .stream()
        .filter(e -> e.getType().toLowerCase().equals("Family".toLowerCase()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    contactTable.setItems(filteredList);

  }

  @FXML
  void showFriendsContacts(ActionEvent event) {

    ObservableList<Contacts> filteredList = app.getContactList()
        .stream()
        .filter(e -> e.getType().toLowerCase().equals("Friend".toLowerCase()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    contactTable.setItems(filteredList);
  }

  @FXML
  void showProfessionalContacts(ActionEvent event) {
    ObservableList<Contacts> filteredList = app.getContactList()
        .stream()
        .filter(e -> e.getType().toLowerCase().equals("Professional".toLowerCase()))
        .collect(Collectors.toCollection(FXCollections::observableArrayList));
    contactTable.setItems(filteredList);
  }

}
