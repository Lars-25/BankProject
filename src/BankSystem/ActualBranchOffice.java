package BankSystem;

import BankSystem.Utils.BranchOfficeRole;

public class ActualBranchOffice {
    private static ActualBranchOffice instance;
    private BranchOfficeRole actualBranchOffice;

    private ActualBranchOffice() {
    }

    public static ActualBranchOffice getInstance() {
        if (instance == null) {
            instance = new ActualBranchOffice();
        }
        return instance;
    }

    public BranchOfficeRole getActualBranchOffice() {
        return actualBranchOffice;
    }

    public void setBracnhOffice(BranchOfficeRole branchOfficeRole) {
        this.actualBranchOffice = branchOfficeRole;
    }

    public boolean thereIsActualBranchOffice() {
        return actualBranchOffice != null;
    }

    public void closeBranchOffice() {
        instance = null;
        actualBranchOffice= null;
    }
}
