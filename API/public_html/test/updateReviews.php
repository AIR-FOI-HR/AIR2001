<?php

include_once "connection.php";
class review{}

$idReceznija=$_POST['id_recenzija'];
$ocjena=$_POST['ocjena']; 
$komentar = $_POST['komentar'];
$upit = "Update recenzija SET ocjena = $ocjena, komentar = '$komentar' WHERE id_recenzija = $idReceznija";




$query= mysqli_query($con, $upit);

if($query){
	$response=new review();
	$response->success=1;
	$response->message="Succesfully updated your review!";
	die(json_encode($response));
	
} else{
	$response=new review();
	$response->success=1;
	$response->message=mysqli_error($con);
	die(json_encode($response));
}

?>