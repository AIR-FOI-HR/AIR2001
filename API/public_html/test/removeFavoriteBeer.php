<?php

include_once "connection.php";
class Beer{}

$idPivo=$_POST['id_proizvod'];
$idKorisnik=$_POST['id_korisnik'];

$upit = mysqli_query($con, 
    "DELETE FROM omiljeno_pivo WHERE proizvod_id_proizvod = $idPivo AND korisnik_id_korisnik = $idKorisnik");

if($upit){
    $response=new Beer();
    $response->success=1;
    $response->message="Successfully deleted a favorite beer";
    die(json_encode($response));
       
}else{
    $response=new Beer();
    $response->success=0;
    $response->message="Error deleating a favorite beer";
    die(json_encode($response));
}

mysqli_close($con);
?>