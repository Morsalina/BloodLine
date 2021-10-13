<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";


$conn = new mysqli($servername, $username, $password, $dbname);


 $response = array();


 if(isset($_POST['username']) and isset($_POST['password']) ){

     $username = $_POST['username'];
     $password = $_POST['password'];
     $stmt = $conn->prepare("SELECT id, fullname,username, contact, blood, location FROM users WHERE username = ? and password = ?");
     $stmt->bind_param("ss",$username,$password);
     $result = $stmt->execute();
   if($result == TRUE){
         $response['error'] = false;
         $response['message'] = "Login Successfull";
         $stmt->store_result();

         $stmt->bind_result($id, $fullname,$username,$contact,$blood,$location);

         $stmt->fetch();

         $response['id'] = $id;
         $response['fullname'] = $fullname;
         $response['username'] = $username;

         $response['contact'] = $contact;
         $response['blood'] = $blood;
         $response['location'] = $location;
     } else{

         $response['error'] = true;
         $response['message'] = "Username or password doesn't match";
     }
 } else{

      $response['error'] = true;
      $response['message'] = "All fields are required.";
 }

 echo json_encode($response);
?>
