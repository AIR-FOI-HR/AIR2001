<?php

include_once "connection.php";
class Beer{}



$id=$_POST['id_meni'];


$query=mysqli_query($con, "SELECT naziv_proizvoda, cijena_proizvoda, okus FROM proizvod WHERE id_proizvod in (SELECT proizvod_id_proizvod from stavka_degustacijskog_menija where degustacijski_meni_id_degustacijski_meni =$id)");


if($query){
    $result = array();
    $result['proizvod'] = array();
    while($row = mysqli_fetch_array($query)){
        $index['naziv_proizvoda'] = $row['0'];
        $index['cijena_proizvoda'] = $row['1'];
        $index['okus'] = $row['2'];
        array_push($result['proizvod'],$index);
        
    }
    $response=new beer();
    $response->success=1;
    $response->proizvod = $result['proizvod'];
    $response->message=" Beers successfully loaded";
    die(json_encode($response));
}
else{
    $response=new beer();
    $response->success=0;
	$response->message="Error occurred";
	die(json_encode($response));
}

//}



mysqli_close($con);

?>