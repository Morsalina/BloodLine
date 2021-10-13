<?php

$servername = "localhost";

// for testing the user name is root.
$username = "root";

// the password for testing is "blank"
$password = "";

// below is the name for our
// database which we have added.
$dbname = "projectandroid";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

 // an array to display response
 $response = array();
 // on below line we are checking if the body provided by user contains
 // this keys as course name,course description and course duration

 if(isset($_POST['fullname']) && isset($_POST['username']) && isset($_POST['password']) &&
    isset($_POST['contact']) && isset($_POST['blood']) && isset($_POST['location'])){
     // if above three parameters are present then we are extravting values
     // from it and storing it in new variables.
     $fullname = $_POST['fullname'];
     $username = $_POST['username'];
     $password = $_POST['password'];
     $contact = $_POST['contact'];
     $blood = $_POST['blood'];
     $location = $_POST['location'];

     $usernamequery ="SELECT * from users WHERE username= '" . $username . "'";
     // select * from table where name = 'kowmi'
     // $usernamequery->bind_param("s",$username);
     $query = mysqli_query($conn, $usernamequery);
     // $query = $usernamequery->execute();
     $usernamecount = mysqli_num_rows($query);
     if($usernamecount>0){

       $response['error'] = true;
       $response['message'] = "User already exists!\n Username must be unique";

      }
     else{
     $statement = $conn->prepare("INSERT INTO users VALUES(NULL, ?,?,?,?,?,?);");
     $statement->bind_param("ssssss",$fullname,$username,$password,$contact,$blood,$location);


   // on below line we are checking if our sql query is executed succesfully.
   if($statement->execute() == TRUE){

        $statement = $conn->prepare("INSERT INTO user_status VALUES(NULL, ?, ?);");
        $status = "Not Available";
        $statement->bind_param("ss",$username,$status);
          $statement->execute();
         $response['error'] = false;
         $response['message'] = "User registered successfully!";
     } else{
         // if we get any error we are passing error to our object.
         $response['error'] = true;
         $response['message'] = "failed\n ".$conn->error;
     }
 }
}
else{
     // this msethod is called when user
     // donot enter sufficient parameters.
     $response['error'] = true;
     $response['message'] = "Please fill out all the fields..";
 }

 // at last we are prinintg our response which we get.
 echo json_encode($response);
 ?>
