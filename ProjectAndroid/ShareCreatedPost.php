<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";
$conn = new mysqli($servername, $username, $password, $dbname);

$response = array();

 if(isset($_POST['username']) || isset($_POST['writtenPost']) || isset($_POST['postCreationTime']) || isset($_POST['contact']) || isset($_POST['fullname']) || isset($_POST['bloodAmount']) || isset($_POST['hospitalName'])){

   $fullname = $_POST['fullname'];
   $username = $_POST['username'];
   $postCreationTime = $_POST['postCreationTime'];
   $writtenPost  = $_POST['writtenPost'];
   $contact = $_POST['contact'];
   $bloodAmount = $_POST['bloodAmount'];
   $hospitalName = $_POST['hospitalName'];


   $statement = $conn->prepare("INSERT INTO post_shared VALUES(NULL,?,?,?,?,?,?,?,?);");
   $bloodStatus = "Unmanaged";
   $statement->bind_param("ssssssss",$username,$fullname,$contact,$writtenPost,$postCreationTime,$bloodAmount,$hospitalName, $bloodStatus);
   if($statement->execute() == TRUE)
   {
       $response['error'] = false;
       $response['message'] = "Post shared successfully!";
   }
   else
   {
       $response['error'] = true;
       $response['message'] = "Something went wrong\n ".$conn->error;
   }
}
else{
     $response['error'] = true;
     $response['message'] = "Please fill out all the fields..";
 }

 echo json_encode($response);
 ?>
