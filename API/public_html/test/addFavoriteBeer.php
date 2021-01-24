<?php

include_once "connection.php";
class Beer{}

$idKorisnika=$_POST['id_korisnik'];
$idPivo=$_POST['id_proizvod'];


$upit = mysqli_query($con,"INSERT INTO omiljeno_pivo(proizvod_id_proizvod,korisnik_id_korisnik) values('$idPivo','$idKorisnika')");
if($upit){
        $response=new Beer();
	    $response->success=1;
	    $response->message="Succesfully added favorite beer";
	    die(json_encode($response));
}else{
        $response=new Beer();
	    $response->success=0;
	    $response->message="Error adding favorite beer";
	    die(json_encode($response));
    }
	
?>