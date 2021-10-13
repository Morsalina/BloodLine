<?php

    require_once '../includes/DbOperations.php';
   $response = array();

   if($_SERVER['REQUEST_METHOD']=='POST'){

     if(isset($_POST['fullname']) && isset($_POST['username']) && isset($_POST['password']) &&
        isset($_POST['contact']) && isset($_POST['blood']) && isset($_POST['location'])){

      //create dbOperation class object

      $db = new DbOperations();

      if($db->createUser(
             $_POST['fullname'],
             $_POST['username'],
             $_POST['password'],
             $_POST['contact'],
             $_POST['blood'],
             $_POST['location'])){

               $response['error']= false;
               $response['message'] = "User registered successfully.";
             }
             else{
               $response['error']= true;
               $response['message'] = "Something went wrong, please try again.";
             }

     }

     else{
       $response['error'] = true;
       $response['message'] = "Please fill out all the fields.";
     }



   }
   else{
     $response['error'] = true;
     $response['message'] = "Invalid request";
   }

  echo json_encode($response);


 ?>
