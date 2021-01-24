<?php

include_once "connection.php";
class korisnik{}

$new_password=$_POST['new_password'];
$id_user=$_POST['id_user'];

$query= mysqli_query($con, "UPDATE korisnik SET lozinka = '$new_password' where id_korisnik = $id_user");

if($query){
    $response=new korisnik();
	$response->success=1;
	$response->message="Succesfully changed your password";
	die(json_encode($response));
}else{
    $response=new korisnik();
	$response->success=0;
	$response->message="Unsuccesfully changed your password";
	die(json_encode($response));
}
mysqli_close($con);

?>