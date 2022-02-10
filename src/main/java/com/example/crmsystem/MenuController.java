package com.example.crmsystem;

import Backend.BusinessObjects.Customer;
import Backend.BusinessObjects.Representative;
import Backend.BusinessObjects.Sale;
import Backend.Chartmaker;
import Backend.DAO.CustomerDao;
import Backend.DAO.RepresentativeDao;
import Backend.DAO.SaleDao;
import Backend.Database.FileHandler;
import Backend.Export;
import Backend.SaleObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuController {

    @FXML
    private Button buttonCustomers;

    @FXML
    private Button buttonRegisterCustomer;

    @FXML
    private Button buttonRegisterSale;

    @FXML
    private Button buttonReps;

    @FXML
    private Button buttonSales;

    @FXML
    private ChoiceBox<Customer> choiceBuyer;

    @FXML
    private ChoiceBox<Representative> choiceSeller;

    @FXML
    private GridPane customerGrid;

    @FXML
    private GridPane registerSaleGrid;

    @FXML
    private GridPane registerCustomerGrid;

    @FXML
    private Pane registerSalePane;

    @FXML
    private GridPane repGrid;

    @FXML
    private GridPane saleGrid;

    @FXML
    private TableColumn<Customer, String> tableColumnAddressCustomer;

    @FXML
    private TableColumn<Representative, String> tableColumnAddressRep;

    @FXML
    private TableColumn<Sale, String> tableColumnAmountSale;

    @FXML
    private TableColumn<Sale, String> tableColumnBuyerSale;

    @FXML
    private TableColumn<Customer, Long> tableColumnIdCustomer;

    @FXML
    private TableColumn<Representative, Long> tableColumnIdRep;

    @FXML
    private TableColumn<Sale, String> tableColumnIdSale;

    @FXML
    private TableColumn<Customer, String> tableColumnNameCustomer;

    @FXML
    private TableColumn<Representative, String> tableColumnNameRep;

    @FXML
    private TableColumn<Sale, String> tableColumnProductNameSale;

    @FXML
    private TableColumn<Sale, String> tableColumnSellerSale;

    @FXML
    private TableColumn<Customer, Date> tableColumnTOCCustomer;

    @FXML
    private TableColumn<Representative, Date> tableColumnTOCRep;

    @FXML
    private TableColumn<Sale, Date> tableColumnTOSSale;

    @FXML
    private TableView<Customer> tableviewCustomer;

    @FXML
    private TableView<Representative> tableviewRep;

    @FXML
    private TableView<Sale> tableviewSale;

    @FXML
    private TextField textProductAmount;

    @FXML
    private TextField textProductName;

    @FXML
    private Text textWelcome;

    @FXML
    private Text topPaneText;

    @FXML
    private Button buttonRegisterActualSale;

    @FXML
    private TextField inputCustomerName;

    @FXML
    private TextField inputCustomerAddress;

    @FXML
    private Button buttonRegisterActualCustomer;

    @FXML
    private Button buttonActivity;

    @FXML
    private GridPane activityGrid;

    @FXML
    private TextArea textfieldActivities;

    @FXML
    private Button logOut;

    @FXML
    private TextField textfieldActivityIndex;

    @FXML
    private TextArea textfieldCustomerHistory;

    @FXML
    private GridPane customerHistoryGrid;

    @FXML
    private Button buttonCustomerHistoryIndex;

    @FXML
    private TextField textfieldCustomerIndex;

    @FXML
    private TextField graphTextfieldCustomer;

    @FXML
    private TextField graphTextfieldProduct;

    @FXML
    private TextField graphTextfieldRep;

    @FXML
    private Button buttonGraphs;

    @FXML
    private GridPane graphGrid;

    @FXML
    private GridPane graphCustomerGrid;

    @FXML
    private LineChart<?, ?> graphCustomer;

    @FXML
    private LineChart<?, ?> graphProduct;

    @FXML
    private GridPane graphProductGrid;

    @FXML
    private LineChart<?, ?> graphRep;

    @FXML
    private GridPane graphRepGrid;

    @FXML
    private GridPane ExportCustomerGrid;

    @FXML
    private GridPane ExportProductGrid;

    @FXML
    private GridPane ExportRepGrid;

    @FXML
    private ChoiceBox<Long> textRepIdChoicebox;

    @FXML
    private TextArea textRepExportIntro;

    @FXML
    private CheckBox textRepExportRepCheckbox;

    @FXML
    private TextField textRepExportTitle;

    @FXML
    private ChoiceBox<String> textProductIdChoicebox;

    @FXML
    private TextArea textProductExportIntro;

    @FXML
    private CheckBox textProductExportRepCheckbox;

    @FXML
    private TextField textProductExportTitle;

    @FXML
    private ChoiceBox<Long> textCustomerIdChoicebox;

    @FXML
    private TextArea textCustomerExportIntro;

    @FXML
    private CheckBox textCustomerExportRepCheckbox;

    @FXML
    private TextField textCustomerExportTitle;



    @FXML
    void repExportClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        Chartmaker chartmaker = new Chartmaker();
        RepresentativeDao repdao = new RepresentativeDao();
        Export export = null;
        if(textRepExportRepCheckbox.isSelected()){
            export = new Export.ExportBuilder(chartmaker.representativeChart(repdao.get(textRepIdChoicebox.getValue()-1).get()))
                    .title("***"+textRepExportTitle.getText()+"***\n\n")
                    .intro(textRepExportIntro.getText()+"\n\n")
                    .rep(loginRep)
                    .build();
        }
        else{
            export = new Export.ExportBuilder(chartmaker.representativeChart(repdao.get(textRepIdChoicebox.getValue() - 1).get()))
                    .title("***"+textRepExportTitle.getText()+"***\n\n")
                    .intro(textRepExportIntro.getText()+"\n\n")
                    .build();
        }
        FileHandler.getInstance().saveString(export.toString());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export");
        alert.setContentText("Exported to export.txt Successfully!");
        alert.showAndWait();
    }
    @FXML
    void customerExportClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        Chartmaker chartmaker = new Chartmaker();
        CustomerDao cusdao = new CustomerDao();
        Export export = null;
        if(textCustomerExportRepCheckbox.isSelected()){
            export = new Export.ExportBuilder(chartmaker.customerChart(cusdao.get(textCustomerIdChoicebox.getValue()-1).get()))
                    .title("***"+textCustomerExportTitle.getText()+"***\n\n")
                    .intro(textCustomerExportIntro.getText()+"\n\n")
                    .rep(loginRep)
                    .build();
        }
        else{
            export = new Export.ExportBuilder(chartmaker.customerChart(cusdao.get(textCustomerIdChoicebox.getValue() - 1).get()))
                    .title("***"+textCustomerExportTitle.getText()+"***\n\n")
                    .intro(textCustomerExportIntro.getText()+"\n\n")
                    .build();
        }
        FileHandler.getInstance().saveString(export.toString());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export");
        alert.setContentText("Exported to export.txt Successfully!");
        alert.showAndWait();
    }
    @FXML
    void ProductExportClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        Chartmaker chartmaker = new Chartmaker();
        SaleDao saledao = new SaleDao();
        Export export = null;
        if(textProductExportRepCheckbox.isSelected()){
            export = new Export.ExportBuilder(chartmaker.saleChart(textProductIdChoicebox.getValue()))
                    .title("***"+textProductExportTitle.getText()+"***\n\n")
                    .intro(textProductExportIntro.getText()+"\n\n")
                    .rep(loginRep)
                    .build();
        }
        else{
            export = new Export.ExportBuilder(chartmaker.saleChart(textProductIdChoicebox.getValue()))
                    .title("***"+textProductExportTitle.getText()+"***\n\n")
                    .intro(textProductExportIntro.getText()+"\n\n")
                    .build();
        }
        FileHandler.getInstance().saveString(export.toString());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export");
        alert.setContentText("Exported to export.txt Successfully!");
        alert.showAndWait();
    }

    @FXML
    void ExportRepsClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao repDao = new RepresentativeDao();
        topPaneText.setText("Export graph - Representative: "+repDao.get(textRepIdChoicebox.getValue()-1).get().getName());
        ExportRepGrid.toFront();
    }

    @FXML
    void ExportCustomersClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        CustomerDao cusdao = new CustomerDao();
        topPaneText.setText("Export graph - Customer: "+cusdao.get(textCustomerIdChoicebox.getValue()-1).get().getName());
        ExportCustomerGrid.toFront();
    }

    @FXML
    void ExportProductsClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        topPaneText.setText("Export graph - Sale: "+textProductIdChoicebox.getValue());
        ExportProductGrid.toFront();
    }


    @FXML
    void graphButtonCustomer(ActionEvent event) throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.get(textCustomerIdChoicebox.getValue()-1).get();
        graphCustomerInitiate(customer, "HHmm");
        topPaneText.setText("Graphs - "+customer.getName());
        graphCustomerGrid.toFront();
    }


    @FXML
    void graphCustomerClearButton(ActionEvent event) {
        graphCustomer.getData().clear();
    }


    @FXML
    void graphCustomerHourMinuteButton(ActionEvent event) throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.get(textCustomerIdChoicebox.getValue()-1).get();
        graphCustomerInitiate(customer, "HHmm");
    }

    @FXML
    void graphCustomerMonthButton(ActionEvent event) throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.get(textCustomerIdChoicebox.getValue()-1).get();
        graphCustomerInitiate(customer, "MM");
    }

    @FXML
    void graphCustomerYearButton(ActionEvent event) throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.get(textCustomerIdChoicebox.getValue()-1).get();
        graphCustomerInitiate(customer, "dd");
    }

    @FXML
    void graphCustomerInitiate(Customer customer, String format) throws IOException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();
        series.setName("Sales");
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        SaleDao saleDao = new SaleDao();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.get(i).get().getBuyerId() == customer.getId()){
                series.getData().add(new XYChart.Data(Long.parseLong(dateFormat.format(saleDao.get(i).get().getTimeOfSale())), saleDao.get(i).get().getAmount()));
            }
        }
        graphCustomer.getData().add(series);
        graphCustomer.getXAxis().setLabel("Date ("+format+")");
    }

    @FXML
    void graphButtonProduct(ActionEvent event) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        List<Sale> products = new ArrayList<Sale>();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.getAll().get(i).getProduct().equals(textProductIdChoicebox.getValue())){
                products.add(saleDao.getAll().get(i));
            }
        }

        graphProductInitiate(products, "HHmm");
        topPaneText.setText("Graphs - "+products.get(0).getProduct());
        graphProductGrid.toFront();
    }

    @FXML
    void graphProductInitiate(List<Sale> products, String format){
        XYChart.Series series = new XYChart.Series();
        series.setName(products.get(0).getProduct());
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        for (int i = 0; i < products.size(); i++) {
            series.getData().add(new XYChart.Data(Long.parseLong(dateFormat.format(products.get(i).getTimeOfSale())), products.get(i).getAmount()));
        }
        graphProduct.getData().add(series);
        graphProduct.getXAxis().setLabel("Date ("+format+")");
    }

    @FXML
    void graphProductClearButton(ActionEvent event) {
        graphProduct.getData().clear();
    }

    @FXML
    void graphProductHourMinuteButton(ActionEvent event) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        List<Sale> products = new ArrayList<Sale>();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.getAll().get(i).getProduct().equals(textProductIdChoicebox.getValue())){
                products.add(saleDao.getAll().get(i));
            }
        }
        graphProductInitiate(products, "HHmm");
    }

    @FXML
    void graphProductMonthButton(ActionEvent event) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        List<Sale> products = new ArrayList<Sale>();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.getAll().get(i).getProduct().equals(textProductIdChoicebox.getValue())){
                products.add(saleDao.getAll().get(i));
            }
        }
        graphProductInitiate(products, "MM");
    }

    @FXML
    void graphProductYearButton(ActionEvent event) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        List<Sale> products = new ArrayList<Sale>();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.getAll().get(i).getProduct().equals(textProductIdChoicebox.getValue())){
                products.add(saleDao.getAll().get(i));
            }
        }
        graphProductInitiate(products, "dd");
    }

    @FXML
    void graphRepInitiate(Representative rep, String format) throws IOException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();
        series.setName("Sales");
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        SaleDao saleDao = new SaleDao();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(saleDao.get(i).get().getSellerId() == rep.getId()){
                series.getData().add(new XYChart.Data(Long.parseLong(dateFormat.format(saleDao.get(i).get().getTimeOfSale())), saleDao.get(i).get().getAmount()));
            }
        }
        graphRep.getData().add(series);
        graphRep.getXAxis().setLabel("Date ("+format+")");
    }

    @FXML
    void graphButtonRep(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao repDao = new RepresentativeDao();
        Representative rep = repDao.get(textRepIdChoicebox.getValue()-1).get();
        graphRepInitiate(rep, "HHmm");
        topPaneText.setText("Graphs - "+rep.getName());
        graphRepGrid.toFront();
    }
    @FXML
    void graphRepClearButton(ActionEvent event) {
        graphRep.getData().clear();
    }


    @FXML
    void graphRepHourMinuteButton(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao representativeDao = new RepresentativeDao();
        Representative representative = representativeDao.get(textRepIdChoicebox.getValue()-1).get();
        graphRepInitiate(representative, "HHmm");
    }

    @FXML
    void graphRepMonthButton(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao representativeDao = new RepresentativeDao();
        Representative representative = representativeDao.get(textRepIdChoicebox.getValue()-1).get();
        graphRepInitiate(representative, "MM");
    }

    @FXML
    void graphRepYearButton(ActionEvent event) throws IOException, ClassNotFoundException {
        RepresentativeDao representativeDao = new RepresentativeDao();
        Representative representative = representativeDao.get(textRepIdChoicebox.getValue()-1).get();
        graphRepInitiate(representative, "dd");
    }

    @FXML
    void buttonCustomerHistoryIndexClicked(ActionEvent event) throws IOException, ClassNotFoundException {
    try {
        CustomerDao customerDao = new CustomerDao();
        Customer customer = customerDao.get(Long.parseLong(textfieldCustomerIndex.getText()) - 1).get();
        customerHistoryTextfield(customer);
        topPaneText.setText("Customer - "+customer.getName());
        customerHistoryGrid.toFront();
    } catch(NumberFormatException e){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("ERROR!");
        error.setContentText("Invalid ID: ID must be a number");
        error.showAndWait();
    } catch(RuntimeException e){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("ERROR!");
        error.setContentText("Invalid ID: ID must be above 0");
        error.showAndWait();
    }
    }

    @FXML
    void buttonRegisterCustomerClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        Customer customer = new Customer(inputCustomerName.getText(), inputCustomerAddress.getText());
        Alert idAlert = new Alert(Alert.AlertType.INFORMATION);
        idAlert.setTitle("New Customer Registered!");
        idAlert.setContentText("Name: "+customer.getName()+"\n"+
                "Address: "+customer.getAddress());
        idAlert.showAndWait();
        CustomerDao customerDao = new CustomerDao();
        customerDao.save(customer);
    }

    @FXML
    void buttonRegisterSaleClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        try{
            if(Integer.parseInt(textProductAmount.getText())<=0)
                throw new RuntimeException();
            Sale sale = new Sale(textProductName.getText(), Integer.parseInt(textProductAmount.getText()), choiceBuyer.getValue(), choiceSeller.getValue());
            Alert idAlert = new Alert(Alert.AlertType.INFORMATION);
            idAlert.setTitle("New Sale Registered!");
            idAlert.setContentText("Product: "+sale.getProduct()+"\n"+
                    "Amount: "+sale.getAmount()+"\n"+
                    "Buyer: "+sale.getBuyer()+"\n"+
                    "Seller: "+sale.getSeller());
            idAlert.showAndWait();
            sale.addPropertyChangeListener(new SaleObserver());
            sale.newSale();
            SaleDao saleDao = new SaleDao();
            saleDao.save(sale);
            RepresentativeDao repdao = new RepresentativeDao();
            choiceSeller.getValue().addToSalesList(sale);
            repdao.update(repdao.get(choiceSeller.getValue().getId()-1).get(), choiceSeller.getValue());
        } catch(NumberFormatException e){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("ERROR!");
        error.setContentText("Invalid ID: ID must be a number");
        error.showAndWait();
//    } catch(RuntimeException e){
//        Alert error = new Alert(Alert.AlertType.ERROR);
//        error.setTitle("ERROR!");
//        error.setContentText("Invalid ID: ID must be above 0");
//        error.showAndWait();
    }
    }


    @FXML
    void buttonActivityAllClicked(ActionEvent event) throws IOException, ClassNotFoundException {
    long repindex = loginRep.getId();
    loginRep.removeAllActivities();
    RepresentativeDao repdao = new RepresentativeDao();
    repdao.update(repdao.get(repindex-1).get(), loginRep);
    activityTextfield();
    }

    @FXML
    void buttonActivityIndexClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        try {
            long repindex = loginRep.getId();
            if (Integer.parseInt(textfieldActivityIndex.getText())<=0)
                throw new RuntimeException();
            loginRep.removeActivity(Integer.parseInt(textfieldActivityIndex.getText()) - 1);
            RepresentativeDao repdao = new RepresentativeDao();
            repdao.update(repdao.get(repindex - 1).get(), loginRep);
            activityTextfield();
        } catch (NumberFormatException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Invalid ID: ID must be a number");
            error.showAndWait();
        } catch (RuntimeException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Invalid ID: ID must be above 0");
            error.showAndWait();
        }
    }
    private MainController mainController;

    Representative loginRep;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setLoginRep(Representative loginRep) {
        this.loginRep = loginRep;
        textWelcome.setText(textWelcome.getText()+loginRep.getName());
    }

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        customerTable();

    }

    private void customerTable() throws IOException, ClassNotFoundException {
        tableColumnAddressCustomer.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColumnIdCustomer.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNameCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnTOCCustomer.setCellValueFactory(new PropertyValueFactory<>("timeOfCreation"));
        tableviewCustomer.setItems(getCustomers());
        tableviewCustomer.getColumns().setAll(tableColumnIdCustomer, tableColumnNameCustomer, tableColumnAddressCustomer, tableColumnTOCCustomer);
    }

    private void representativeTable() throws IOException, ClassNotFoundException {
        tableColumnAddressRep.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableColumnIdRep.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnNameRep.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnTOCRep.setCellValueFactory(new PropertyValueFactory<>("timeOfCreation"));
        tableviewRep.setItems(getRepresentatives());
        tableviewRep.getColumns().setAll(tableColumnIdRep, tableColumnNameRep, tableColumnAddressRep, tableColumnTOCRep);
    }

    private void saleTable() throws IOException, ClassNotFoundException {
        tableColumnIdSale.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnProductNameSale.setCellValueFactory(new PropertyValueFactory<>("product"));
        tableColumnAmountSale.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableColumnSellerSale.setCellValueFactory(new PropertyValueFactory<>("seller"));
        tableColumnBuyerSale.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        tableColumnTOSSale.setCellValueFactory(new PropertyValueFactory<>("timeOfSale"));
        tableviewSale.setItems(getSales());
        tableviewSale.getColumns().setAll(tableColumnIdSale, tableColumnProductNameSale, tableColumnAmountSale, tableColumnSellerSale, tableColumnBuyerSale, tableColumnTOSSale);
    }

    private void activityTextfield() throws IOException, ClassNotFoundException {

        List<String> currentUsersActivities = getRepresentatives().get((int) loginRep.getId()-1).getActivityList();
        String activitiesText ="";
        for (int i = 0; i < currentUsersActivities.size(); i++) {
            activitiesText = activitiesText+currentUsersActivities.get(i)+"\n";
        }
        textfieldActivities.setText(activitiesText);
    }

    private void customerHistoryTextfield(Customer customer) throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        String customerHistory = "";
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if (saleDao.get(i).get().getBuyerId()==customer.getId()){
                customerHistory+=(saleDao.get(i).get().toString())+"\n";
            }
        }
        textfieldCustomerHistory.setText(customerHistory);
    }

    public ObservableList<Customer> getCustomers() throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerDao.getAll());
        return observableList;
    }
    public ObservableList<Long> getCustomersId() throws IOException, ClassNotFoundException {
        CustomerDao customerDao = new CustomerDao();
        ObservableList<Long> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < customerDao.getAll().size(); i++) {
            observableList.add(customerDao.get(i).get().getId());
        }
        return observableList;
    }
    public ObservableList<Representative> getRepresentatives() throws IOException, ClassNotFoundException {
        RepresentativeDao representativeDao = new RepresentativeDao();
        ObservableList<Representative> observableList = FXCollections.observableArrayList();
        observableList.addAll(representativeDao.getAll());
        return observableList;
    }
    public ObservableList<Long> getRepId() throws IOException, ClassNotFoundException {
        RepresentativeDao representativeDao = new RepresentativeDao();
        ObservableList<Long> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < representativeDao.getAll().size(); i++) {
            observableList.add(representativeDao.get(i).get().getId());
        }
        return observableList;
    }
    public ObservableList<Sale> getSales() throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        ObservableList<Sale> observableList = FXCollections.observableArrayList();
        observableList.addAll(saleDao.getAll());
        return observableList;
    }
    public ObservableList<String> getProductName() throws IOException, ClassNotFoundException {
        SaleDao saleDao = new SaleDao();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (int i = 0; i < saleDao.getAll().size(); i++) {
            if(!(observableList.contains(saleDao.get(i).get().getProduct().toString())))
            observableList.add(saleDao.get(i).get().getProduct().toString());
        }
        return observableList;
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException, ClassNotFoundException {
        if(event.getSource() == buttonCustomers){
            topPaneText.setText("Customers");
            customerTable();
            customerGrid.toFront();
        }
        else if(event.getSource() == buttonReps){
            topPaneText.setText("Representatives");
            representativeTable();
            repGrid.toFront();
        }
        else if(event.getSource() == buttonSales){
            topPaneText.setText("Sales");
            saleTable();
            saleGrid.toFront();
        }
        else if(event.getSource() == buttonRegisterSale){
            topPaneText.setText("Register Sale");
            getChoiceboxValues();
            registerSaleGrid.toFront();
        }
        else if(event.getSource() == buttonRegisterCustomer){
            topPaneText.setText("Register Customer");
            registerCustomerGrid.toFront();
        }
        else if(event.getSource() == buttonActivity){
            topPaneText.setText("Activity List - "+loginRep.getName());
            activityTextfield();
            activityGrid.toFront();
        }
        else if(event.getSource() == logOut){
            Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Start.fxml"));
            Scene scene = new Scene(loader.load());
            thisStage.setScene(scene);
            thisStage.show();
        }
        else if(event.getSource() == buttonGraphs){
            topPaneText.setText("Graphs");
            textCustomerIdChoicebox.setItems(getCustomersId());
            textProductIdChoicebox.setItems(getProductName());
            textRepIdChoicebox.setItems(getRepId());
            graphGrid.toFront();
        }
    }

    @FXML
    private void getChoiceboxValues() throws IOException, ClassNotFoundException {
        choiceBuyer.setItems(getCustomers());
        choiceSeller.setItems(getRepresentatives());
    }


}
