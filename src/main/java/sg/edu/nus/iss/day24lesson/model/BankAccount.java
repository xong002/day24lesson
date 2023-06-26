package sg.edu.nus.iss.day24lesson.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private int id;
    private String fullName;
    private Boolean isActive;
    private Boolean isBlocked;
    private String accountType;
    private Float balance;
}
