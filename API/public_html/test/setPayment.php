<?php

include_once "connection.php";
class Payment{}

$idKorisnika=$_POST['id_korisnik'];
//$idKorisnika = 50;

$upit = mysqli_query($con,"UPDATE korisnik SET zadnja_uplata = CURRENT_DATE WHERE id_korisnik = $idKorisnika");
if($upit){
        $response=new Payment();
	    $response->success=1;
	    $response->message="Succesfully updated your payment!";
	    die(json_encode($response));
}else{
        $response=new Payment();
	    $response->success=0;
	    $response->message="Error updating your payment!";
	    die(json_encode($response));
    }
	
?>