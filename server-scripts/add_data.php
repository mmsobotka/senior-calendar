<?php

$user = 'root';
$password = '';
$db_name = 'senior-calendar';
$table_name = 'users_data';

$COL1 = "TIMESTAMP";
$COL2 = "USER_ID";
$COL3 = 'TEMPERATURE';
$COL4 = 'WEIGHT';
$COL5 = 'BLOOD_PRESSURE';
$COL6 = 'SUGAR_LEVEL';

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
  $weight =  $_REQUEST[$COL4];
  $blood_pressure = $_REQUEST[$COL5];
  $sugar_level = $_REQUEST[$COL6];

  $QUERY = "INSERT INTO $table_name ($COL1, $COL2, $COL3, $COL4, $COL5, $COL6) VALUES ('$date_time', '$user_id', $temperature, $weight, $blood_pressure, $sugar_level)";
  if ($conn->query($QUERY) === TRUE) {
        echo "New record added successfully";
  } else {
        echo 'Cannot add to the database';
  }
}

mysqli_close($conn);
?>

