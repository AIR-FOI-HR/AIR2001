<?php

include_once "connection.php";
class promotion{}

$idPromocija=$_POST['id_promocija'];

$upit = "DELETE FROM iskoristena_promocija WHERE  promocija_id_promocija= $idPromocija";

$predQuery= mysqli_query($con, $upit);
if($predQuery){
    $upit = "DELETE FROM promocije WHERE id_promocija = $idPromocija";
    
    $query= mysqli_query($con, $upit);
    
    if($query){
    	$response=new promotion();
    	$response->success=1;
    	$response->message="Succesfully deleted your promotion!";
    	die(json_encode($response));
    	
    } else{
    	$response=new promotion();
    	$response->success=0;
    	$response->message="Error when deleting your promotion!";
    	die(json_encode($response));
    }
}
?>