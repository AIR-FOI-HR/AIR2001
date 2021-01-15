<?php

include_once "connection.php";
class pivo{}

$idLokacija=$_POST['id_lokacija'];
$nazivProizvoda=$_POST['naziv_proizvoda'];
$cijenaProizvoda=$_POST['cijena_proizvoda']; 
$vrsta_proizvoda = $_POST['vrsta_proizvoda'];
$kolicina_proizvoda = $_POST['kolicina_proizvoda'];
$idKategorija=$_POST['id_kategorija'];
$slika = $_POST['slika'];


$idevi =mysqli_query($con,"SELECT id_proizvod from proizvod order by id_proizvod desc limit 1");
$row = $idevi->fetch_row();
$value = $row[0] ?? 0;


$id = $value+1;




$path = "Slike/Piva/$id.png";

$actualpath = "https://beervana2020.000webhostapp.com/$path";


$query= mysqli_query($con, "INSERT INTO proizvod(id_proizvod, id_kategorija, naziv_proizvoda, cijena_proizvoda, okus, litara, slika, datum_kreiranja)
    values (default, $idKategorija,'$nazivProizvoda',$cijenaProizvoda,'$vrsta_proizvoda',$kolicina_proizvoda,'$actualpath', default)");

if($query){
    $id = mysqli_insert_id($con);
	$dodavanje = mysqli_query($con,"INSERT INTO pivo_lokacija (id_proizvod,id_lokacija) values ($id,$idLokacija)");
	if($dodavanje){
		file_put_contents("../$path",base64_decode($slika));
		$response=new pivo();
		$response->success=1;
		$response->message="Succesfully added a beer";
		die(json_encode($response));
	}
	else{
		$response=new pivo();
		$response->success=1;
		$response->message="Error adding a new beer";
		die(json_encode($response));
	}
} else{
	$response=new pivo();
	$response->success=1;
	$response->message=mysqli_error($con);
	die(json_encode($response));
}

?>