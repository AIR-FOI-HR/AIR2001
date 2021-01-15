<?php

include_once "connection.php";
class review{}

$idReceznija=$_POST['id_recenzija'];

$upit = "DELETE FROM recenzija WHERE id_recenzija = $idReceznija";




$query= mysqli_query($con, $upit);

if($query){
	$response=new review();
	$response->success=1;
	$response->message="Succesfully deleted your review!";
	die(json_encode($response));
	
} else{
	$response=new review();
	$response->success=1;
	$response->message=mysqli_error($con);
	die(json_encode($response));
}

?>