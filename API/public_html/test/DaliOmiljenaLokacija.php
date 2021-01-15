<?php

include_once "connection.php";
class lokacija{}
$idKorisnik=$_POST['id_korisnik'];
$idLokacija=$_POST['id_lokacija'];

$q=mysqli_query($con,
    "SELECT * FROM omiljena_lokacija WHERE lokacija_id_lokacija = $idLokacija AND korisnik_id_korisnik = $idKorisnik");
    $nr = mysqli_num_rows($q);
    if($nr>0){
        $response=new lokacija();
	    $response->success=1;
	    $response->message="This is a favorite location";
	    die(json_encode($response));
    }else{
        $response=new lokacija();
	    $response->success=0;
	    $response->message="This is not a favorite location";
	    die(json_encode($response));
    }
mysqli_close($con);

?>