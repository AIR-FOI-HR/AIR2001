<?php

include_once "connection.php";
class promocija{}

$idPromocija= $_POST['id_promocija'];
$idLokacija = $_POST['id_lokacije'];
$tip = $_POST['tip_promocije'];
$naziv = $_POST['naziv_promocije'];
$opis = $_POST['opis_promocije'];
$datumPocetak = $_POST['datum_pocetak'];
$datumKraj = $_POST['datum_kraj'];
$json = $_POST['json_string'];


$query= mysqli_query($con, "UPDATE promocije 
SET naziv = '$naziv', opis = '$opis', id_lokacija='$idLokacija',tip='$tip',opis_o_promociji='$json',datum_od='$datumPocetak',datum_do='$datumKraj'  WHERE id_promocija = $idPromocija");

if($query){
        $response=new promocija();
	    $response->success=1;
	    $response->message="Succesfully updated a promotion.";
	    die(json_encode($response));
    }
else{
    $response=new promocija();
	    $response->success=0;
	    $response->message="Error failed to update promotion.";
	    die(json_encode($response));
}


mysqli_close($con);

?>