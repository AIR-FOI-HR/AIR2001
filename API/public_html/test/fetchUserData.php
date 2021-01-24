<?php

include_once('connection.php');
$result = array();
$result['korisnik'] = array();
$id_korisnik=$_POST['id_korisnik'];

$select = "SELECT korsnicko_ime, lozinka FROM korisnik WHERE id_korisnik = $id_korisnik";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['korsnicko_ime'] = $row['0'];
    $index['lozinka'] = $row['1'];
    
    array_push($result['korisnik'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>