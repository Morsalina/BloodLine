<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";


$conn = new mysqli($servername, $username, $password, $dbname);

 // an array to display response
 $response = array();
 //array to store Info
 $info = array();
if(isset($_POST['fullname']) OR isset($_POST['contact']) OR isset($_POST['location'])){
 $username = $_POST['username'];
 $fullname = $_POST['fullname'];
 $contact = $_POST['contact'];
 $location = $_POST['location'];

 $sql = "UPDATE users SET fullname = '" . $fullname. "', contact = '" . $contact. "',location = '" . $location. "' WHERE username = '" . $username. "' ";

 $result = mysqli_query($conn, $sql);

 if($result){
   $response['message'] = "Updated Successfully";
   //echo "Updated Successfully";
 }
 else{
   $response['message'] = "Could not be updated.";
    //echo "Sorry !";
  }
}
else{
  //echo "all fields are required.";
   $response['message'] = "All fields are required.";
  }

   echo json_encode($response);

 ?>
