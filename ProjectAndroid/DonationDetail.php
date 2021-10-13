<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";
// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

 $response = array();

 if(isset($_POST['donationDate'])){
     $username = $_POST['username'];
     $donationDate = $_POST['donationDate'];

     $statement = $conn->prepare("INSERT INTO donation_details VALUES(NULL, ?,?);");
     $statement->bind_param("ss",$username,$donationDate);
     if($statement->execute() == TRUE)
     {
         $response['error'] = false;
         $response['message'] = "Date added successfully!";
     }
     else
     {
         $response['error'] = true;
         $response['message'] = "failed\n ".$conn->error;
     }
 }

else{
     $response['error'] = true;
     $response['message'] = "Please fill out all the fields..";
 }

 echo json_encode($response);
 ?>
