<?php

include_once "connection.php";
class lokacija{}

$query = mysqli_query($con,"SELECT l.id_lokacija, l.naziv_lokacije, CAST(AVG(r.ocjena) AS DECIMAL(10,2)) as ocjena from lokacija l left join recenzija r on l.id_lokacija = r.id_lokacija GROUP by l.id_lokacija order by l.id_lokacija desc limit 1");

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
    $response->message="Successfully retrieved location";
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