<?php

include_once "connection.php";
class korisnik{}
$korisnickoIme=$_POST['korsnicko_ime'];

$q=mysqli_query($con, "SELECT * FROM korisnik WHERE korsnicko_ime='$korisnickoIme'");
    $nr = mysqli_num_rows($q);
    if($nr>0){
        $response=new korisnik();
	    $response->success=1;
	    $response->message="Username already exists";
	    die(json_encode($response));
    } else{
        $response=new korisnik();
        $response->success=1;
        $response->message="Username ok";
        die(json_encode($response));
    }
mysqli_close($con);

?>