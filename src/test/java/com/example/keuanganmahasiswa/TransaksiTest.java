package com.example.keuanganmahasiswa;
import org.junit.jupiter.api.Test;

import com.example.keuanganmahasiswa.model.Transaksi;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TransaksiTest {

    @Test
    public void testGetId() {
        Transaksi transaksi = new Transaksi(1, 123, 1000, "Pemasukan");
        assertEquals(1, transaksi.getId());
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