package timemaster_tests;

import com.jayway.restassured.RestAssured;
import org.junit.*;
import timemaster.TimeMasterController;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class REST_TimeMaster_Tests extends TimeMasterController {


    private static Map<String, String> cookies;


    @BeforeClass
    public static void beforeClass() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    static int ID = another_ID;

    @BeforeClass
    public static void before() {
        cookies = getCookie(another_userName, another_userPassword);
        // cookies = getCookie(super_userName, super_userPassword);
    }

    @AfterClass
    public static void after() {
        logout(cookies);
    }

    @Test
    public void loginPost() {
        assertTrue("OUPS, user don't get cookies ", !cookies.isEmpty());
    }

    // ----------------------> BALANCE
    @Test
    public void getBalanceCurrentTest() {
        String resp = getBalanceCurrent(cookies);
        System.out.println(cookies);
        assertTrue("Current balance is empty. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getBalanceByIdTest() {
        String resp = getBalanceById(cookies, ID);
        assertTrue("Current balance is empty. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getBalanceYearTest() {
        String resp = getBalanceYear(cookies, 2018);
        assertTrue("Some error in getting balance by year. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getBalanceYearMonthTest() {
        String resp = getBalanceYearMonth(cookies, 2018, 8);
        assertTrue("Some error in getting balance by year and month. Status - " + resp, resp.equals(status200));
    }


    //add POSTs requests
    //for what need getBalanceYearMonth
// <---------------------- BALANCE

    // ----------------------> DAY_CODES
    @Test
    public void getDayCodesCurrentTest() {
        String resp = getDayCodesCurrent(cookies);
        assertTrue("Some error in getting day codes. Status - " + resp, resp.equals(status200));
    }
// <---------------------- DAY_CODES

    // ----------------------> DAYS
    @Test
    public void getCurrentDaysByYearAndMonthTest() {
        String resp = getCurrentDaysByYearAndMonth(cookies, 2018, 7);
        assertTrue("Some error in getting days by year and month. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getCurrentDaysByYearAndMonthAndDayTest() {
        String resp = getCurrentDaysByYearAndMonthAndDay(cookies, 2018, 7, 3);
        assertTrue("Some error in getting days by year, month and day. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getValidateDaysByYearAndMonthTest() {
        String resp = getValidateDaysByYearAndMonth(cookies, 2018, 7);
        assertTrue("Some error in getting validate days by year and month. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getDaysOfUserByYearMonthTest() {
        String resp = getDaysOfUserByYearMonth(cookies, 163, 2018, 7);
        assertTrue("Some error in getting days of user by user id, year and month. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getDaysOfUserByYearMonthDayTest() {
        String resp = getDaysOfUserByYearMonthDay(cookies, ID, 2018, 6, 5);
        assertTrue("Some error in getting days of user by user id, year and month. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void putDaysTest() {
        String resp = putDays(cookies, "2018-08-07", "Day from REST Test");
        assertTrue("Some error in putting Days. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void putDaysByIDTest() { //CSRF
        String resp = putDaysByID(cookies, ID, "2018-08-07", "Day from REST Test");
        assertTrue("Some error in putting Days by ID. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void postSetBreakTimeTest() { //CSRF
        String resp = postSetBreakTime(cookies, 200);
        assertTrue("Some error in posting BreakTime. Status - " + resp, resp.equals(status200));
    }


// <---------------------- DAYS

    // ----------------------> HOLIDAYS
    @Test
    public void getHolidaysTest() {
        String resp = getHolidays(cookies, 2018, 9);
        assertTrue("Some error in getting holidays. Status - " + resp, resp.equals(status200));
    }
// <---------------------- HOLIDAYS

// ----------------------> LOGOUT
    //POST Logout come back with error "CSRF Failed: CSRF token missing or incorrect."
// <---------------------- LOGOUT

    // ----------------------> PROJECTS
    @Test
    public void getProjectsTest() {
        String resp = getProjects(cookies);
        assertTrue("Some error in getting projects. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getProjectsTest2() { //---------------------------------------------------------------------------------------------
        String resp = getProjects(cookies, "id", "d", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.equals(status200));

    }
    @Test
    public void getProjectsTest3() { //---------------------------------------------------------------------------------------------
        String resp = getProjects(cookies, "id", "", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getProjectsTest4() { //---------------------------------------------------------------------------------------------
        String  resp = getProjects(cookies, "", "d", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getProjectsTest5() { //---------------------------------------------------------------------------------------------
        String resp = getProjects(cookies, "", "", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getProjectsByIDTest() {
        String resp = getProjectsById(cookies, 1);
        assertTrue("Some error in getting projects by ID. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getProjectsByIDTest2() {
        String resp = getProjectsById(cookies, 1, "id", "", true);
        assertTrue("Some error in getting projects by ID. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getProjectsByIDTest3() {
        String resp = getProjectsById(cookies, 1, "", "is", true);
        assertTrue("Some error in getting projects by ID. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getProjectsByYearMonthDayTest() {
        String resp = getProjectsByYearMonthDay(cookies, 2018, 07, 07);
        assertTrue("Some error in getting projects by year month and day. Status - " + resp, resp.equals(status200));
    }
// <---------------------- PROJECTS

    // ----------------------> REPORTS
    @Test
    public void getReportsTest() {
        String resp = getReports(cookies);
        assertTrue("Some error in getting reports. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void postReportsByIDTest() {
        String resp = postReportsByID(cookies, ID, 2018, 7);
        assertTrue("Some error in getting reports by id. Status - " + resp, resp.equals(status200));
    }

// <---------------------- REPORTS


    // ----------------------> TIME_SHEET
    @Test
    public void getTimeSheetTest() {
        String resp = getTimeSheet(cookies, 2018, 8);
        assertTrue("Some error in getting time sheet. Status - " + resp, resp.equals(status200));
    }


// <---------------------- TIME_SHEET

    // ----------------------> TIME_TRACKER
    @Test
    public void getTimeTrackerTest() {
        String resp = getTimeTracker(cookies, 2018, 8);
        assertTrue("Some error in getting time tracker. Status - " + resp, resp.equals(status200));
    }
// <---------------------- TIME_TRACKER

    // ----------------------> USERS
    @Test
    public void getUsersTest() {
        String resp = getUsers(cookies, "", "", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getUsersTest2() {
        String resp = getUsers(cookies, "", "uid", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getUsersTest3() {
        String resp = getUsers(cookies, "is", "uid", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.equals(status200));
    }
    @Test
    public void getUsersByIDTest() {
        String resp = getUsersByID(cookies, ID);
        assertTrue("Some error in getting users by ID. Status - " + resp, resp.equals(status200));
    }


// <---------------------- USERS

// ----------------------> USERSETTINGS

    @Test
    public void getUserSettingsTest() {
        String resp = getUserSettings(cookies);
        assertTrue("Some error in getting user settings. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getUserSettingsTimeZonesTest() {
        String resp = getUserSettingsTimeZones(cookies);
        assertTrue("Some error in getting time zones. Status - " + resp, resp.equals(status202));
    }

    @Test
    public void getUserSettingsByIDTest() {
        String resp = getUserSettingsByID(cookies, ID);
        assertTrue("Some error in getting current user settings. Status - " + resp, resp.equals(status200));
    }
// <---------------------- USERSETTINGS

    // ----------------------> VACATION
    @Test
    public void getVacationBalanceTest() {
        String resp = getVacationBalance(cookies);
        assertTrue("Some error in getting vacation balance. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getVacationBalanceByIDTest() {
        String resp = getVacationBalanceByID(cookies, ID);
        assertTrue("Some error in getting vacation balance by id. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getVacationBalanceByYearMonthTest() { //странная генерация url
        String resp = getVacationBalanceByYearMonth(cookies, 2018, 07);
        assertTrue("Some error in getting vacation balance by year, month. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getVacationBalanceByIDYearMonthTest() {
        String resp = getVacationBalanceByIDYearMonth(cookies, ID, 2018, 07);
        assertTrue("Some error in getting vacation balance by id, year and month. Status - " + resp, resp.equals(status200));
    }

// <---------------------- VACATION

    // ----------------------> WHOAMI
    @Test
    public void getWhoAmITest() {
        String resp = getWhoAmI(cookies);
        assertTrue("Some error in getting who am i. Status - " + resp, resp.equals(status200));
    }
// <---------------------- WHOAMI

    // ----------------------> WORKLOAD
    //only superuser
    @Test
    public void getWorkloadAssignmentsTests() {
        String resp = getWorkloadAssignments(cookies, 2018, 8);
        assertTrue("Some error in getting workload assignments. Status - " + resp, resp.equals(status200));
    }

    @Test
    public void getWorkloadTimeTrackerTest() {
        String resp = getWorkloadTimeTracker(cookies, ID, 2018, 8);
        System.out.println(resp);
        assertTrue("Some error in getting workload time tracker. Status - " + resp, resp.equals(status200));
    }
// <---------------------- WORKLOAD
}
