<?php
class recenzija{};
include_once('connection.php');
$korisnik = $_POST['id_korisnik'];
$result = array();
$result['moja_recenzija'] = array();
$select = "SELECT ocjena, komentar, datum_i_vrijeme_recenzije FROM recenzija where id_korisnik = $korisnik LIMIT 1";
$response = mysqli_query($con,$select);

while($row = mysqli_fetch_array($response)){
    
    $index['ocjena'] = $row['0'];
    $index['komentar'] = $row['1'];
    $index['datum_i_vrijeme_recenzije'] = $row['2'];
    
    array_push($result['moja_recenzija'],$index);
}
$rezultat = new recenzija();
$rezultat -> success = 1;
$rezultat -> body = $result['moja_recenzija'];
$rezultat-> message="Succesfully retrived reviews";
die(json_encode($rezultat));
mysqli_close($con);

?>