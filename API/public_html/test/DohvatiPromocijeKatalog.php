<?php

$idLokacija=$_POST['id_lokacija'];


include_once('connection.php');
$result = array();
$result['promocija'] = array();
$select = "SELECT * FROM promocije WHERE id_lokacija=$idLokacija";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['id_promocija'] = $row['0'];
    $index['naziv'] = $row['1'];
    $index['opis'] = $row['2'];
    $index['id_lokacija'] = $row['3'];
    $index['tip'] = $row['4'];
    $index['opis_o_promociji'] = $row['5'];
    $index['datum_od'] = $row['6'];
    $index['datum_do'] = $row['7'];
    
    array_push($result['promocija'],$index);
}
$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>