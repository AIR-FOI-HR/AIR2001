<?php

include_once "connection.php";

$upit1 = $con->prepare("SELECT id_proizvod, naziv_proizvoda, okus, litara, slika FROM proizvod ORDER BY id_proizvod");

$upit1->execute();

$upit1->bind_result($id_proizvod,$naziv_proizvoda,$okus,$litara, $slika1);

$pivkana = array();
$pivkana['piva'] = array();

while($upit1->fetch()){
    
    $temp=array();
    $temp['id_proizvod'] = $id_proizvod;
    $temp['naziv_proizvoda'] = $naziv_proizvoda;
    $temp['okus'] = $okus; 
    $temp['litara'] = $litara; 
    $temp['slika'] = $slika1; 
    array_push($pivkana['piva'], $temp);

}
$pivkana["success"]="1";
echo json_encode($pivkana);


$upit2 = $con->prepare("SELECT id_dogadaj, naziv_dogadaja, opis_događaja, vizual_događaja FROM dogadaj ORDER BY id_dogadaj");

$upit2->execute();

$upit2->bind_result($id_dogadaj,$naziv_dogadaja,$opis_događaja, $slika2);

$dogadaj = array();
$dogadaj['dogadaj'] = array();

while($upit2->fetch()){
    $temp=array();
    $temp['id_dogadaj'] = $id_dogadaj;
    $temp['naziv_dogadaja'] = $naziv_dogadaja;
    $temp['opis_događaja'] = $opis_događaja; 
    $temp['vizual_događaja'] = $slika2; 
    array_push($dogadaj['dogadaj'], $temp);

}

echo json_encode($dogadaj);


$upit3 = $con->prepare("SELECT id_degustacijski_meni, id_lokacija, naziv_menija, opis_menija FROM degustacijski_meni ORDER BY id_degustacijski_meni");

$upit3->execute();

$upit3->bind_result($id_degustacijski_meni, $id_lokacija, $naziv_menija,$opis_menija);

$meniji = array();
$meniji['meniji'] = array();

while($upit3->fetch()){
    $temp=array();
    $temp['id_degustacijski_meni'] = $id_degustacijski_meni;
    $temp['id_lokacija'] = $id_lokacija;
    $temp['naziv_menija'] = $naziv_menija;
    $temp['opis_menija'] = $opis_menija; 
    array_push($meniji['meniji'], $temp);

}

echo json_encode($meniji);


$upit4 = $con->prepare("SELECT id_recenzija, id_proizvod, ocjena, komentar FROM recenzija WHERE id_lokacija = $id_lokacija ORDER BY id_recenzija");

$upit4->execute();

$upit4->bind_result($id_recenzija,$id_proizvod1,$ocjena, $komentar);

$recenzije = array();
$recenzije['recenzije'] = array();

while($upit4->fetch()){
    
    $temp=array();
    $temp['id_recenzija'] = $id_recenzija;
    $temp['id_proizvod'] = $id_proizvod1;
    $temp['ocjena'] = $ocjena; 
    $temp['komentar'] = $komentar;
    array_push($recenzije['recenzije'], $temp);

}

echo json_encode($recenzije);
?>