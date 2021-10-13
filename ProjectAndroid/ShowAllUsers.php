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

  $sql = "select fullname,contact,blood,location,status from users,user_status where users.username = user_status.username" ;

  $result = mysqli_query($conn, $sql);

  while($row = mysqli_fetch_assoc($result)){

    $info['fullname'] = $row['fullname'];
    $info['contact'] = $row['contact'];
    $info['blood'] = $row['blood'];
    $info['location'] = $row['location'];
    $info['status'] = $row['status'];

    array_push($response, $info);
  }

echo json_encode($response);

?>
