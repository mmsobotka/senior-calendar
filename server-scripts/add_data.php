<?php

$user = 'root';
$password = '';
$db_name = 'senior-calendar';
$table_name = 'data';

$COL1 = "TIMESTAMP";
$COL2 = "USER_ID";
$COL3 = 'TEMPERATURE';
$COL4 = 'SUGAR_LEVEL';
$COL5 = 'WEIGHT';
$COL6 = 'BLOOD_PRESSURE';
$COL7 = 'HEART_RATE';
$COL8 = 'SATURATION';

$conn = new mysqli('localhost', $user, $password, $db_name);
if (!$conn) {
    echo "Cannot connect to the database";
    die("");
} else {
  echo "success";
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {

  
  $date_time = $_REQUEST[$COL1];
  $user_id = $_REQUEST[$COL2];
  $temperature = $_REQUEST[$COL3];
  $sugar_level = $_REQUEST[$COL4];
  $weight = $_REQUEST[$COL5];
  $blood_pressure = $_REQUEST[$COL6];
  $heart_rate = $_REQUEST[$COL7];
  $saturation = $_REQUEST[$COL8];

  $QUERY = "INSERT INTO $table_name ($COL1, $COL2, $COL3, $COL4, $COL5, $COL6, $COL7, $COL8) VALUES ('$date_time', '$user_id', $temperature, $sugar_level, $weight, $blood_pressure, $heart_rate, $saturation)";
  if ($conn->query($QUERY) === TRUE) {
        echo "New record added successfully";
  } else {
        echo 'Cannot add to the database';
  }
}

mysqli_close($conn);
?>