package sg.edu.nus.iss.day24lesson.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    // @ExceptionHandler(BankAccountNotFoundException.class)
    // public ResponseEntity<String> handleBankAccountNotFoundException(BankAccountNotFoundException ex){
    //     return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler(BankAccountNotFoundException.class)
    public ModelAndView handleBankAccountNotFoundException(BankAccountNotFoundException ex, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("error.html");
        ErrorMessage errMsg = new ErrorMessage(ex.getMessage(), new Date(), request.getRequestURI());
        mav.addObject("errMsg", errMsg);
        return mav;
    }
}
