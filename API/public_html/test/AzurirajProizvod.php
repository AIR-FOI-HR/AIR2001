<?php

include_once "connection.php";
class pivo{}

$idProizvod=$_POST['id_proizvoda'];
$idLokacija=$_POST['id_lokacija'];
$nazivProizvoda=$_POST['naziv_proizvoda'];
$cijenaProizvoda=$_POST['cijena_proizvoda']; 
$vrsta_proizvoda = $_POST['vrsta_proizvoda'];
$kolicina_proizvoda = $_POST['kolicina_proizvoda'];
$idKategorija=$_POST['id_kategorija'];


$query= mysqli_query($con, "UPDATE proizvod SET naziv_proizvoda = '$nazivProizvoda', cijena_proizvoda='$cijenaProizvoda', okus='$vrsta_proizvoda', litara='$kolicina_proizvoda', id_kategorija='$idKategorija' WHERE id_proizvod = $idProizvod");

if($query){
    if(isset($_POST['slika'])){
        $slika=$_POST['slika'];
        $path = "Slike/Piva/$idProizvod.png";
        unlink("../$path");
        file_put_contents("../$path",base64_decode($slika));
    }
    $response=new pivo();
	$response->success=1;
	$response->message="Succesfully updated a beer";
	die(json_encode($response));
}else{
    $response=new pivo();
	$response->success=0;
	$response->message="Error failed to update item.";
	die(json_encode($response));
}
mysqli_close($con);

?>