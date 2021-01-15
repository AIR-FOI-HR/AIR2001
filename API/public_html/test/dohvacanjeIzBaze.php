<?php

include_once "connection.php";

$upit = $con->prepare("SELECT d.id_dogadaj,d.naziv_dogadaja,d.opis_događaja, d.vizual_događaja, d.datum_kreiranja,dl.datum_dogadaja_od, dl.datum_dogadaja_do FROM dogadaj d, dogadaj_na_lokaciji dl WHERE d.id_dogadaj = dl.dogadaj_id_dogadaj");

$upit->execute();

$upit->bind_result($id_dogadaj,$naziv_dogadaja,$opis_događaja,$vizual_događaja,$datum_kreiranja,$datum_dogadaja_od, $datum_dogadaja_do);

$dogadaji = array();

while($upit->fetch()){
    $temp=array();
    $temp['id_dogadaj'] = $id_dogadaj;
    $temp['naziv_dogadaja'] = $naziv_dogadaja;
    $temp['opis_dogadaja'] = $opis_događaja; 
    $temp['vizual_dogadaja'] = $vizual_događaja; 
    $temp['datum_kreiranja'] = $datum_kreiranja; 
    $temp['datum_dogadaja_od'] = $datum_dogadaja_od; 
    $temp['datum_dogadaja_do'] = $datum_dogadaja_do; 
    array_push($dogadaji, $temp);

}

echo json_encode($dogadaji);
?>