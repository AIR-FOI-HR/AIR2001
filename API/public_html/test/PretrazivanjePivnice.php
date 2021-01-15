<?php
include_once('connection.php');
class beerplace{}

$pretrazivanje = $_POST['pretrazivanje'];

$result = array();
$result['proizvod'] = array();
$select = "SELECT l.naziv_lokacije, l.adresa_lokacije, r.ocjena,l.id_lokacija FROM lokacija l LEFT JOIN recenzija r on r.id_lokacija = l.id_lokacija WHERE l.naziv_lokacije LIKE '%$pretrazivanje%'";
$query= mysqli_query($con, $select);

if($query->num_rows!==0){
while($row = mysqli_fetch_array($query)){
    
    $index['naziv_lokacije'] = $row['0'];
    $index['adresa_lokacije'] = $row['1'];
    $index['ocjena'] = $row['2'];
    $index['id_lokacija'] = $row['3'];
    
    array_push($result['proizvod'],$index);
    }
    $response = new beerplace;
    $response->body =$result['proizvod'];
    $response->success=1;
    $response->message = "Successfully searched bearplaces";
    echo json_encode($response);
}
else{
    $response = new beerplace();
    $response->success = 0;
    $response->message="No beerplaces with this name";
	die(json_encode($response));
}



mysqli_close($con);

?>