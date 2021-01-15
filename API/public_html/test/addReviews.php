<?php

include_once "connection.php";
class review{}

$idKorisnik=$_POST['id_korisnik'];
$ocjena=$_POST['ocjena']; 
$komentar = $_POST['komentar'];
if(isset($_POST['id_lokacija'])){
    $idLokacija=$_POST['id_lokacija'];
    $upit="INSERT INTO recenzija(id_korisnik, id_lokacija, ocjena, komentar, datum_i_vrijeme_recenzije) VALUES ($idKorisnik, $idLokacija, $ocjena, '$komentar', CURRENT_TIMESTAMP)";
}
else{
    $idProizvod=$_POST['id_proizvod'];
    $upit = "INSERT INTO recenzija(id_korisnik, id_proizvod, ocjena, komentar, datum_i_vrijeme_recenzije) VALUES ($idKorisnik, $idProizvod, $ocjena, '$komentar', CURRENT_TIMESTAMP)";
}



$query= mysqli_query($con, $upit);

if($query){
	$response=new review();
	$response->success=1;
	$response->message="Succesfully added your review!";
	die(json_encode($response));
	
} else{
	$response=new review();
	$response->success=1;
	$response->message=mysqli_error($con);
	die(json_encode($response));
}

?>