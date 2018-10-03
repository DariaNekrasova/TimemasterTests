package timemaster;


import common.Constants_TimeMaster;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class TimeMasterController extends Constants_TimeMaster {


    static{ RestAssured.baseURI = "http://timemaster-dev2.sidenis.io/";}
    protected final static RequestSpecification rs = given().basePath("api/").log().uri().contentType(ContentType.JSON);


    public static Map<String, String> getCookie(String login, String password) {
        return given()
                .params("username", login, "password", password)
                .auth().preemptive().basic(login, password)
                .when()
                .post("api/login/")
                .then().extract().cookies();

    }

    public static String logout(Map<String, String> cookies){
        System.out.println("---------------------------------------------->LOGOUT<-----------------------------------------------------------");
        return given()
                .header("X-CSRFToken", cookies.get("csrftoken"))
                .cookies(cookies)
                .when()
                .post("api/logout/")
                .then()
             //   .log().all()
                .statusCode(202)
                .extract().statusLine();
    }
    public Response getBalanceCurrent(Map<String, String> cookies){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/current/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getBalanceById(Map<String, String> cookies, int id){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/current/" + id)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getBalanceYear(Map<String, String> cookies, int year){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/" + year)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getBalanceYearMonth(Map<String, String> cookies, int year, int month){
        return  rs
                .cookies(cookies)
                .when()
                .get("balance/" + year + "/" + month)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }


    public Response getDayCodesCurrent(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("day_codes/current_user")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getWhoAmI(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("whoami")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }
    public Response getHolidays(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("holidays/current_user/" + year + "/" + month)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getReports(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("reports/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getTimeSheet(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("time_sheet/days/current_user/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getTimeTracker(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("time_tracker/current_user/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getWorkloadAssignments(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("workload/assignments/total/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getWorkloadTimeTracker(Map<String, String> cookies, int id, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("workload/time_tracker/" + id + "/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getCurrentDaysByYearAndMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/current_user/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }
    public Response getCurrentDaysByYearAndMonthAndDay(Map<String, String> cookies, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("days/current_user/" + year + "/" + month + "/" + day + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getValidateDaysByYearAndMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/validate/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getDaysOfUserByYearMonth(Map<String, String> cookies, int id, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("days/" + id + "/" + year + "/" + month + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getDaysOfUserByYearMonthDay(Map<String, String> cookies, int id, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("days/" + id + "/" + year + "/" + month + "/" + day + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response putDays(Map<String, String> cookies, String date, String comment)
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
                .header("X-CSRFToken", cookies.get("csrftoken"))
                .cookies(cookies)
                .when()
                .body(requestBody)
                .put("days/current_user")
                .then()
                .log().all().statusCode(200)
                .extract().response();

    }
    public Response putDaysByID(Map<String, String> cookies, int ID, String date, String comment)
    {
        JSONObject requestBody = new JSONObject();
        requestBody.put("code_id", null);
        requestBody.put("assignments", null);
        requestBody.put("wdate", date);
        requestBody.put("start_time", 36002);
        requestBody.put("stop_time", 68897);
        requestBody.put("break_time", 0);
        requestBody.put("comment", comment);


        return rs
                .header("X-CSRFToken", cookies.get("csrftoken"))
                .cookies(cookies)
                .body(requestBody)
                .when()
                .put("days/" + ID + "/")
                .then()
                .log().all().statusCode(200)
                .extract().response();

    }

    public Response postSetBreakTime(Map<String, String> cookies, int break_time) {

        JSONObject requestBody = new JSONObject();
        requestBody.put("break_time", break_time );

        return given().baseUri("http://timemaster-dev2.sidenis.io/api/days/set_time/").contentType(ContentType.JSON)
                .header("X-CSRFtoken", cookies.get("csrftoken"))
                .cookies(cookies)
                .body(requestBody)
                .log().all()
                .when()
                .post()
                .then()
                .log().all().statusCode(200)
                .extract().response();


    }


    public Response postReportsByID(Map<String, String> cookies, int ID, int year, int month){
        JSONObject requestBody = new JSONObject();
        requestBody.put("user_ids", "[" + ID + "]");

        System.out.println(requestBody);

        return given().baseUri("http://timemaster-dev2.sidenis.io/api" + "/reports/" + year + "/" + month + "/generate_work_hour_report").contentType(ContentType.JSON)
                .cookies(cookies)
                .body(requestBody)
                .when()
                .post()
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getVacationBalance(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/balance")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getVacationBalanceByID(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/balance/" + ID )
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }
    public Response getVacationBalanceByYearMonth(Map<String, String> cookies, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/current_user/" + year + "/" + month)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getVacationBalanceByIDYearMonth(Map<String, String> cookies, int ID, int year, int month){
        return rs
                .cookies(cookies)
                .when()
                .get("vacation/" + ID + "/" + year + "/" + month)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getUserSettings (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getUserSettingsCurrent (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/current_user")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }
    public Response getUserSettingsTimeZones (Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/time_zones")
                .then()
                .log().all().statusCode(202)
                .extract().response();
    }
    public Response getUserSettingsByID(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("usersettings/" + ID)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getProjects(Map<String, String> cookies){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getProjects(Map<String, String> cookies, String ordering, String search, boolean valid_only){
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
                .log().all().statusCode(200)
                .extract().response();
        else
            return rs
                    .cookies(cookies)
                    .when()
                    .get(request)
                    .then()
                    .log().all().statusCode(200)
                    .extract().response();
    }



    public Response getProjectsById(Map<String, String> cookies, int ID){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/" + ID)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getProjectsById(Map<String, String> cookies, int ID, String ordering, String search, boolean valid_only){

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
                .log().all().statusCode(200)
                .extract().response();
        else
            return rs
                    .cookies(cookies)
                    .when()
                    .get("projects/" + ID + request)
                    .then()
                    .log().all().statusCode(200)
                    .extract().response();
    }



    public Response getProjectsByYearMonthDay(Map<String, String> cookies, int year, int month, int day){
        return rs
                .cookies(cookies)
                .when()
                .get("projects/" + year + "/" + month + "/" + day)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

    public Response getUsers(Map<String, String> cookies, String search, String ordering, boolean is_active){


        return rs
                .cookies(cookies)
                .param("search", search)
                .param("ordering",ordering)
                .param("is_active",is_active)
                .when()
                .get("users/")
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }


    public Response getUsersByID(Map<String, String> cookies, int ID){
        return rs
                .log().uri()
                .cookies(cookies)
                .when()
                .get("users/" + ID)
                .then()
                .log().all().statusCode(200)
                .extract().response();
    }

}
