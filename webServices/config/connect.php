<?php
include "bdConfig.php";

try{
    $connection = mysqli_connect(DB_HOST, DB_USER, DB_PASS);
}catch(Exception $e){
    echo "Failed to connect to DB server.";
}

try{
    $resultado = mysqli_select_db($connection, DB_NAME);
}catch (Exception $e){
    echo "There was a problem connecting to the database.";
}

?>