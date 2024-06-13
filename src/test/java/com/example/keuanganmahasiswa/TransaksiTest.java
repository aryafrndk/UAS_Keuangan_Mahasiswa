package com.example.keuanganmahasiswa;
import org.junit.jupiter.api.Test;

import com.example.keuanganmahasiswa.model.Transaksi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TransaksiTest {

    @Test
    public void testGetId() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        assertEquals(1, transaksi.getId());
    }
    private DatabaseConnection databaseConnection;

    @BeforeEach
    public void setUp() {
        databaseConnection = new DatabaseConnection();
    }

    @Test
    public void testGetConnection_Success() throws SQLException {
        Connection mockConnection = Mockito.mock(Connection.class);
        Mockito.when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(mockConnection);

        Connection connection = databaseConnection.getConnection();

        assertNotNull(connection);
    }

    @Test
    public void testGetConnection_Failure() {
        try {
            Connection connection = databaseConnection.getConnection();
            assertNull(connection); // Expecting null due to exception in DatabaseConnection
        } catch (Exception e) {
            // Exception is expected due to connection failure in DatabaseConnection
        }
    }

    @Test
    public void testGetUser_id() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        assertEquals(123, transaksi.getUser_id());
    }

    @Test
    public void testGetNominal() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        assertEquals(1000, transaksi.getNominal());
    }

    @Test
    public void testGetJenis_transaksi() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        assertEquals("Pemasukan", transaksi.getJenis_transaksi());
    }

    @Test
    public void testSetId() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        transaksi.setId(2);
        assertEquals(2, transaksi.getId());
    }

    @Test
    public void testSetUser_id() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        transaksi.setUser_id(456);
        assertEquals(456, transaksi.getUser_id());
    }

    @Test
    public void testSetNominal() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        transaksi.setNominal(2000);
        assertEquals(2000, transaksi.getNominal());
    }

    @Test
    public void testSetJenis_transaksi() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        transaksi.setJenis_transaksi("Pengeluaran");
        assertEquals("Pengeluaran", transaksi.getJenis_transaksi());
    }
}