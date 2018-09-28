package timemaster;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import common.Constants_TimeMaster;
import net.minidev.json.JSONObject;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.*;

public class TimeMasterController extends Constants_TimeMaster {


    static{ RestAssured.baseURI = "http://timemaster-dev2.sidenis.io/";}
    protected final static RequestSpecification rs = given().basePath("/api/").contentType(ContentType.JSON);


    public static Map<String, String> getCookie(String login, String password) {
        return given()
                .params("username", login, "password", password)
                .auth().preemptive().basic(login, password)
                .when()
                .post("api/login/")
                .then().extract().cookies();

    }

    public static String logout(Map<String, String> cookies){
        return given()
                .cookie(cookies.get("csrftoken"))
                .when()
                .post("api/logout/")
                .then()
             //   .log().all()
                .statusCode(202)
                .extract().statusLine();
    }
    public String getBalanceCurrent(Map<String, String> cookies){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/current/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getBalanceById(Map<String, String> cookies, int id){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/current/" + id)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getBalanceYear(Map<String, String> cookies, int year){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/" + year)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getBalanceYearMonth(Map<String, String> cookies, int year, int month){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/" + year + "/" + month)
                .then()
                .log().all()
                .extract().statusLine();
    }


    public String getDayCodesCurrent(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("day_codes/current_user")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getWhoAmI(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("whoami")
                .then()
                .log().all()
                .extract().statusLine();
    }
    public String getHolidays(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("holidays/current_user/" + year + "/" + month)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getReports(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("reports/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getTimeSheet(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("time_sheet/days/current_user/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getTimeTracker(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("time_tracker/current_user/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getWorkloadAssignments(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("workload/assignments/total/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getWorkloadTimeTracker(Map<String, String> cookies, int id, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("workload/time_tracker/" + id + "/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getCurrentDaysByYearAndMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/current_user/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }
    public String getCurrentDaysByYearAndMonthAndDay(Map<String, String> cookies, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("days/current_user/" + year + "/" + month + "/" + day + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getValidateDaysByYearAndMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/validate/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getDaysOfUserByYearMonth(Map<String, String> cookies, int id, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/" + id + "/" + year + "/" + month + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getDaysOfUserByYearMonthDay(Map<String, String> cookies, int id, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("days/" + id + "/" + year + "/" + month + "/" + day + "/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String putDays(Map<String, String> cookies, String date, String comment)
    {
        JSONObject requestBody = new JSONObject();
        requestBody.put("code_id", 0);
        requestBody.put("assignments", "[]");
        requestBody.put("wdate", date);
        requestBody.put("start_time", 0);
        requestBody.put("stop_time", 0);
        requestBody.put("break_time", 0);
        requestBody.put("comment", comment);


        return rs
                .cookies(cookies)
                .when()
                .body(requestBody)
                .put("days/current_user")
                .then()
                .log().all()
                .extract().statusLine();

    }
    public String putDaysByID(Map<String, String> cookies, int ID, String date, String comment)
    {
        JSONObject requestBody = new JSONObject();
        requestBody.put("code_id", null);
        requestBody.put("assignments", "[]");
        requestBody.put("wdate", date);
        requestBody.put("start_time", 36002);
        requestBody.put("stop_time", 68897);
        requestBody.put("break_time", 0);
        requestBody.put("comment", comment);


        return rs
                .cookie("csrftoken", cookies.get("csrftoken"))
                .body(requestBody)
                .when()
                .put("days/" + ID + "/")
                .then()
                .log().all()
                .extract().statusLine();

    }

    public String postSetBreakTime(Map<String, String> cookies, int break_time) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("break_time", break_time );

        return rs.
                cookies(cookies)
                .body(requestBody)
                .when()
                .post("http://timemaster-dev2.sidenis.io/api/days/set_time/")
                .then()
                .log().all()
                .extract().statusLine();


    }


    public String postReportsByID(Map<String, String> cookies, int ID, int year, int month){
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_ids", "[" + ID + "]");

        System.out.println(requestBody);

        return rs
                .cookies(cookies)
                .body(requestBody)
                .when()
                .post("/reports/" + year + "/" + month + "/generate_work_hour_report")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getVacationBalance(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/balance")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getVacationBalanceByID(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/balance/" + ID )
                .then()
                .log().all()
                .extract().statusLine();
    }
    public String getVacationBalanceByYearMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/current_user/" + year + "/" + month)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getVacationBalanceByIDYearMonth(Map<String, String> cookies, int ID, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/" + ID + "/" + year + "/" + month)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getUserSettings (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getUserSettingsCurrent (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/current_user")
                .then()
                .log().all()
                .extract().statusLine();
    }
    public String getUserSettingsTimeZones (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/time_zones")
                .then()
                .log().all()
                .extract().statusLine();
    }
    public String getUserSettingsByID(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/" + ID)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getProjects(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/")
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getProjects(Map<String, String> cookies, String ordering, String search, boolean valid_only){
//-----------------------------------------------------------------------------------------------------------------------------------
        String request = "projects/?";

        if(!ordering.isEmpty())
        {
            request += "ordering=";
            request += ordering;
        }
        if(!search.isEmpty() & request.equals("projects/?"))
        {
            request += "search=";
            request += search;
        }
        else if (!search.isEmpty() & !request.equals("projects/?"))
        {
            request += "&";
            request += "search=";
            request += search;
        }
        if(valid_only & request.equals("projects/?"))
        {
            request += "valid_only=";
            request += valid_only;
        }
        else if (valid_only & !request.equals("projects/?"))
        {
            request += "&";
            request += "valid_only=";
            request += valid_only;
        }

        System.out.println(request);
        if(request.equals("projects/?"))
        return rs
                .cookies(cookies)
                .when()
                .get("projects/")
                .then()
                .log().all()
                .extract().statusLine();
        else
            return rs
                    .cookies(cookies)
                    .when()
                    .get(request)
                    .then()
                    .log().all()
                    .extract().statusLine();
    }



    public String getProjectsById(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/" + ID)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getProjectsById(Map<String, String> cookies, int ID, String ordering, String search, boolean valid_only){

        String request = "/?";

        if(!ordering.isEmpty())
        {
            request += "ordering=";
            request += ordering;
        }
        if(!search.isEmpty() & request.equals("projects/?"))
        {
            request += "search=";
            request += search;
        }
        else if (!search.isEmpty() & !request.equals("/?"))
        {
            request += "&";
            request += "search=";
            request += search;
        }
        if(valid_only & request.equals("/?"))
        {
            request += "valid_only=";
            request += valid_only;
        }
        else if (valid_only & !request.equals("projects/?"))
        {
            request += "&";
            request += "valid_only=";
            request += valid_only;
        }
        System.out.println(request);
        if(request.equals("/?"))
        return rs
                .cookies(cookies)
                .when()
                .get("projects/" + ID)
                .then()
                .log().all()
                .extract().statusLine();
        else
            return rs
                    .cookies(cookies)
                    .when()
                    .get("projects/" + ID + request)
                    .then()
                    .log().all()
                    .extract().statusLine();
    }



    public String getProjectsByYearMonthDay(Map<String, String> cookies, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/" + year + "/" + month + "/" + day)
                .then()
                .log().all()
                .extract().statusLine();
    }

    public String getUsers(Map<String, String> cookies, String search, String ordering, boolean is_active){


        return rs
                .cookies(cookies)
                .param("search", search)
                .param("ordering",ordering)
                .param("is_active",is_active)
                .when()
                .get("users/")
                .then()
                .log().all()
                .extract().statusLine();
    }


    public String getUsersByID(Map<String, String> cookies, int ID){
        return rs
                .cookie(cookies.get("csrftoken"))
                .when()
                .get("users/" + ID)
                .then()
                .log().all()
                .extract().statusLine();
    }

}
