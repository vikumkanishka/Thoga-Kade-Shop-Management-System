package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ItemDto;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ItemInfoFormController implements Initializable {

    public TextField txtId;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtPackSize;
    public TextField txtQTY;
    public TextArea txtDescription;
    @FXML
    private TableColumn<?, ?> ColPackSize;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableView<ItemDto> tblItemInformations;


    ObservableList <ItemDto> itemDtos = FXCollections.observableArrayList();
    ObservableList <String> categoryList = FXCollections.observableArrayList("Food", "Beverage", "Other");
    ItemController itemController = new ItemController();


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String packSize = txtPackSize.getText();
        Integer qty = Integer.valueOf(txtQTY.getText());
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        itemController.addItem(id, name, price, description, category, packSize, qty);
        loadAllItems();
        clearFeilds();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        itemController.deleteItem(txtId.getText());
        loadAllItems();
        clearFeilds();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        itemDtos.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Thogakade_Shop_Management_System", "root", "200004602360");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE itemId = ?");

            preparedStatement.setObject(1, txtId.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                Double price = resultSet.getDouble(3);
                String description = resultSet.getString(4);
                String category = resultSet.getString(5);
                String packSize = resultSet.getString(6);
                Integer qty = resultSet.getInt(7);

                ItemDto itemDto = new ItemDto(id, name, price, description, category, packSize, qty);
                itemDtos.add(itemDto);
            }
            colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            ColPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));


            tblItemInformations.setItems(itemDtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        Double price = Double.valueOf(txtPrice.getText());
        String packSize = txtPackSize.getText();
        Integer qty = Integer.valueOf(txtQTY.getText());
        String category = String.valueOf(cmbCategory.getValue());
        String description = txtDescription.getText();

        itemController.updateItem(id, name, price, description, category, packSize, qty);

        loadAllItems();
        clearFeilds();
    }

    public void clearFeilds(){
        txtId.clear();
        txtName.clear();
        txtPrice.clear();
        txtPackSize.clear();
        txtQTY.clear();
        cmbCategory.setValue(null);
        txtDescription.clear();
    }

    public void btnReloadOnAction(ActionEvent actionEvent) {

        loadAllItems();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        ColPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
        cmbCategory.setItems(categoryList);

        tblItemInformations.setItems(itemController.getAllItems());
    }

    public void loadAllItems(){
        tblItemInformations.setItems(itemController.getAllItems());
    }
}
