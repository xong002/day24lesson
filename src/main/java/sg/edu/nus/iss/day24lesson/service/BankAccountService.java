package sg.edu.nus.iss.day24lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.day24lesson.model.BankAccount;
import sg.edu.nus.iss.day24lesson.repository.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository repo;

    public BankAccount getAccountById(int id) {
        return repo.getAccountById(id);
    }

    public Integer createAccount(BankAccount bankAccount) {
        return repo.createAccount(bankAccount);
    }

    @Transactional
    public Boolean transfer(int withdrawAccId, int depositAccId, float transferAmount) {
        Boolean bTransfer = false;

        BankAccount withdrawAcc = repo.getAccountById(withdrawAccId);
        BankAccount depositAcc = repo.getAccountById(depositAccId);

        if (withdrawAcc.getIsActive() && depositAcc.getIsActive()) {
            if (!withdrawAcc.getIsBlocked() && !depositAcc.getIsBlocked()) {
                if (withdrawAcc.getBalance() >= transferAmount) {
                    repo.withdraw(withdrawAccId, transferAmount);
                    repo.deposit(depositAccId, transferAmount);
                    bTransfer = true;
                } //throw custom exception here
            } //throw custom exception here
        } //throw custom exception here
        return bTransfer;
    }
}
