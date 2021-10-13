<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";
$conn = new mysqli($servername, $username, $password, $dbname);

 $response = array();

 if(isset($_POST['status'])){

     $username = $_POST['username'];
     $status = $_POST['status'];

     $usernamequery ="SELECT * from user_status WHERE username= '" . $username . "'";
     $query = mysqli_query($conn, $usernamequery);
     // $query = $usernamequery->execute();
     $usernamecount = mysqli_num_rows($query);
     if($usernamecount>0){

       $response['message'] = "User already exists";
       $sql = "UPDATE user_status SET status = '" . $status. "' WHERE username= '" . $username . "'";

       $result = mysqli_query($conn, $sql);

       if($result){
         $response['message'] = "Updated Successfully";
       }
       else{
         $response['message'] = "Could not be updated.";
        }

      }

     else{
     $statement = $conn->prepare("INSERT INTO user_status VALUES(NULL, ?,?);");
     $statement->bind_param("ss",$username,$status);
           if($statement->execute() == TRUE){
                 $response['error'] = false;
                 $response['message'] = "Status Updated Successfully!";
           } else{
                 $response['error'] = true;
                 $response['message'] = "failed\n ".$conn->error;
           }
       }
    }
  else{
     $response['error'] = true;
     $response['message'] = "Please select an option..";
   }

 echo json_encode($response);
 ?>
