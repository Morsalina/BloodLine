<?php

  require_once '../includes/DbOperations.php';
   $response = array();

   if($_SERVER['REQUEST_METHOD']=='POST'){
             if(isset($_POST['username']) and isset($_POST['password'])){
                 $db = new DbOperations();

                 if($db->userLogin($_POST['username'], $_POST['password'])){
                   $user = $db->getUserByUsername($_POST['username']);

                   $response['error'] = false;
                   $response['id'] = $user['id'];
                   $response['fullname'] = $user['fullname'];
                   $response['username'] = $user['username'];
                   $response['contact'] = $user['contact'];
                   $response['blood'] = $user['blood'];
                   $response['location'] = $user['location'];


                 }
                 else{
                   $response['error'] = true;
                   $response['message'] = "Username or password doesn't locale_filter_matches";
                 }

             }
             else{

               $response['error'] = true;
               $response['message'] = "Please fill out all the fields.";
             }




   }

   echo json_encode($response);
