package com.example.senior_calendar_app;

public interface ProjectVariables {
    String COL1_NAME = "TIMESTAMP";
    String COL2_NAME = "USER_ID";
    String COL3_NAME = "TEMPERATURE";
    String COL4_NAME = "WEIGHT";
    String COL5_NAME = "BLOOD_PRESSURE";
    String COL6_NAME = "SUGAR_LEVEL";
    String IPv4 = "192.168.0.229:80";
    String URL = "http://" + IPv4 + "/senior-calendar/add_data.php";
    String GetDataURL = "http://" + IPv4 + "/senior-calendar/get_data.php";
}
