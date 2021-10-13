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

if(isset($_POST['username'])){

   $username = $_POST['username'];

  $sql = "select fullname,contact,writtenPost,postCreationTime,bloodAmount,hospitalName,bloodStatus from post_shared where username = '" . $username. "'
  order by postCreationTime desc" ;
  $result = mysqli_query($conn, $sql);

  while($row = mysqli_fetch_assoc($result)){

    $info['fullname'] = $row['fullname'];
    $info['contact'] = $row['contact'];
    $info['writtenPost'] = $row['writtenPost'];
    $info['postCreationTime'] = $row['postCreationTime'];
    $info['bloodAmount'] = $row['bloodAmount'];
    $info['hospitalName'] = $row['hospitalName'];
    $info['bloodStatus'] = $row['bloodStatus'];

    array_push($response, $info);
  }
}

echo json_encode($response);

?>
