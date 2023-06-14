<?php
$user = 'root';
$password = '';
$db_name = 'senior-calendar';
$table_name = 'users_data';

$conn = new mysqli('localhost', $user, $password, $db_name);
if (!$conn) {
    echo "Cannot connect to database";
    die("");
}
if($_SERVER["REQUEST_METHOD"] == "POST"){
    $column_name = $_REQUEST["COL_NAME"];
    $n_values = $_REQUEST["N_VALUES"];
    $QUERY = "SELECT $column_name FROM $table_name LIMIT $n_values";
    $result = $conn->query($QUERY);
    $response = array();
    if($result->num_rows > 0) {
        while($row = $result->fetch_assoc()){
            array_push($response, $row[$column_name]);
        }
        echo json_encode(
            array(
                "data" => $response
                )
        );
    } else {
        echo json_encode(
            array(
                "data" => $response
                )
        );
    }
}
?>