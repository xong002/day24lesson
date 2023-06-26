package sg.edu.nus.iss.day24lesson.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day24lesson.model.BankAccount;

@Repository
public class BankAccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_BALANCE_SQL = "select * from bank_account where id = ?";
    private final String WITHDRAW_SQL = "update bank_account set balance = balance - ? where id = ?";
    private final String DEPOSIT_SQL = "update bank_account set balance = balance + ? where id = ?";
    private final String CREATE_ACCOUNT_SQL = "insert into bank_account (full_name, is_blocked, is_active, account_type, balance) values (?, ?, ?, ?, ?)";

    public BankAccount getBalanceById(int id){
        return jdbcTemplate.queryForObject(GET_BALANCE_SQL, BankAccount.class, id);
    }

    public Boolean withdraw(int id, Float withdrawAmount){
        int withdrawSuccess = jdbcTemplate.update(WITHDRAW_SQL, withdrawAmount, id);
        return withdrawSuccess > 0 ? true : false;
    }

    public Boolean deposit(int id, Float depositAmount){
        return jdbcTemplate.update(DEPOSIT_SQL, depositAmount, id) > 0 ? true : false;
    }

}
