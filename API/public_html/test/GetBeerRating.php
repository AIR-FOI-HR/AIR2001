<?php
class recenzija{};
include_once('connection.php');
$id_proizvod = $_POST['id_proizvod'];

$select = "SELECT avg(ocjena) FROM recenzija where id_proizvod = $id_proizvod";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['ocjena'] = $row['0'];
}
$rezultat = new recenzija();
$rezultat -> success = 1;
$rezultat -> body = $index['ocjena'];
$rezultat-> message="Succesfully retrived rating";
die(json_encode($rezultat));
mysqli_close($con);

?>