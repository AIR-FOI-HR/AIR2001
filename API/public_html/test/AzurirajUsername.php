<?php

include_once "connection.php";
class korisnik{}

$new_username=$_POST['new_username'];
$old_username=$_POST['korsnicko_ime'];
$q=mysqli_query($con, "SELECT * FROM korisnik WHERE korsnicko_ime='$new_username'");
$nr = mysqli_num_rows($q);
if($nr>0){
    $response=new korisnik();
	$response->success=1;
	$response->message="Username already exists";
	die(json_encode($response));
}
$query= mysqli_query($con, "UPDATE korisnik SET korsnicko_ime = '$new_username' where korsnicko_ime = '$old_username'");

if($query){
    $response=new korisnik();
	$response->success=1;
	$response->message="Succesfully changed your username";
	die(json_encode($response));
}else{
    $response=new korisnik();
	$response->success=0;
	$response->message="Unsuccesfully changed your username";
	die(json_encode($response));
}
mysqli_close($con);

?>