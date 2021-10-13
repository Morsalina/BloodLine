<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "projectandroid";


$conn = new mysqli($servername, $username, $password, $dbname);

$response = array();
$info = array();

$sql = "select fullname,contact,postCreationTime,writtenPost,bloodAmount,hospitalName from post_shared where bloodStatus= 'Unmanaged' order by postCreationTime desc"  ;

$result = mysqli_query($conn, $sql);

while($row = mysqli_fetch_assoc($result)){
    $info['fullname'] = $row['fullname'];
    $info['contact'] = $row['contact'];
    $info['postCreationTime'] = $row['postCreationTime'];
    $info['writtenPost'] = $row['writtenPost'];
    $info['bloodAmount'] = $row['bloodAmount'];
    $info['hospitalName'] = $row['hospitalName'];

    array_push($response, $info);
  }

echo json_encode($response);

?>
