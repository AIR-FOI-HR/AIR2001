<?php

include_once "connection.php";
class lokacija{}

$idKorisnika=$_POST['id_korisnik'];
$idLokacija=$_POST['id_lokacija'];


$upit = mysqli_query($con,"INSERT INTO omiljena_lokacija(lokacija_id_lokacija,korisnik_id_korisnik)
    values($idLokacija,$idKorisnika)");
if($upit){
        $response=new lokacija();
	    $response->success=1;
	    $response->message="Succesfully added favorite location";
	    die(json_encode($response));
}else{
        $response=new lokacija();
	    $response->success=0;
	    $response->message="Error adding favorite location";
	    die(json_encode($response));
    }
	




?>