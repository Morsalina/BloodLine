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

  $sql = "select donationDate from donation_details where username = '" . $username. "'" ;
  $result = mysqli_query($conn, $sql);

  while($row = mysqli_fetch_assoc($result)){

    $info['donationDate'] = $row['donationDate'];

    array_push($response, $info);
  }
}

echo json_encode($response);

?>
