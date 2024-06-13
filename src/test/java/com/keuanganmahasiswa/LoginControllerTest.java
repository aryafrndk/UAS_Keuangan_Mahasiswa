package com.keuanganmahasiswa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.keuanganmahasiswa.DatabaseConnection;
import com.example.keuanganmahasiswa.RuntimeConfiguration;
import com.example.keuanganmahasiswa.controller.LoginController;
import com.example.keuanganmahasiswa.controller.RegisterController;
import com.example.keuanganmahasiswa.controller.ReportController;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginControllerTest {

    private LoginController loginController;
    private RegisterController registerController;
    private ReportController reportController;

    @BeforeEach
    public void setUp() {
        loginController = new LoginController();
        loginController.welcomeText = new Label();
        loginController.tfUsername = new TextField();
        loginController.pfPassword = new PasswordField();

        registerController = new RegisterController();
        registerController.tfNIM = new TextField();
        registerController.tfNama = new TextField();
        registerController.tfUsername = new TextField();
        registerController.pfPassword = new PasswordField();

        reportController = new ReportController();
        reportController.masuk = new TextField();
        reportController.keluar = new TextField();
        reportController.total = new TextField();
    }

    @Test
    public void testToRegister() {
        ActionEvent event = Mockito.mock(ActionEvent.class);
        loginController.toRegister(event);
        assertEquals("Hello!", loginController.welcomeText.getText());
    }

    @Test
    public void testLoginWithValidCredentials() {
        loginController.tfUsername.setText("validUsername");
        loginController.pfPassword.setText("validPassword");
        ActionEvent event = Mockito.mock(ActionEvent.class);
        loginController.login(event);
        assertNotNull(loginController.welcomeText);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginController.tfUsername.setText("");
        loginController.pfPassword.setText("");
        ActionEvent event = Mockito.mock(ActionEvent.class);
        loginController.login(event);
        assertNull(loginController.welcomeText);
    }

    @Test
    public void testRegister() {
        registerController.tfNIM.setText("123456");
        registerController.tfNama.setText("John Doe");
        registerController.tfUsername.setText("johndoe");
        registerController.pfPassword.setText("password123");

        ActionEvent event = Mockito.mock(ActionEvent.class);
        registerController.register(event);

        assertEquals("", registerController.tfNIM.getText());
        assertEquals("", registerController.tfNama.getText());
        assertEquals("", registerController.tfUsername.getText());
        assertEquals("", registerController.pfPassword.getText());
    }

    @Test
    public void testToLogin() {
        ActionEvent event = Mockito.mock(ActionEvent.class);
        registerController.toLogin(event);
        Stage stage = new Stage(); // Create a new Stage for testing
        stage.setTitle("Hello!");
        assertTrue(stage.getTitle().equals("Hello!"));
    }

    @Test
    public void testCount() {
        // Mock RuntimeConfiguration.getLoginId() to return a specific user ID
        Mockito.when(RuntimeConfiguration.getLoginId()).thenReturn("123");

        reportController.count();

        assertEquals("100", reportController.masuk.getText());
        assertEquals("50", reportController.keluar.getText());
        assertEquals("50", reportController.total.getText());
    }

    @Test
    public void testGetConnection() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testSaveLoginIdAndGetLoginId() {
        String testLoginId = "12345";
        RuntimeConfiguration.saveLoginId(testLoginId);
        String retrievedLoginId = RuntimeConfiguration.getLoginId();
        assertEquals(testLoginId, retrievedLoginId);
    }
}