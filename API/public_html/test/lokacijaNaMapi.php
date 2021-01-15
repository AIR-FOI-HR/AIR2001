<?php

$lokacija = $_POST['id_lokacija'];
include_once('connection.php');
$result = array();
$result['koordinate'] = array();
$select = "SELECT longituda, latituda FROM lokacija where id_lokacija = $lokacija ";
$response = mysqli_query($con, $select);

while($row = mysqli_fetch_array($response)){
    
    $index['longituda'] = $row['0'];
    $index['latituda'] = $row['1'];
    
    array_push($result['koordinate'],$index);
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>