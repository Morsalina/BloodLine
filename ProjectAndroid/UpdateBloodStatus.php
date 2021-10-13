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
if(isset($_POST['username']) OR isset($_POST['postCreationTime'])){
 $username = $_POST['username'];
 $postCreationTime = $_POST['postCreationTime'];

 $sql = "UPDATE post_shared SET bloodStatus ='Managed' WHERE username = '" . $username. "' AND postCreationTime = '" . $postCreationTime. "' ";

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
