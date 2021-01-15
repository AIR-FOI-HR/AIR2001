<?php

include_once "connection.php";
class lokacija{}

$idLokacija=$_POST['id_lokacija'];
$idKorisnik=$_POST['id_korisnik'];

$upit = mysqli_query($con, 
    "DELETE FROM omiljena_lokacija WHERE lokacija_id_lokacija = $idLokacija AND korisnik_id_korisnik = $idKorisnik");

if($upit){
    $response=new lokacija();
    $response->success=1;
    $response->message="Successfully deleted a favorite location";
    die(json_encode($response));
       
}else{
    $response=new lokacija();
    $response->success=0;
    $response->message="Error deleating a favorite location";
    die(json_encode($response));
}


mysqli_close($con);

?>