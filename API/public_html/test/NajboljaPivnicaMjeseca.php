<?php

include_once "connection.php";
class lokacija{}

$d=strtotime("today");
$datum=date("Y-m-d h:i:s", $d);

$d=strtotime("-1 Months");
$datum1=date("Y-m-d h:i:s", $d);

$query = mysqli_query($con,"SELECT l.id_lokacija, l.naziv_lokacije, CAST(AVG(r.ocjena) AS DECIMAL(10,2)) AS prosjek FROM lokacija l left JOIN recenzija r on l.id_lokacija = r.id_lokacija WHERE r.datum_i_vrijeme_recenzije BETWEEN '$datum1' AND '$datum' GROUP BY l.id_lokacija ORDER BY prosjek DESC LIMIT 1");

if($query->num_rows !==0){
    $result = array();
    $result['lokacija'] = array();
    $row = mysqli_fetch_array($query);
    $index['id_lokacija'] = $row[0];
    $index['naziv_lokacije'] = $row[1];
    $index['ocjena'] = $row[2];
    array_push($result['lokacija'],$index);
    $response = new lokacija();
    $response->success =1;
    $response->body = $result['lokacija'];
    $response->message="Successfully retrieved best location";
    die(json_encode($response));

}
else{
    
    $response=new lokacija();
    $response->success=0;
	$response->message="Couldn't retrieve location";
	die(json_encode($response));
}

mysqli_close($con);


?>