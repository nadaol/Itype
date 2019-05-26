package com.example.lmorda.websocketchat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest {

    @Test
    public void test1ValidLogin() throws Exception
    {
        assertEquals("Log in succesfull,Welcome user1 ",Usuario.login("user1","12345"));
    }

    @Test
    public void test2InvalidLogin() throws Exception
    {
        assertEquals("Invalid Username or Password ",Usuario.login("usuarioInvalido","12345"));
    }

    @Test
    public void test3InvalidReg() throws Exception
    {
        assertTrue(Usuario.register("user1","12345").startsWith("Registration Error"));
    }

    @Test
    public void test4UpdateData() throws Exception
    {
        Usuario.updateData();
        assertEquals("user1",Usuario.getName());
    }

}