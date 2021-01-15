<?php

include_once "connection.php";
class korisnik{}



$korisnicko_ime=$_POST['korsnicko_ime'];
$lozinka=$_POST['lozinka']; 

$query=mysqli_query($con, "SELECT * FROM korisnik WHERE korsnicko_ime='$korisnicko_ime' AND lozinka='$lozinka' LIMIT 1");

if($query->num_rows=== 1){
    $response=new korisnik();
    $response->success=1;
    $response->message=" Successfully loged in";
    die(json_encode($response));
}
else{
    $response=new korisnik();
    $response->success=0;
	$response->message="Wrong username or password";
	die(json_encode($response));
}

//}



mysqli_close($con);

?>