import com.gp.cspd.login.UserVerification;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserVerificationTest {
    @Test
    public void textSSNLength(){
        boolean resutl = UserVerification.isValidSSN("1234567891");
        assertEquals(true,resutl);
    }

    @Test
    public void textPasswordLength(){
        boolean resutl = UserVerification.isValidPassword("abcdefj");
        assertEquals(true,resutl);
    }
}