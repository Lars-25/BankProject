package BankSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import Users.*;
import Users.Utils.Role;
import BankSystem.Utils.BranchOfficeRole;


public class Bank {

    public float funds;

    public Bank(float funds){
        this.funds = funds;
    }

    public float getFunds(){
        return funds;
    }

    public void setFunds(float funds){
        this.funds = funds;
    }

    public static HashMap<BranchOfficeRole, HashMap<Role, ArrayList<User>> > users = new HashMap<>();

    public static ArrayList<Long> numCards  = new ArrayList<>();

    public User verifyLogIn(String username, String password) {
        for (Map.Entry<BranchOfficeRole, HashMap<Role, ArrayList<User>>> bracnhOfficeEntry : users.entrySet()) {
            HashMap<Role, ArrayList<User>> rolesByBranchOffice = bracnhOfficeEntry.getValue();
            for (ArrayList<User> users : rolesByBranchOffice.values()) {
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return user;
                    }
                }
            }
        }
        return null;
    }


    public static void updateInformation(User user) {
        User.updateInformation(user);
    }

    public static ArrayList<Long> getNumTarjetas() {
        return numCards;
    }

    public void addClient(){
        AccountExecutive.registerClient();
    }

    public void addInvestor(){
        Investor.registrarInversionista();
    }

    public void agregarGerente(){
        GerenteDeSucursal.registrarGerente();
    }

    public void agregarEjecutivo(){
        EjecutivoDeCuenta.registrarEjecutivo();
    }

    public void agregarCapturista(){
        Capturista.registrarCapturista();
    }

    public void mostrarAllUsuarios(){
        Usuario.mostrarAllUsuarios();
    }

    public void mostrarAllClientes(){
        Cliente.mostrarAllClientes();
    }

    public void mosttrarAllCapturistas(){
        Capturista.mostrarAllCapturista();
    }

    public void mostrarAllEjecutivos(){
        EjecutivoDeCuenta.mostrarAllEjecutivoDeCuenta();
    }

    public void mostrarAllInversionistas(){
        Inversionista.mostrarAllInversionistas();
    }

    public void mostrarAllGerentes(){
        GerenteDeSucursal.mostrarAllGerenteDeSucursal();
    }


}
