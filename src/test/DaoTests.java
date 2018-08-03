import com.revature.dao.*;
import com.revature.model.Issue;
import com.revature.model.Reservation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DaoTests
{
    private UserDao userDao = new UserDao();
    private HotelDao hotelDao = new HotelDao();
    private HotelRoomDao hotelRoomDao = new HotelRoomDao();
    private ReservationDao reservationDao = new ReservationDao();
    private IssueDao issueDao = new IssueDao();


    @Test
    public void getUserTest()
    {
        assertNotNull(userDao.getUserByEmail("tim@test.com"));
        assertNotNull(userDao.getUserByUsername("tim"));
        assertNotNull(userDao.getUserById(10));
        assertNull(userDao.getUserByUsername("NotAUser"));
    }

    @Test
    public void getReservationTest()
    {
        List<Reservation> reservations = reservationDao.getAllReservationsForHotel(1);
        Reservation reservation = reservations.get(0);
        assertNotNull(reservationDao.getAReservation(reservation.getUserId(), reservation.getHotelRoomId(), reservation.getStartDate(), reservation.getEndDate()));
    }

    @Test
    public void getHotelTest()
    {
        assertNotNull(hotelDao.getHotelById(1));
    }

    @Test
    public void getHotelRoomTest()
    {
        assertNotNull(hotelRoomDao.getAHotelRoomByHotelRoomId(1));
        assertNotNull(hotelRoomDao.getARoomFromAHotelByRoomNumber(101, 1));
    }

    @Test
    public void getIssueTest()
    {
        List<Issue> issues = issueDao.getAllIssues();
        Issue issue = issues.get(0);
        assertNotNull(issueDao.getIssue(issue.getCreatedById(), issue.getCreatedOn()));
    }


}
