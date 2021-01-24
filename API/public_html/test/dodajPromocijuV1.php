<?php

include_once "connection.php";
class promocija{}

$idLokacija = $_POST['id_lokacije'];
$tip = $_POST['tip_promocije'];
$naziv = $_POST['naziv_promocije'];
$opis = $_POST['opis_promocije'];
$datumPocetak = $_POST['datum_pocetak'];
$datumKraj = $_POST['datum_kraj'];
$json = $_POST['json_string'];

$query = mysqli_query($con,"INSERT INTO promocije(id_promocija,naziv,opis,id_lokacija,tip,opis_o_promociji,datum_od,datum_do) VALUES(default,'$naziv','$opis',$idLokacija,'$tip','$json','$datumPocetak','$datumKraj')");

if($query){
    $response = new promocija();
    $response->success=1;
    $response->message="Succesfully added a promotion.";
    die(json_encode($response));
}
else{
    $response = new promocija();
    $response->success=0;
    $response->message=mysqli_error($con);
    die(json_encode($response));
}


?>