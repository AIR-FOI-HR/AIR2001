<?php

include_once "connection.php";
class dogadaj{}

$id=$_POST['id_dogadaja'];

$path = "Slike/$id.png";

$upit = mysqli_query($con, "DELETE FROM dogadaj_na_lokaciji WHERE dogadaj_id_dogadaj = $id");

if($upit){
    $query=mysqli_query($con, "DELETE FROM dogadaj WHERE id_dogadaj = $id");

    if($query){
        unlink("../$path");
        $response=new dogadaj();
        $response->success=1;
        $response->message="Successfully deleted an event";
        die(json_encode($response));
    }
    else{
        $response=new dogadaj();
        $response->success=0;
    	$response->message="Error deleating an event";
    	die(json_encode($response));
    }    
}else{
    $response=new dogadaj();
    $response->success=0;
    $response->message="Error deleating an event-location";
    die(json_encode($response));
}


mysqli_close($con);

?>