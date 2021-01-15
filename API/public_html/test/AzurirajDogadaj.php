<?php

include_once "connection.php";
class dogadjaj{}

$idDogadaja=$_POST['id_dogadaj'];
$idLokacija=$_POST['id_lokacija'];
$nazivDogadjaja=$_POST['naziv_dogadjaja'];
$opisDogadjaja=$_POST['opis_dogadjaja']; 
$pocetniDatum = $_POST['datum_pocetak'];
$zavrsniDatum = $_POST['datum_kraj'];


$query= mysqli_query($con, "UPDATE dogadaj 
SET naziv_dogadaja = '$nazivDogadjaja', opis_događaja = '$opisDogadjaja' WHERE id_dogadaj = $idDogadaja");

if($query){
    $upit = mysqli_query($con, "UPDATE dogadaj_na_lokaciji 
    SET datum_dogadaja_od = '$pocetniDatum', datum_dogadaja_do = '$zavrsniDatum' 
    WHERE dogadaj_id_dogadaj = $idDogadaja AND lokacija_id_lokacija = $idLokacija");
    if($upit){
        if(isset($_POST['slika'])){
            $slikaDogadjaja=$_POST['slika'];
            $path = "Slike/$idDogadaja.png";
            unlink("../$path");
            file_put_contents("../$path",base64_decode($slikaDogadjaja));
        }
        $response=new dogadjaj();
	    $response->success=1;
	    $response->message="Succesfully updated an event.";
	    die(json_encode($response));
    }else{
        $response=new dogadjaj();
	    $response->success=0;
	    $response->message="Error failed to update event-location.";
	    die(json_encode($response));
    }
}else{
    $response=new dogadjaj();
	    $response->success=0;
	    $response->message="Error failed to update event.";
	    die(json_encode($response));
}


mysqli_close($con);

?>