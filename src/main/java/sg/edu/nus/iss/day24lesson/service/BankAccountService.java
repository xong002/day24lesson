package sg.edu.nus.iss.day24lesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day24lesson.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.day24lesson.model.BankAccount;
import sg.edu.nus.iss.day24lesson.repository.BankAccountRepository;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository repo;

    public BankAccount getAccountById(int id) {
        try {
            return repo.getAccountById(id);
        } catch (EmptyResultDataAccessException erdae) {
            throw new BankAccountNotFoundException("Bank account not found.");
        }
    }

    public Integer createAccount(BankAccount bankAccount) {
            return repo.createAccount(bankAccount);
    }
}
