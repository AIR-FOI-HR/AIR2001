<?php

$lokacija = $_POST['id_lokacija'];
include_once('connection.php');
$result = array();
$result['proizvod'] = array();
$select = "SELECT p.* FROM proizvod p inner join pivo_lokacija pl on pl.id_proizvod = p.id_proizvod inner join lokacija l on l.id_lokacija = pl.id_lokacija where l.id_lokacija ='$lokacija'";
$response = mysqli_query($con, $select);

while($row = mysqli_fetch_array($response)){
    
    $index['id_proizvod'] = $row['0'];
    $index['id_kategorija'] = $row['1'];
    $index['naziv_proizvoda'] = $row['2'];
    $index['cijena_proizvoda'] = $row['3'];
    $index['okus'] = $row['4'];
    $index['litara'] = $row['5'];
    $index['slika'] = $row['6'];
    
    array_push($result['proizvod'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>