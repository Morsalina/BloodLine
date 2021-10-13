<?php
  class DbOperations{


    private $conn;
    
    function __construct(){

      require_once dirname(__FILE__).'/dbConnect.php';

      $db = new dbConnect();

      $this->conn = $db->connect();

     }

      /* CRUD  C->Create user, basically signup*/
      function createUser($fullname, $username, $pass, $contact, $blood, $location){

        $password = md5($pass);

        $statement = $this->conn->prepare("INSERT INTO users VALUES(NULL, ?,?,?,?,?,?);");
        $statement->bind_param("ssssss",$fullname,$username,$password,$contact,$blood,$location);

        if($statement->execute()){
          return true;
        }
        else{
          return false;
        }

      }

      public function userLogin($username, $password){
        $statement = $this -> $conn->prepare("SELECT id FROM users WHERE username= ? AND password = ?");
        $statement->bind_param("ss",$username,$password);
        $statement->execute();
        $statement->store_result();
        return $statement->num_rows >0;
      }

      public function getUserByUsername($username){
        $statement = $this -> $conn->prepare("SELECT * FROM users WHERE username= ?");
        $statement->bind_param("s",$username);
        return $statement->get_result()->fetch_assoc();

      }


  }



 ?>
