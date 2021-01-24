<?php

include_once "connection.php";
class promocija{}

$idKorisnika=$_POST['id_korisnik'];
$idPromocija=$_POST['id_promocija'];


$upit = mysqli_query($con,"INSERT INTO iskoristena_promocija(korisnik_id_korisnik,promocija_id_promocija)
    values($idKorisnika,$idPromocija)");
if($upit){
        $response=new promocija();
	    $response->success=1;
	    $response->message="Succesfully used a promotion";
	    die(json_encode($response));
}else{
        $response=new promocija();
	    $response->success=0;
	    $response->message="Error using a promotion";
	    die(json_encode($response));
    }
	




?>