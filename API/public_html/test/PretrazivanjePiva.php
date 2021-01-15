<?php
include_once('connection.php');
class beer{}

$pretrazivanje = $_POST['pretrazivanje'];

$result = array();
$result['proizvod'] = array();
$select = "SELECT p.id_proizvod,p.naziv_proizvoda,p.cijena_proizvoda,p.okus,p.litara,p.slika FROM proizvod p WHERE p.naziv_proizvoda LIKE '%$pretrazivanje%'";
$query= mysqli_query($con, $select);

if($query->num_rows!==0){
while($row = mysqli_fetch_array($query)){
    
    $index['id_proizvod'] = $row['0'];
    $index['naziv_proizvoda'] = $row['1'];
    $index['cijena_proizvoda'] = $row['2'];
    $index['okus'] = $row['3'];
    $index['litara'] = $row['4'];
    $index['slika'] = $row['5'];
    
    array_push($result['proizvod'],$index);
    }
    $response = new beer();
    $response->body =$result['proizvod'];
    $response->success=1;
    $response->message = "Successfully searched bears";
    echo json_encode($response);
}
else{
    $response = new beer();
    $response->success = 0;
    $response->message="No beers with this name";
	die(json_encode($response));
}



mysqli_close($con);

?>