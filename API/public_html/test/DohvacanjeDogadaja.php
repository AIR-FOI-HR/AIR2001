<?php

$idLokacija=$_POST['id_lokacija'];


include_once('connection.php');
$result = array();
$result['dogadaj'] = array();
$select = "SELECT dogadaj.id_dogadaj,dogadaj.naziv_dogadaja, dogadaj.opis_događaja, dogadaj.vizual_događaja, dogadaj.id_korisnik, dogadaj_na_lokaciji.datum_dogadaja_od, dogadaj_na_lokaciji.datum_dogadaja_do, dogadaj_na_lokaciji.lokacija_id_lokacija, lokacija.naziv_lokacije, lokacija.adresa_lokacije
FROM dogadaj 
	LEFT JOIN dogadaj_na_lokaciji ON dogadaj_na_lokaciji.dogadaj_id_dogadaj = dogadaj.id_dogadaj 
	LEFT JOIN lokacija ON dogadaj_na_lokaciji.lokacija_id_lokacija = lokacija.id_lokacija
	WHERE dogadaj_na_lokaciji.lokacija_id_lokacija = $idLokacija AND dogadaj.id_dogadaj = dogadaj_na_lokaciji.dogadaj_id_dogadaj";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['id_dogadaj'] = $row['0'];
    $index['naziv_dogadaja'] = $row['1'];
    $index['opis_dogadaja'] = $row['2'];
    $index['vizual_dogadaja'] = $row['3'];
    $index['id_korisnik'] = $row['4'];
    $index['datum_od'] = $row['5'];
    $index['datum_do'] = $row['6'];
    $index['lokacija_id'] = $row['7'];
    $index['naziv_lokacije'] = $row['8'];
    $index['adresa_lokacije'] = $row['9'];
    
    
    array_push($result['dogadaj'],$index);
}
$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>