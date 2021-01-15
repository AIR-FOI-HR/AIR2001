<?php

include_once "connection.php";
class Beer{}

$id=$_POST['id_proizvod'];

$path = "Slike/Piva/$id.png";

$query1=mysqli_query($con, "DELETE FROM stavka_degustacijskog_menija WHERE proizvod_id_proizvod = $id");
$query2=mysqli_query($con, "DELETE FROM pivo_lokacija WHERE id_proizvod = $id");
$query3=mysqli_query($con, "DELETE FROM omiljeno_pivo WHERE proizvod_id_proizvod = $id");
$query4=mysqli_query($con, "DELETE FROM recenzija WHERE id_proizvod = $id");
if($query1 || $query2 || $query3 || $query4){
    $query=mysqli_query($con, "DELETE FROM proizvod WHERE id_proizvod = $id");

    if($query){
		unlink("../$path");
		$response=new Beer();
        $response->success=1;
        $response->message="Successfully deleted a beer";
        die(json_encode($response));
    }
    else{
        $response=new Beer();
        $response->success=0;
        $response->message="Error deleting a beer";
	    die(json_encode($response));
    }
}else {
    $response=new Beer();
    $response->success=0;
    $response->message="Error deleting a beer";
	die(json_encode($response));
}


mysqli_close($con);

?>