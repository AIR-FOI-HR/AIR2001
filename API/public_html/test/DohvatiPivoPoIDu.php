<?php

$id_pivo = $_POST['id_pivo'];
include_once('connection.php');
$result = array();
$result['proizvod'] = array();
$select = "SELECT p.naziv_proizvoda FROM proizvod p WHERE p.id_proizvod = $id_pivo";
$response = mysqli_query($con, $select);

while($row = mysqli_fetch_array($response)){
    $index['naziv_proizvoda'] = $row['0'];
    array_push($result['proizvod'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>