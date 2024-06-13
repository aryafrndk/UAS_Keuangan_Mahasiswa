import com.example.keuanganmahasiswa.model.Transaksi;
import com.example.keuanganmahasiswa.RuntimeConfiguration;
import com.example.keuanganmahasiswa.MainApplication;
import com.example.keuanganmahasiswa.model.Transaksi;
import com.example.keuanganmahasiswa.RuntimeConfiguration;
import com.example.keuanganmahasiswa.MainApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;  
import org.junit.jupiter.api.BeforeEach;
import java.util.Properties;

public class TestingProgramt {

    @Test
    public void testGetConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Assertions.assertNotNull(connection);
    }
    @Test
    public void testGetId() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        Assertions.assertEquals(1, transaksi.getId());
    }

    @Test
    public void testGetUser_id() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        Assertions.assertEquals(1, transaksi.getUser_id());
    }

    @Test
    public void testGetNominal() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        Assertions.assertEquals(1000, transaksi.getNominal());
    }

    @Test
    public void testGetJenis_transaksi() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        Assertions.assertEquals("Pemasukan", transaksi.getJenis_transaksi());
    }

    @Test
    public void testSetId() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        transaksi.setId(2);
        Assertions.assertEquals(2, transaksi.getId());
    }

    @Test
    public void testSetUser_id() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        transaksi.setUser_id(2);
        Assertions.assertEquals(2, transaksi.getUser_id());
    }

    @Test
    public void testSetNominal() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        transaksi.setNominal(2000);
        Assertions.assertEquals(2000, transaksi.getNominal());
    }

    @Test
    public void testSetJenis_transaksi() {
        Transaksi transaksi = new Transaksi(1, 1, 1000, "Pemasukan");
        transaksi.setJenis_transaksi("Pengeluaran");
        Assertions.assertEquals("Pengeluaran", transaksi.getJenis_transaksi());
    }
    @Test
    public void testLogin() throws Exception {
        LoginController loginController = new LoginController();
        ActionEvent event = Mockito.mock(ActionEvent.class);

        DatabaseConnection db = Mockito.mock(DatabaseConnection.class);
        Connection connection = Mockito.mock(Connection.class);
        Statement statement = Mockito.mock(Statement.class);
        ResultSet queryResult = Mockito.mock(ResultSet.class);
        Window window = Mockito.mock(Window.class);
        Stage stage = Mockito.mock(Stage.class);
        Scene scene = Mockito.mock(Scene.class);
        FXMLLoader fxmlLoader = Mockito.mock(FXMLLoader.class);


        Mockito.when(db.getConnection()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(queryResult);
        Mockito.when(queryResult.next()).thenReturn(true);
        Mockito.when(fxmlLoader.load()).thenReturn(new Parent() {
        });
        Mockito.when(window.getScene()).thenReturn(scene);
        Mockito.when(window.getWindow()).thenReturn(stage);
        Mockito.when(stage.getScene()).thenReturn(scene);
        Mockito.when(stage.getTitle()).thenReturn("Main");
        Mockito.when(stage.getWindow()).thenReturn(window);
        Mockito.when(MainApplication.class.getResource(Mockito.anyString())).thenReturn(getClass().getResource("main.fxml"));

      
        loginController.setDatabaseConnection(db);
        loginController.setWindow(window);
        loginController.setFXMLLoader(fxmlLoader);

        loginController.login(event);

       
        Assertions.assertNotNull(loginController);
    }
    private ReportController reportController;
    private DatabaseConnection db;
    private Connection connection;
    private Statement statement;
    private ResultSet output;
    private TextField masuk;
    private TextField keluar;
    private TextField total;
    private TextField nominal;
    private ChoiceBox<String> cbTransaksi;
    private TableView<Transaksi> MyTable;
    private TableColumn<Transaksi, Integer> colNo;
    private TableColumn<Transaksi, Integer> colNominal;
    private TableColumn<Transaksi, String> colTransaksi;
    private TableColumn<Transaksi, Void> colAction;
    private ObservableList<Transaksi> mylist;

    @BeforeEach
    public void setup() {
        reportController = new ReportController();
        db = Mockito.mock(DatabaseConnection.class);
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        output = Mockito.mock(ResultSet.class);
        masuk = Mockito.mock(TextField.class);
        keluar = Mockito.mock(TextField.class);
        total = Mockito.mock(TextField.class);

        Mockito.when(db.getConnection()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
    }

    @Test
    public void testCount() throws SQLException {
        String query = "select * from transaksi where user_id=" + RuntimeConfiguration.getLoginId();
        int masukCount = 500;
        int keluarCount = 200;

        Mockito.when(statement.executeQuery(query)).thenReturn(output);
        Mockito.when(output.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(output.getString("jenis_transaksi")).thenReturn("Masuk").thenReturn("Keluar");
        Mockito.when(output.getInt("nominal")).thenReturn(masukCount).thenReturn(keluarCount);

        reportController.setDatabaseConnection(db);
        reportController.setMasuk(masuk);
        reportController.setKeluar(keluar);
        reportController.setTotal(total);

        reportController.count();

        Mockito.verify(masuk).setText(String.valueOf(masukCount));
        Mockito.verify(keluar).setText(String.valueOf(keluarCount));
        Mockito.verify(total).setText(String.valueOf(masukCount - keluarCount));
    }
    @BeforeEach
    // setup main controler
    public void setupM() {
        mainController = new MainController();
        db = Mockito.mock(DatabaseConnection.class);
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(Statement.class);
        output = Mockito.mock(ResultSet.class);
        nominal = Mockito.mock(TextField.class);
        cbTransaksi = Mockito.mock(ChoiceBox.class);
        MyTable = Mockito.mock(TableView.class);
        colNo = Mockito.mock(TableColumn.class);
        colNominal = Mockito.mock(TableColumn.class);
        colTransaksi = Mockito.mock(TableColumn.class);
        colAction = Mockito.mock(TableColumn.class);
        mylist = FXCollections.observableArrayList();

        Mockito.when(db.getConnection()).thenReturn(connection);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(MyTable.getSelectionModel()).thenReturn(Mockito.mock(TableView.TableViewSelectionModel.class));
        Mockito.when(MyTable.getItems()).thenReturn(mylist);
    }

    @Test
    public void testInitialize() throws SQLException {
        List<String> transaksiList = new ArrayList<>();
        transaksiList.add("Masuk");
        transaksiList.add("Keluar");

        Mockito.when(cbTransaksi.setItems(Mockito.any())).thenReturn(Mockito.mock(ObservableList.class));

        mainController.setDatabaseConnection(db);
        mainController.setCbTransaksi(cbTransaksi);

        mainController.initialize(null, null);

        Mockito.verify(cbTransaksi).setItems(FXCollections.observableArrayList(transaksiList));
    }

    @Test
    public void testRead() throws SQLException {
        int loginId = 1;
        int id1 = 1;
        int id2 = 2;
        int nominal1 = 1000;
        int nominal2 = 2000;
        String jenisTransaksi1 = "Masuk";
        String jenisTransaksi2 = "Keluar";
        List<Transaksi> transaksiList = new ArrayList<>();
        transaksiList.add(new Transaksi(id1, loginId, nominal1, jenisTransaksi1));
        transaksiList.add(new Transaksi(id2, loginId, nominal2, jenisTransaksi2));

        Mockito.when(RuntimeConfiguration.getLoginId()).thenReturn(String.valueOf(loginId));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(output);
        Mockito.when(output.next()).thenReturn(true, true, false);
        Mockito.when(output.getInt("id")).thenReturn(id1, id2);
        Mockito.when(output.getInt("user_id")).thenReturn(loginId);
        Mockito.when(output.getInt("nominal")).thenReturn(nominal1, nominal2);
        Mockito.when(output.getString("jenis_transaksi")).thenReturn(jenisTransaksi1, jenisTransaksi2);
        Mockito.when(colNo.setCellFactory(Mockito.any())).thenReturn(Mockito.mock(TableColumn.class));

        mainController.setDatabaseConnection(db);
        mainController.setConnection(connection);
        mainController.setColNo(colNo);
        mainController.setColNominal(colNominal);
        mainController.setColTransaksi(colTransaksi);
        mainController.setColAction(colAction);
        mainController.setMyTable(MyTable);

        mainController.read();

        Mockito.verify(MyTable).setItems(FXCollections.observableArrayList(transaksiList));
    }

    @Test
    public void testDelete() throws SQLException {
        int id = 1;
        String query = "delete from transaksi where id=" + id;

        Mockito.when(statement.executeUpdate(query)).thenReturn(1);

        mainController.setConnection(connection);
        mainController.setStatement(statement);
        mainController.setMyTable(MyTable);

        mainController.delete(id);

        Mockito.verify(MyTable).setItems(Mockito.any());
    }

    @Test
    public void testTambah() throws SQLException {
        int loginId = 1;
        String nominalValue = "1000";
        String jenisTransaksiValue = "Masuk";
        String query = "INSERT INTO transaksi (user_id,nominal,jenis_transaksi) values (?,?,?)";

        Mockito.when(RuntimeConfiguration.getLoginId()).thenReturn(String.valueOf(loginId));
        Mockito.when(connection.prepareStatement(query)).thenReturn(Mockito.mock(PreparedStatement.class));

        mainController.setDatabaseConnection(db);
        mainController.setConnection(connection);
        mainController.setNominal(nominal);
        mainController.setCbTransaksi(cbTransaksi);

        mainController.tambah(null);

        Mockito.verify(nominal).getText();
        Mockito.verify(cbTransaksi).getSelectionModel();
        Mockito.verify(statement).executeUpdate(query);
        Mockito.verify(nominal).setText("");
        Mockito.verify(cbTransaksi).getSelectionModel();
        Mockito.verify(mainController).read();
    }

    @Test
    public void testLogout() throws IOException {
        Mockito.when(nominal.getScene()).thenReturn(Mockito.mock(Scene.class));
        Mockito.when(nominal.getScene().getWindow()).thenReturn(Mockito.mock(Window.class));

        mainController.setNominal(nominal);

        mainController.logout(null);

        Mockito.verify(nominal.getScene().getWindow()).hide();
        Mockito.verify(MainApplication.class.getResource("login.fxml"));
        Mockito.verify(Stage.class).setTitle("Login");
        Mockito.verify(Stage.class).setScene(Mockito.any());
        Mockito.verify(Stage.class).show();
        Mockito.verify(RuntimeConfiguration.class).saveLoginId("0");
    }

    @Test
    public void testToReport() throws IOException {
        Mockito.when(MainApplication.class.getResource("report.fxml")).thenReturn(Mockito.mock(URL.class));
        Mockito.when(FXMLLoader.class.getResource("report.fxml")).thenReturn(Mockito.mock(URL.class));
        Mockito.when(FXMLLoader.load()).thenReturn(Mockito.mock(Parent.class));
        Mockito.when(Stage.class.getConstructor()).thenReturn(Mockito.mock(Stage.class).getClass().getConstructor());

        mainController.toReport(null);

        Mockito.verify(FXMLLoader.class.getResource("report.fxml"));
        Mockito.verify(Stage.class).setScene(Mockito.any());
        Mockito.verify(Stage.class).show();
    }
    private RuntimeConfiguration runtimeConfiguration;
    private Properties properties;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    @BeforeEach
    // setup untuk runtime
    public void setupR() throws IOException {
        runtimeConfiguration = new RuntimeConfiguration();
        properties = Mockito.mock(Properties.class);
        fileInputStream = Mockito.mock(FileInputStream.class);
        fileOutputStream = Mockito.mock(FileOutputStream.class);
    }

    @Test
    public void testSaveLoginId() throws IOException {
        String loginId = "12345";

        Mockito.when(properties.setProperty(Mockito.anyString(), Mockito.anyString())).thenReturn(Mockito.anyString());
        Mockito.when(Mockito.mock(FileOutputStream.class)).thenReturn(fileOutputStream);

        runtimeConfiguration.saveLoginId(loginId);

        Mockito.verify(properties).setProperty(RuntimeConfiguration.LOGIN_ID_KEY, loginId);
        Mockito.verify(properties).store(fileOutputStream, "User Preferences");
    }

    @Test
    public void testGetLoginId() throws IOException {
        String loginId = "12345";

        Mockito.when(properties.getProperty(RuntimeConfiguration.LOGIN_ID_KEY)).thenReturn(loginId);
        Mockito.when(Mockito.mock(FileInputStream.class)).thenReturn(fileInputStream);

        String result = runtimeConfiguration.getLoginId();

        Mockito.verify(properties).load(fileInputStream);
        Assertions.assertEquals(loginId, result);
    }
    private RegisterController registerController;
  
    private Window window;
    private FXMLLoader fxmlLoader;
    private Scene scene;
    private Stage stage;

    @BeforeEach
    // testing regiter
    public void setupRs() {
        registerController = new RegisterController();
        db = Mockito.mock(DatabaseConnection.class);
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(PreparedStatement.class);
        window = Mockito.mock(Window.class);
        fxmlLoader = Mockito.mock(FXMLLoader.class);
        scene = Mockito.mock(Scene.class);
        stage = Mockito.mock(Stage.class);

        Mockito.when(db.getConnection()).thenReturn(connection);
    }

    @Test
    public void testRegister() throws IOException {
        ActionEvent event = Mockito.mock(ActionEvent.class);
        String nim = "123456";
        String username = "john";
        String nama = "John Doe";
        String password = "password";
        String query = "INSERT INTO users (nim,username,nama,password) values (?,?,?,?)";

        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        registerController.setDatabaseConnection(db);
        registerController.setTfNIM(Mockito.mock(TextField.class));
        registerController.setTfUsername(Mockito.mock(TextField.class));
        registerController.setTfNama(Mockito.mock(TextField.class));
        registerController.setPfPassword(Mockito.mock(PasswordField.class));

        registerController.register(event);

        Mockito.verify(statement).setString(1, nim);
        Mockito.verify(statement).setString(2, username);
        Mockito.verify(statement).setString(3, nama);
        Mockito.verify(statement).setString(4, password);
        Mockito.verify(statement).executeUpdate();
        Mockito.verify(registerController.getTfNama()).setText("");
        Mockito.verify(registerController.getTfUsername()).setText("");
        Mockito.verify(registerController.getPfPassword()).setText("");
        Mockito.verify(registerController.getTfNIM()).setText("");
    }

    @Test
    public void testToLogin() throws IOException {
        ActionEvent event = Mockito.mock(ActionEvent.class);

        Mockito.when(registerController.getTfUsername()).thenReturn(Mockito.mock(TextField.class));
        Mockito.when(registerController.getTfUsername().getScene()).thenReturn(scene);
        Mockito.when(registerController.getTfUsername().getScene().getWindow()).thenReturn(window);
        Mockito.when(MainApplication.class.getResource("login.fxml")).thenReturn(Mockito.mock(URL.class));
        Mockito.when(fxmlLoader.load()).thenReturn(Mockito.mock(Parent.class));
        Mockito.when(window.getWindow()).thenReturn(stage);

        registerController.setFXMLLoader(fxmlLoader);

        registerController.toLogin(event);

        Mockito.verify(registerController.getTfUsername().getScene().getWindow()).hide();
        Mockito.verify(fxmlLoader).getResource("login.fxml");
        Mockito.verify(stage).setTitle("Hello!");
        Mockito.verify(stage).setScene(scene);
        Mockito.verify(stage).show();
    }
}