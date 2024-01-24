<?php
include "./config/connect.php";

header("Content-Type: application/json");

$sql = "SELECT * FROM usuarios";
$result = $connection->query($sql);

if ($result->num_rows > 0) {
$rows = $result->fetch_all(MYSQLI_ASSOC);

echo json_encode($rows);
} else {
echo json_encode(["message" => "No users found."]);
}

$connection->close();

?>
