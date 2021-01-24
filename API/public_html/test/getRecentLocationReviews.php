<?php

include_once('connection.php');
$result = array();
$result['recenzija'] = array();
$id_lokacija=$_POST['id_lokacija'];
$select = "SELECT * FROM recenzija WHERE id_lokacija = $id_lokacija order by datum_i_vrijeme_recenzije limit 2";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['id_recenzija'] = $row['0'];
    $index['id_proizvod'] = $row['1'];
    $index['id_korisnik'] = $row['2'];
    $index['id_lokacija'] = $row['3'];
    $index['ocjena'] = $row['4'];
    $index['komentar'] = $row['5'];
    $index['datum_i_vrijeme_recenzije'] = $row['6'];
    
    array_push($result['recenzija'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>