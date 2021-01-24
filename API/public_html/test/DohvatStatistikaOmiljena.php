<?php

include_once('connection.php');
$result = array();
$result['statistika'] = array();
$id_lokacija=$_POST['id_lokacija'];

$prviStart = date("Y-m-d", strtotime("first day of previous month"));
$prviKraj = date("Y-m-d", strtotime("last day of previous month"));
$drugiStart = date("Y-m-d", strtotime("first day of previous month -1 month"));
$drugiKraj = date("Y-m-d", strtotime("last day of previous month -1 month"));
$treciStart = date("Y-m-d", strtotime("first day of previous month -2 month"));
$treciKraj = date("Y-m-d", strtotime("last day of previous month -2 month"));

$select = "select COUNT(ol.korisnik_id_korisnik) as pros_omiljena from omiljena_lokacija ol where ol.lokacija_id_lokacija = $id_lokacija AND ol.datum_dodavanja BETWEEN '$prviStart' AND '$prviKraj' union ALL select COUNT(ol.korisnik_id_korisnik) as pros_omiljena from omiljena_lokacija ol where ol.lokacija_id_lokacija = $id_lokacija AND ol.datum_dodavanja BETWEEN '$drugiStart' AND '$drugiKraj' union all select COUNT(ol.korisnik_id_korisnik) as pros_omiljena from omiljena_lokacija ol where ol.lokacija_id_lokacija = $id_lokacija AND ol.datum_dodavanja BETWEEN '$treciStart' AND '$treciKraj'";
$response = mysqli_query($con,$select);
$brojac = 0;

while($row = mysqli_fetch_array($response)){

    if ($brojac == 0){
        $index['prvi_mjesec'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 1){
        $index['drugi_mjesec'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 2){
        $index['treci_mjesec'] = $row['0'];
        array_push($result['statistika'],$index);
    }
    
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>