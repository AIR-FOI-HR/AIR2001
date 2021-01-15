<?php

include_once "connection.php";
class TastingMenu{}

$id=$_POST['id_meni'];
$query=mysqli_query($con, "DELETE FROM stavka_degustacijskog_menija WHERE degustacijski_meni_id_degustacijski_meni = $id");
if($query){
    $query=mysqli_query($con, "DELETE FROM degustacijski_meni WHERE id_degustacijski_meni = $id");

    if($query){
       $response=new TastingMenu();
        $response->success=1;
        $response->message="Successfully deleted an tasting menu";
        die(json_encode($response));
    }
    else{
        $response=new TastingMenu();
        $response->success=0;
        $response->message="Error deleating an tasting menu";
	    die(json_encode($response));
    }
}else {
    $response=new TastingMenu();
    $response->success=0;
    $response->message="Error deleating an tasting menu";
	die(json_encode($response));
}


mysqli_close($con);

?>