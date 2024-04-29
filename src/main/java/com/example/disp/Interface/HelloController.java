package com.example.disp.Interface;

import com.example.disp.ConnectionDB.InterogationAddRecipe;
import com.example.disp.ConnectionDB.InterogationCustomers;
import com.example.disp.ConnectionDB.InterogationProduct;
import com.example.disp.bussinesLogic.ClientBLL;
import com.example.disp.bussinesLogic.ProductBLL;
import com.example.disp.model.BillRecord;
import com.example.disp.model.Customer;
import com.example.disp.model.Product;
//import com.example.disp.ConnectionDB.Interogation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    private int id_max;
    private int id_max2;
    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMakeOrder;

    @FXML
    private Button btnProducts;

    //
    @FXML
    private TextField adrMod;

    @FXML
    private Button btnADDcst;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnREF;

    @FXML
    private TextField cntAdress;

    @FXML
    private TextField cntMail;

    @FXML
    private TextField cntName;

    @FXML
    private TextField cntName1;

    @FXML
    private TextField cntNameEdit;

    @FXML
    private TextField idModClient;

    @FXML
    private TextField mailMod;
    @FXML
    private TableColumn<?, ?> c1;

    @FXML
    private TableColumn<?, ?> c2;

    @FXML
    private TableColumn<?, ?> c3;

    @FXML
    private TableColumn<?, ?> c4;


    @FXML
    private TableView<Customer> tableViewCustomers;

    @FXML
    private Button btnEdit;
    /**
     * id uri producrts
     */
    @FXML
    private Button addProd;

    @FXML
    private TableColumn<?, ?> c1p;

    @FXML
    private TableColumn<?, ?> c2p;

    @FXML
    private TableColumn<?, ?> c3p;

    @FXML
    private TableColumn<?, ?> c4p;

    @FXML
    private Button dIsplayProducts;

    @FXML
    private TextField delidprod;

    @FXML
    private TextField editIDprod;

    @FXML
    private TextField prodCntName;

    @FXML
    private TextField prodPrice;

    @FXML
    private TextField prodStock;

    @FXML
    private Button prodbtnDel;

    @FXML
    private TextField prodcntAdress;

    @FXML
    private TextField prodcntMail;

    @FXML
    private TextField prodcntName2;

    @FXML
    private Button updateProd;

    @FXML
    private TableView<Product> tableViewProducts;

    @FXML
    private Button CeckBTN;

    @FXML
    private Button addCart;

    @FXML
    private TextField btnAmount;

    @FXML
    private TextField btnIDproduct;

    @FXML
    private TableColumn<?, ?> c12op;

    @FXML
    private TableColumn<?, ?> c1op;

    @FXML
    private TableColumn<?, ?> c22op;

    @FXML
    private TableColumn<?, ?> c2op;

    @FXML
    private TableColumn<?, ?> c32op;

    @FXML
    private TableColumn<?, ?> c3op;

    @FXML
    private TableColumn<?, ?> c42op;

    @FXML
    private TableColumn<?, ?> c4op;

    @FXML
    private TextField idBuyer;

    @FXML
    private Text numeCumparator;

    @FXML
    private Text problemUp;

    @FXML
    private TableView<Product> tab1;

    @FXML
    private TableView<Product> tab2;

    @FXML
    private Button genRecipe;
    ObservableList<Product> listaCos = FXCollections.observableArrayList();
    @FXML
    void handleClicks(ActionEvent event) throws IOException, SQLException {
        ObservableList<Product> listaOBJ = FXCollections.observableArrayList();
        ObservableList<ProductBLL> listaOBJBLL = FXCollections.observableArrayList();
        ObservableList<ClientBLL> listaCltBLL = FXCollections.observableArrayList();
        if(c1op!=null && c2op!=null && c3op!=null &c4op!=null) {
            c1op.setCellValueFactory(new PropertyValueFactory<>("id"));
            c2op.setCellValueFactory(new PropertyValueFactory<>("nume"));
            c3op.setCellValueFactory(new PropertyValueFactory<>("price"));
            c4op.setCellValueFactory(new PropertyValueFactory<>("stock"));
        }
        if (event.getSource() == btnCustomers) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Customers.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root));

            stage.show();

        }else if (event.getSource() == btnProducts) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("thingsToSale.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("ThingsToSale");
            stage.setScene(new Scene(root));

            stage.show();
        }else if (event.getSource() == btnMakeOrder) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderMeniu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Order");
            stage.setScene(new Scene(root));
            stage.show();

        }

        ObservableList<Customer> tableList = FXCollections.observableArrayList();
        if(c1!=null && c2!=null && c3!=null &c4!=null) {
            c1.setCellValueFactory(new PropertyValueFactory<>("id"));
            c2.setCellValueFactory(new PropertyValueFactory<>("nume"));
            c3.setCellValueFactory(new PropertyValueFactory<>("adresa"));
            c4.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if(event.getSource() == btnREF){                //TODO: ref the table
            ResultSet rez= InterogationCustomers.getAllCustomers();
            while(rez.next()){
                String id=rez.getString(1);
                id_max= Integer.parseInt(id);
                String nume=rez.getString(2);
                String locatie=rez.getString(3);
                String email=rez.getString(4);
                Customer newCst= new Customer(id,nume,locatie,email);
                tableList.add(newCst);
            }
            tableViewCustomers.setItems(tableList);
        }


        //events Customers
        if (event.getSource() == btnADDcst) {           //TODO: Add a new customers to the table
            String name = cntName.getText();
            String adress = cntAdress.getText();
            String mail = cntMail.getText();
            InterogationCustomers.addCustomer(name,adress,mail);

        } else if (event.getSource() == btnEdit) {       //TODO: Edit  an obj customers to the table
            String idToEdit = cntName1.getText();
            int id = Integer.parseInt(idToEdit);

            String name = cntNameEdit.getText();
            String adress = adrMod.getText();
            String mail = mailMod.getText();
            System.out.println("TRY TO update:"+id);
            InterogationCustomers.updateCustomer(id,name,adress,mail);

        } else if (event.getSource() == btnDel) {     //TODO: Delete an obj customers to the table
            String idTodel = idModClient.getText();
            int id = Integer.parseInt(idTodel);

            System.out.println("TRY TO DEL id:"+id);
            InterogationCustomers.deleteCustomer(id);

        }


        /**
         * controler for product, handles events on button in window ":things to sale"
         * for products
         */

        ObservableList<Product> tableProducts = FXCollections.observableArrayList();
        if(c1p!=null && c2p!=null && c3p!=null &c4p!=null) {
            c1p.setCellValueFactory(new PropertyValueFactory<>("id"));
            c2p.setCellValueFactory(new PropertyValueFactory<>("nume"));
            c3p.setCellValueFactory(new PropertyValueFactory<>("price"));
            c4p.setCellValueFactory(new PropertyValueFactory<>("stock"));
        }
        if(c12op!=null && c22op!=null && c32op!=null &c42op!=null) {
            c12op.setCellValueFactory(new PropertyValueFactory<>("id"));
            c22op.setCellValueFactory(new PropertyValueFactory<>("nume"));
            c32op.setCellValueFactory(new PropertyValueFactory<>("price"));
            c42op.setCellValueFactory(new PropertyValueFactory<>("stock"));
        }

        if(event.getSource()==dIsplayProducts){
            //System.out.println("aici");
            ResultSet rez=InterogationProduct.getAllProducts();
            while(rez.next()){
                String id=rez.getString(1);
                id_max2= Integer.parseInt(id);
                String nume=rez.getString(2);
                String price=rez.getString(3);
                String stock=rez.getString(4);
                Product newProd= new Product(id,nume,price,stock);
                System.out.println(newProd);
                tableProducts.add(newProd);
            }
            tableViewProducts.setItems(tableProducts);
        }
        else if(event.getSource()==prodbtnDel){
            String idToDel=delidprod.getText();
            int id = Integer.parseInt(idToDel);

            System.out.println("TRY TO DEL id:"+id);
            InterogationProduct.deleteProduct(id);
        }else if(event.getSource()==addProd) {
            String prodName=prodCntName.getText();
            String priceProd=prodcntAdress.getText();
            double priceInt=Double.parseDouble(priceProd);
            String stock=prodcntMail.getText();
            int stockInt=Integer.parseInt(stock);
            id_max2++;
            InterogationProduct.addProduct(id_max2,prodName,priceInt,stockInt);
        }else if(event.getSource()==updateProd){
            String id=editIDprod.getText();
            String prodName=prodcntName2.getText();
            String priceProd=prodPrice.getText();
            double priceInt=Double.parseDouble(priceProd);
            String stock=prodStock.getText();
            int stockInt=Integer.parseInt(stock);

            InterogationProduct.updateProduct(Integer.parseInt(id),prodName,priceInt,stockInt);
        }

        /**
         * in recipe mod
         */

        if(event.getSource()==CeckBTN){
            String idCumparator=idBuyer.getText();
            //System.out.println(idCumparator);
            String str="Hello, ";
            str+=InterogationCustomers.getCustomerNameById(Integer.parseInt(idCumparator));
            //System.out.println(str);
            numeCumparator.setText(str);
            ResultSet rez=InterogationProduct.getAllProducts();
            while(rez.next()){
                String id=rez.getString(1);
                id_max2= Integer.parseInt(id);
                String nume=rez.getString(2);
                String price=rez.getString(3);
                String stock=rez.getString(4);
                Product newProd= new Product(id,nume,price,stock);
                //System.out.println(newProd);
                listaOBJ.add(newProd);
            }
            //System.out.println(tab1);
            if(tab1!=null){
                tab1.setItems(listaOBJ);
            }
        }
        else if(event.getSource()==addCart){

            String idProd = btnIDproduct.getText();
            String amountProd = btnAmount.getText();

            ResultSet resultSet = InterogationProduct.getProductById(Integer.parseInt(idProd));
            while (resultSet.next()) {
                String productId = resultSet.getString("id");
                String productName = resultSet.getString("nume");
                String productPrice = resultSet.getString("pret");
                String productStock = resultSet.getString("cantitate");

                int currentStock = Integer.parseInt(productStock);
                int requestedAmount = Integer.parseInt(amountProd);

                if (requestedAmount <= currentStock) {
                    int newStock = currentStock - requestedAmount;
                    InterogationProduct.updateProduct(Integer.parseInt(productId), productName, Double.parseDouble(productPrice), newStock);

                    Product newProd = new Product(productId, productName, productPrice, amountProd);
                    listaCos.add(newProd);
                } else {
                    problemUp.setText("Stock too low");
                }
            }

            // Set the cart table view to display the updated cart items
            if (tab2 != null) {
                tab2.setItems(listaCos);
            }
            ResultSet rez=InterogationProduct.getAllProducts();
            while(rez.next()){
                String id=rez.getString(1);
                id_max2= Integer.parseInt(id);
                String nume=rez.getString(2);
                String price=rez.getString(3);
                String stock=rez.getString(4);
                Product newProd= new Product(id,nume,price,stock);
                ProductBLL prodBll= new ProductBLL(Integer.parseInt(id),nume, (float)Double.parseDouble(price),Integer.parseInt(stock));
                //listaOBJBLL.add(prodBll);
                listaOBJ.add(newProd);
            }
            //System.out.println(tab1);
            if(tab1!=null){
                tab1.setItems(listaOBJ);
            }
        }else if(event.getSource()==genRecipe){
            String numecmp= InterogationCustomers.getCustomerNameById(Integer.parseInt(idBuyer.getText()));
            double suma=0.f;
            for(Product prdIter:listaCos){
                suma+=Double.parseDouble(prdIter.getPrice())*Integer.parseInt(prdIter.getStock());
            }
            BillRecord bill = new BillRecord(id_max2, numecmp,"-",suma);
            InterogationAddRecipe.addBill(id_max2,numecmp,suma);
        }


    }
}