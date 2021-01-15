<?php

include_once "connection.php";


if(isset($_POST['oldMenu'])){
    $menuOldName = $_POST['oldMenu'];
}else {
    include_once "addMenu.php";
}
class TastingMenu1{}
$idKorisnik = $_POST['id_korisnik'];
$idLokacija=$_POST['id_lokacija'];
$menuName = $_POST['menuName'];
$beers = $_POST['beersOnMenu'];
$menuDuration = $_POST['duration'];
$menuDescription = $_POST['description'];


$query= mysqli_query($con, "update degustacijski_meni set id_lokacija = $idLokacija, naziv_menija= '$menuName', trajanje = '$menuDuration', id_korisnik = $idKorisnik, opis_menija = '$menuDescription' where naziv_menija ='$menuOldName'");


if($query){
    $query = mysqli_query($con, "select id_degustacijski_meni from degustacijski_meni where naziv_menija = '$menuOldName'");
    
    $id= $query->fetch_object()->id_degustacijski_meni;
    $beerNames = explode(', ',$beers);
    $query1 = mysqli_query($con, "delete from stavka_degustacijskog_menija where degustacijski_meni_id_degustacijski_meni = $id");
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
		$response=new TastingMenu1();
		$response->success=1;
		$response->message="Succesfully updated tasting menu";
		die(json_encode($response));
	}
	else{
		$response=new TastingMenu1();
		$response->success=1;
		$response->message="Error updating tasting menu";
		die(json_encode($response));
	}
} else{
	$response=new TastingMenu1();
	$response->success=0;
	$response->message="mysqli_error"($con);
	die(json_encode($response));
}
