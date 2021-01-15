<?php

$lokacija = $_POST['id_lokacija'];

include_once('connection.php');
$result = array();
$result['tastingMenu'] = array();
$select = "SELECT * FROM degustacijski_meni WHERE id_lokacija = $lokacija";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['id_degustacijski_meni'] = $row['0'];
    $index['id_lokacija'] = $row['1'];
    $index['naziv_menija'] = $row['2'];
    $index['slika_menija'] = $row['3'];
    $index['opis_menija'] = $row['4'];
    $index['datum_kreiranja'] = $row['5'];
    $index['trajanje'] = $row['6'];
    $index['id_korisnik'] = $row['7'];
    
    array_push($result['tastingMenu'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>