<?php

include_once "connection.php";
class TastingMenu{}

$idKorisnik = $_POST['id_korisnik'];
$idLokacija=$_POST['id_lokacija'];
$menuName = $_POST['menuName'];
$beers = $_POST['beersOnMenu'];
$menuDuration = $_POST['duration'];
$menuDescription = $_POST['description'];




$query= mysqli_query($con, "INSERT INTO degustacijski_meni(id_lokacija, naziv_menija, trajanje, id_korisnik, opis_menija)
    values ($idLokacija,'$menuName','$menuDuration',$idKorisnik, '$menuDescription')");
$id= intval($con->insert_id);
if($query){
    $beerNames = explode(', ',$beers);
    foreach($beerNames as $beerName)
    {

        $query= $con->prepare("SELECT id_proizvod FROM proizvod WHERE naziv_proizvoda='$beerName'");

        
        if($query->execute()){
            
        $query->bind_result($id_beer);
        $query->fetch();
        $id_beer = intval($id_beer);
        $query->close();
        $dodavanje = mysqli_query($con, "INSERT INTO stavka_degustacijskog_menija (proizvod_id_proizvod,degustacijski_meni_id_degustacijski_meni) values ($id_beer,$id)");

            }
            
        }
    if ($dodavanje) {
		$response=new TastingMenu();
		$response->success=1;
		$response->message="Succesfully added tasting menu";
		die(json_encode($response));
	}
	else{
		$response=new TastingMenu();
		$response->success=1;
		$response->message="Error adding tasting menu";
		die(json_encode($response));
	}
} else{
	$response=new TastingMenu();
	$response->success=1;
	$response->message="mysqli_error"($con);
	die(json_encode($response));
}
