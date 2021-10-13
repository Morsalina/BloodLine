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

if(isset($_POST['blood']) and isset($_POST['location'])){

   $blood = $_POST['blood'];
   $location = $_POST['location'];

  $sql = "select fullname,contact,blood,location,status from users, user_status where users.username = user_status.username and users.blood = '" . $blood. "' AND users.location ='" . $location. " '" ;
  $result = mysqli_query($conn, $sql);

  while($row = mysqli_fetch_assoc($result)){

    $info['fullname'] = $row['fullname'];
    $info['contact'] = $row['contact'];
    $info['blood'] = $row['blood'];
    $info['location'] = $row['location'];
    $info['status'] = $row['status'];

    array_push($response, $info);
  }

  // }else{
  //   $response['error'] = true;
  //   $response['message'] = "Something went wrong";
  // }


// }else{
//   $response['error'] = true;
//   $response['message'] = "Fillup the field.";
}

//$response['message']="Heeloo";

echo json_encode($response);

?>
