package sg.edu.nus.iss.day24lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day24lesson.model.BankAccount;
import sg.edu.nus.iss.day24lesson.service.BankAccountService;

@RestController
@RequestMapping("/api/bankaccounts")
public class BankAccountController {

    @Autowired
    BankAccountService svc;

    @PostMapping("/create")
    public ResponseEntity<Integer> createAccount(@RequestBody BankAccount bankAccount) {
        Integer createdAccountId = svc.createAccount(bankAccount);
        if (createdAccountId != null) {
            return ResponseEntity.ok().body(createdAccountId);
        } else return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable int accountId){
        BankAccount bankAccount = svc.getAccountById(accountId);
        if(bankAccount != null){
            return ResponseEntity.ok().body(bankAccount);
        } else return ResponseEntity.notFound().build();
    }


    @PutMapping("/transfer")
    public ResponseEntity<Boolean> transfer(@RequestParam(required = true) int withdrawId, @RequestParam(required = true) int depositId, @RequestParam(required = true) float transferAmount){
        Boolean result = svc.transfer(withdrawId, depositId, transferAmount);
        if(result){
            return ResponseEntity.ok().body(result);
        } else return ResponseEntity.badRequest().build();
    } 
}
