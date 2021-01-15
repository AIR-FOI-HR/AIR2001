<?php

include_once "connection.php";
class dogadjaj{}

$idKorisnika=$_POST['id_korisnik'];
$idLokacija=$_POST['id_lokacija'];
$slikaDogadjaja=$_POST['slika'];
$nazivDogadjaja=$_POST['naziv_dogadjaja'];
$opisDogadjaja=$_POST['opis_dogadjaja']; 
$pocetniDatum = $_POST['datum_pocetak'];
$zavrsniDatum = $_POST['datum_kraj'];


$idevi =mysqli_query($con,"SELECT id_dogadaj from dogadaj order by id_dogadaj desc limit 1");
$row = $idevi->fetch_row();
$value = $row[0] ?? 0;


$id = $value+1;




$path = "Slike/$id.png";

$actualpath="https://beervana2020.000webhostapp.com/$path";

$query= mysqli_query($con, "INSERT INTO dogadaj(id_dogadaj,naziv_dogadaja, opis_događaja, vizual_događaja, id_korisnik)
    values ($id,'$nazivDogadjaja','$opisDogadjaja','$actualpath',$idKorisnika)");

if($query){
    $upit = mysqli_query($con,"INSERT INTO dogadaj_na_lokaciji(lokacija_id_lokacija,dogadaj_id_dogadaj,datum_dogadaja_od,datum_dogadaja_do)
    values($idLokacija,$id,'$pocetniDatum','$zavrsniDatum')");
    if($upit){
        file_put_contents("../$path",base64_decode($slikaDogadjaja));
        $response=new dogadjaj();
	    $response->success=1;
	    $response->message="Succesfully added an event";
	    die(json_encode($response));
    }else{
        $response=new dogadjaj();
	    $response->success=1;
	    $response->message="Error adding event location";
	    die(json_encode($response));
    }
	
}else{
    $response=new dogadjaj();
	$response->success=1;
	$response->message=mysqli_error($con);
	die(json_encode($response));
}



?>