import com.revature.dao.UserDao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DaoTests
{
    UserDao userDao = new UserDao();

    @Test
    public void getAGuest(){

        assertNotNull("Message", userDao.getUserByEmail("tim@test.com"));
        assertNotNull("Message", userDao.getUserById(10));
        assertNotNull("Message", userDao.getUserByUsername("tim"));
        assertNull("message", userDao.getUserByUsername("DOES NOT EXIST"));
    }

}
