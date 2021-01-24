<?php

include_once('connection.php');
$result = array();
$result['statistika'] = array();
$id_korisnik=$_POST['id_korisnik'];

$select = "select count(r.id_korisnik) as broj_recenzija from recenzija r where r.id_korisnik = $id_korisnik union ALL select count(ol.korisnik_id_korisnik) from omiljena_lokacija ol where ol.korisnik_id_korisnik = $id_korisnik union all select count(op.korisnik_id_korisnik) from omiljeno_pivo op where op.korisnik_id_korisnik = $id_korisnik";
$response = mysqli_query($con,$select);
$brojac = 0;
while($row = mysqli_fetch_array($response)){

    if ($brojac == 0){
        $index['broj_recenzija'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 1){
        $index['omiljene_lokacije'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 2){
        $index['omiljena_piva'] = $row['0'];
        array_push($result['statistika'],$index);
    }
    
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>