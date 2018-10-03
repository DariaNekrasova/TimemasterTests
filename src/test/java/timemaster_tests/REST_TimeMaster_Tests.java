package timemaster_tests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.*;
import timemaster.TimeMasterController;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class REST_TimeMaster_Tests extends TimeMasterController {


    private  Map<String, String> cookies;


    @BeforeClass
    public static  void beforeClass() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    static int ID = another_ID;
    Response resp;

    @Before
    public  void before() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
        cookies = getCookie(another_userName, another_userPassword);
        // cookies = getCookie(super_userName, super_userPassword);
    }

    @After
    public  void after() {
        logout(cookies);
    }

    @Test
    public void loginPost() {
        assertTrue("OUPS, user don't get cookies ", !cookies.isEmpty());
    }

    // ----------------------> BALANCE
    @Test
    public void getBalanceCurrentTest() {
        resp = getBalanceCurrent(cookies);
        System.out.println(cookies);
        assertTrue("Current balance is empty. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getBalanceByIdTest() {
         resp = getBalanceById(cookies, ID);
        assertTrue("Current balance is empty. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getBalanceYearTest() {
         resp = getBalanceYear(cookies, 2018);
        assertTrue("Some error in getting balance by year. Status - " + resp,resp.statusCode() == 200);
    }

    @Test
    public void getBalanceYearMonthTest() {
         resp = getBalanceYearMonth(cookies, 2018, 8);
        assertTrue("Some error in getting balance by year and month. Status - " + resp, resp.statusCode() == 200);
    }


    //add POSTs requests
    //for what need getBalanceYearMonth
// <---------------------- BALANCE

    // ----------------------> DAY_CODES
    @Test
    public void getDayCodesCurrentTest() {
         resp = getDayCodesCurrent(cookies);
        assertTrue("Some error in getting day codes. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- DAY_CODES

    // ----------------------> DAYS
    @Test
    public void getCurrentDaysByYearAndMonthTest() {
         resp = getCurrentDaysByYearAndMonth(cookies, 2018, 7);
        assertTrue("Some error in getting days by year and month. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getCurrentDaysByYearAndMonthAndDayTest() {
         resp = getCurrentDaysByYearAndMonthAndDay(cookies, 2018, 7, 3);
        assertTrue("Some error in getting days by year, month and day. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getValidateDaysByYearAndMonthTest() {
         resp = getValidateDaysByYearAndMonth(cookies, 2018, 7);
        assertTrue("Some error in getting validate days by year and month. Status - " + resp,resp.statusCode() == 200);
    }

    @Test
    public void getDaysOfUserByYearMonthTest() {
         resp = getDaysOfUserByYearMonth(cookies, 163, 2018, 7);
        assertTrue("Some error in getting days of user by user id, year and month. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getDaysOfUserByYearMonthDayTest() {
         resp = getDaysOfUserByYearMonthDay(cookies, ID, 2018, 6, 5);
        assertTrue("Some error in getting days of user by user id, year and month. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void putDaysTest() {
         resp = putDays(cookies, "2018-08-07", "Day from REST Test");
        assertTrue("Some error in putting Days. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void putDaysByIDTest() { //CSRF
         resp = putDaysByID(cookies, ID, "2018-08-07", "Day from REST Test");
        assertTrue("Some error in putting Days by ID. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void postSetBreakTimeTest() { //CSRF
         resp = postSetBreakTime(cookies, 100);
        assertTrue("Some error in posting BreakTime. Status - " + resp, resp.statusCode() == 200);
    }


// <---------------------- DAYS

    // ----------------------> HOLIDAYS
    @Test
    public void getHolidaysTest() {
         resp = getHolidays(cookies, 2018, 9);
        assertTrue("Some error in getting holidays. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- HOLIDAYS

// ----------------------> LOGOUT
    //POST Logout come back with error "CSRF Failed: CSRF token missing or incorrect."
// <---------------------- LOGOUT

    // ----------------------> PROJECTS
    @Test
    public void getProjectsTest() {
         resp = getProjects(cookies);
        assertTrue("Some error in getting projects. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getProjectsTest2() { //---------------------------------------------------------------------------------------------
         resp = getProjects(cookies, "id", "d", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.statusCode() == 200);

    }
    @Test
    public void getProjectsTest3() { //---------------------------------------------------------------------------------------------
         resp = getProjects(cookies, "id", "", true);
        assertTrue("Some error in getting projects. Status - " + resp,resp.statusCode() == 200);
    }
    @Test
    public void getProjectsTest4() { //---------------------------------------------------------------------------------------------
          resp = getProjects(cookies, "", "d", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.statusCode() == 200);
    }
    @Test
    public void getProjectsTest5() { //---------------------------------------------------------------------------------------------
         resp = getProjects(cookies, "", "", true);
        assertTrue("Some error in getting projects. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getProjectsByIDTest() {
         resp = getProjectsById(cookies, 1);
        assertTrue("Some error in getting projects by ID. Status - " + resp,resp.statusCode() == 200);
    }
    @Test
    public void getProjectsByIDTest2() {
         resp = getProjectsById(cookies, 1, "id", "", true);
        assertTrue("Some error in getting projects by ID. Status - " + resp, resp.statusCode() == 200);
    }
    @Test
    public void getProjectsByIDTest3() {
         resp = getProjectsById(cookies, 1, "", "is", true);
        assertTrue("Some error in getting projects by ID. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getProjectsByYearMonthDayTest() {
         resp = getProjectsByYearMonthDay(cookies, 2018, 07, 07);
        assertTrue("Some error in getting projects by year month and day. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- PROJECTS

    // ----------------------> REPORTS
    @Test
    public void getReportsTest() {
         resp = getReports(cookies);
        assertTrue("Some error in getting reports. Status - " + resp,resp.statusCode() == 200);
    }

    @Test
    public void postReportsByIDTest() {
         resp = postReportsByID(cookies, ID, 2018, 7);
        assertTrue("Some error in getting reports by id. Status - " + resp, resp.statusCode() == 200);
    }

// <---------------------- REPORTS


    // ----------------------> TIME_SHEET
    @Test
    public void getTimeSheetTest() {
         resp = getTimeSheet(cookies, 2018, 8);
        assertTrue("Some error in getting time sheet. Status - " + resp, resp.statusCode() == 200);
    }


// <---------------------- TIME_SHEET

    // ----------------------> TIME_TRACKER
    @Test
    public void getTimeTrackerTest() {
         resp = getTimeTracker(cookies, 2018, 8);
        assertTrue("Some error in getting time tracker. Status - " + resp,resp.statusCode() == 200);
    }
// <---------------------- TIME_TRACKER

    // ----------------------> USERS
    @Test
    public void getUsersTest() {
         resp = getUsers(cookies, "", "", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.statusCode() == 200);
    }
    @Test
    public void getUsersTest2() {
         resp = getUsers(cookies, "", "uid", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getUsersTest3() {
         resp = getUsers(cookies, "is", "uid", false);
        assertTrue("Some error in getting users. Status - " + resp, resp.statusCode() == 200);
    }
    @Test
    public void getUsersByIDTest() {
         resp = getUsersByID(cookies, ID);
        assertTrue("Some error in getting users by ID. Status - " + resp,resp.statusCode() == 200);
    }


// <---------------------- USERS

// ----------------------> USERSETTINGS

    @Test
    public void getUserSettingsTest() {
         resp = getUserSettings(cookies);
        assertTrue("Some error in getting user settings. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getUserSettingsTimeZonesTest() {
         resp = getUserSettingsTimeZones(cookies);
        assertTrue("Some error in getting time zones. Status - " + resp,resp.statusCode() == 202);
    }

    @Test
    public void getUserSettingsByIDTest() {
         resp = getUserSettingsByID(cookies, ID);
        assertTrue("Some error in getting current user settings. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- USERSETTINGS

    // ----------------------> VACATION
    @Test
    public void getVacationBalanceTest() {
         resp = getVacationBalance(cookies);
        assertTrue("Some error in getting vacation balance. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getVacationBalanceByIDTest() {
         resp = getVacationBalanceByID(cookies, ID);
        assertTrue("Some error in getting vacation balance by id. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getVacationBalanceByYearMonthTest() { //странная генерация url
         resp = getVacationBalanceByYearMonth(cookies, 2018, 07);
        assertTrue("Some error in getting vacation balance by year, month. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getVacationBalanceByIDYearMonthTest() {
         resp = getVacationBalanceByIDYearMonth(cookies, ID, 2018, 07);
        assertTrue("Some error in getting vacation balance by id, year and month. Status - " + resp, resp.statusCode() == 200);
    }

// <---------------------- VACATION

    // ----------------------> WHOAMI
    @Test
    public void getWhoAmITest() {
         resp = getWhoAmI(cookies);
        assertTrue("Some error in getting who am i. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- WHOAMI

    // ----------------------> WORKLOAD
    //only superuser
    @Test
    public void getWorkloadAssignmentsTests() {
         resp = getWorkloadAssignments(cookies, 2018, 8);
        assertTrue("Some error in getting workload assignments. Status - " + resp, resp.statusCode() == 200);
    }

    @Test
    public void getWorkloadTimeTrackerTest() {
         resp = getWorkloadTimeTracker(cookies, ID, 2018, 8);
        System.out.println(resp);
        assertTrue("Some error in getting workload time tracker. Status - " + resp, resp.statusCode() == 200);
    }
// <---------------------- WORKLOAD
}
