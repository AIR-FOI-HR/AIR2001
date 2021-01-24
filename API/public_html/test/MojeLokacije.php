<?php

include_once "connection.php";
class lokacija{}

$idKorisnik = $_POST["id_korisnik"];


$query = mysqli_query($con,"SELECT l.id_lokacija, l.naziv_lokacije, CAST(AVG(r.ocjena) AS DECIMAL(10,2)), l.adresa_lokacije AS prosjek FROM lokacija l left JOIN recenzija r on l.id_lokacija = r.id_lokacija JOIN omiljena_lokacija o on l.id_lokacija = o.lokacija_id_lokacija WHERE o.korisnik_id_korisnik = $idKorisnik");

if($query->num_rows !==0){
    $result = array();
    $result['lokacija'] = array();
    while($row = mysqli_fetch_array($query)){
    $index['id_lokacija'] = $row[0];
    $index['naziv_lokacije'] = $row[1];
    $index['ocjena'] = $row[2];
    $index['adresa_lokacije'] = $row[3];
    array_push($result['lokacija'],$index);
    }
    $response = new lokacija();
    $response->success =1;
    $response->body = $result['lokacija'];
    $response->message="Successfully retrieved my locations";
    die(json_encode($response));

}
else{
    
    $response=new lokacija();
    $response->success=0;
	$response->message="Couldn't retrieve location";
	die(json_encode($response));
}

mysqli_close($con);


?>