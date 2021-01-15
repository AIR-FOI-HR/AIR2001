<?php

include_once "connection.php";
class LocationsForMap{}


$lng = floatval($_POST["Longituda"]);
$lat = floatval($_POST["Latituda"]);

$query = mysqli_query($con, "SELECT l.id_lokacija, l.naziv_lokacije, avg(r.ocjena) , ( ACOS( COS( RADIANS( $lat ) ) * COS( RADIANS( l.latituda ) ) * COS( RADIANS( l.longituda ) - RADIANS( $lng ) ) + SIN( RADIANS( $lat ) ) * SIN( RADIANS( l.latituda ) ) ) * 6371 ) AS distance_in_km FROM lokacija l left JOIN recenzija r on l.id_lokacija = r.id_lokacija GROUP by l.id_lokacija having distance_in_km <5 ORDER by distance_in_km limit 2");

if($query){
    $result = array();
    $result['lokacije'] = array();
    while($row = mysqli_fetch_array($query)){
        $index['id_lokacije'] = $row['0'];
        $index['naziv_lokacije'] = $row['1'];
        $index['udaljenost'] = $row['3'];
        $index['ocjena'] = $row['2'];
        array_push($result['lokacije'],$index);
        
    }
    $response=new LocationsForMap();
    $response->success=1;
    $response->lokacije = $result['lokacije'];
    $response->message="Locations successfully loaded";
    die(json_encode($response));
}
else{
    $response=new LocationsForMap();
    $response->success=0;
	$response->message="Error loading locations.";
	die(json_encode($response));
}