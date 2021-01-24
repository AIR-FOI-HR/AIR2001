<?php

include_once "connection.php";
class korisnik{}

$emailKorisnika=$_POST['email_korisnika'];

$q=mysqli_query($con, "SELECT * FROM korisnik WHERE email_korisnika='$emailKorisnika'");
$nr = mysqli_num_rows($q);
if($nr>0){
    $response=new korisnik();
	$response->success=1;
	$response->message="E-mail already exists";
	die(json_encode($response));
}else{
    $response = new korisnik();
    $response->success=1;
    $response->message="E-mail ok";
    die(json_encode($response));
}

mysqli_close($con);

?>