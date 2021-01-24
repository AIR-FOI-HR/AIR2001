<?php

include_once "connection.php";
class lokacija{}

$oibLokacija=$_POST['OIBLokacije'];

$q=mysqli_query($con, "SELECT * FROM lokacija WHERE OIB_lokacije='$oibLokacija'");
$nr = mysqli_num_rows($q);
if($nr>0){
    $response=new lokacija();
	$response->success=1;
	$response->message="There already exist a location with this OIB";
	die(json_encode($response));
}else{
    $response=new lokacija();
	$response->success=1;
	$response->message="OIB ok";
	die(json_encode($response));
}

mysqli_close($con);

?>