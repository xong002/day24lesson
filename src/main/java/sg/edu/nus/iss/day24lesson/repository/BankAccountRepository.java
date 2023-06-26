package sg.edu.nus.iss.day24lesson.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public Integer createAccount(BankAccount bankAccount){
        KeyHolder generatedKey = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(CREATE_ACCOUNT_SQL, new String[] {"id"});
                ps.setString(1, bankAccount.getFullName());
                ps.setBoolean(2, bankAccount.getIsBlocked());
                ps.setBoolean(3,bankAccount.getIsActive());
                ps.setString(4, bankAccount.getAccountType());
                ps.setFloat(5, bankAccount.getBalance());
                return ps;
            }
            
        };
        jdbcTemplate.update(psc, generatedKey);
        return generatedKey.getKey().intValue();
    }

    //alternatively, if no need to return id:
    // return jdbcTemplate.update(CREATE_ACCOUNT_SQL, bankAccount.getFullName(), bankAccount.getIsBlocked(), bankAccount.getIsActive(), bankAccount.getAccountType(), bankAccount.getBalance()) > 0 ? true : false;

}
