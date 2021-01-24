<?php

include_once "connection.php";
class promocija{}



$id=$_POST['id_promocija'];
$id_korisnik=$_POST['id_korisnik'];

$query=mysqli_query($con, "SELECT p.naziv,p.opis,p.datum_od,p.datum_do,p.opis_o_promociji, i.korisnik_id_korisnik from promocije p  LEFT JOIN iskoristena_promocija i ON p.id_promocija = i.promocija_id_promocija WHERE p.id_promocija = $id UNION SELECT p.naziv,p.opis,p.datum_od,p.datum_do,p.opis_o_promociji, i.korisnik_id_korisnik from promocije p RIGHT JOIN iskoristena_promocija i ON p.id_promocija = i.promocija_id_promocija WHERE i.korisnik_id_korisnik = $id_korisnik AND i.promocija_id_promocija = $id");


if($query){
    $result = array();
    $result['promocija'] = array();
    while($row = mysqli_fetch_array($query)){
        $index['naziv'] = $row['0'];
        $index['opis'] = $row['1'];
        $index['datum_od'] = $row['2'];
        $index['datum_do'] = $row['3'];
        $index['opis_o_promociji'] = $row['4'];
        $index['id_korisnik'] = $row['5'];
        array_push($result['promocija'],$index);
    }
    
    $response=new promocija();
    $response->success=1;
    $response->promocija = $result['promocija'];
    $response->message=" Successfully loaded";
    die(json_encode($response));
}
else{
    $response=new promocija();
    $response->success=0;
	$response->message="Error occurred";
	die(json_encode($response));
}



mysqli_close($con);

?>