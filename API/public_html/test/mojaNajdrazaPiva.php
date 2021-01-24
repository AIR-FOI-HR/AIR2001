<?php

include_once "connection.php";
class pivo{}

$idKorisnik = $_POST["id_korisnik"];


$query = mysqli_query($con,"SELECT DISTINCT p.id_proizvod,p.naziv_proizvoda,p.cijena_proizvoda,p.okus,p.litara,p.slika, CAST(AVG(r.ocjena) AS DECIMAL(10,2)), l.naziv_lokacije, l.id_lokacija from proizvod p 
       inner join omiljeno_pivo op on op.proizvod_id_proizvod = p.id_proizvod 
       inner join korisnik k on k.id_korisnik = op.korisnik_id_korisnik 
       left join recenzija r on r.id_proizvod = p.id_proizvod
       inner join pivo_lokacija pl on pl.id_proizvod = p.id_proizvod 
       inner join lokacija l on l.id_lokacija = pl.id_lokacija
       where op.korisnik_id_korisnik = $idKorisnik");


if($query->num_rows !==0){
    $result = array();
    $result['pivo'] = array();
    while($row = mysqli_fetch_array($query)){
    $index['id_proizvod'] = $row[0];
    $index['naziv_proizvoda'] = $row[1];
    $index['cijena_proizvoda'] = $row[2];
    $index['okus'] = $row[3];
    $index['litara'] = $row[4];
    $index['slika'] = $row[5];
    $index['ocjena'] = $row[6];
    $index['naziv_lokacije'] = $row[7];
    $index['id_lokacija'] = $row[8];
    array_push($result['pivo'],$index);
    }
   
    
    $response = new pivo();
    $response->success =1;
    $response->body = $result['pivo'];
    $response->message="Successfully retrieved my beers";
    die(json_encode($response));

}
else{
    
    $response=new pivo();
    $response->success=0;
	$response->message="Couldn't retrieve beers";
	die(json_encode($response));
}

mysqli_close($con);


?>