<?php

include_once "connection.php";
class Beer{}
$idKorisnik=$_POST['id_korisnik'];
$idPivo=$_POST['id_proizvod'];

$q=mysqli_query($con,
    "SELECT * FROM omiljeno_pivo WHERE proizvod_id_proizvod = $idPivo AND korisnik_id_korisnik = $idKorisnik");
    $nr = mysqli_num_rows($q);
    if($nr>0){
        $response=new Beer();
	    $response->success=1;
	    $response->message="This is a favorite beer";
	    die(json_encode($response));
    }else{
        $response=new Beer();
	    $response->success=0;
	    $response->message="This is not a favorite beer";
	    die(json_encode($response));
    }
mysqli_close($con);

?>