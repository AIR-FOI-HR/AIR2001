<?php

include_once "connection.php";
class Beer{}



$id=$_POST['id_menu'];


$query=mysqli_query($con, "SELECT naziv_proizvoda from proizvod where id_proizvod in (select id_proizvod from pivo_lokacija where id_lokacija = (select id_lokacija from degustacijski_meni where id_degustacijski_meni='$id' ))");


if($query){
    $result = array();
    $result['proizvod'] = array();
    while($row = mysqli_fetch_array($query)){
        $index['naziv_proizvoda'] = $row['0'];
        array_push($result['proizvod'],$index);
        
    }
    $query = mysqli_query($con, "select naziv_menija, opis_menija, trajanje from degustacijski_meni where id_degustacijski_meni='$id'");
    $result['meni'] = array();
    while($row = mysqli_fetch_array($query)){
        $index1['tastingMenuName'] = $row['0'];
        $index1['description'] = $row['1'];
        $index1['duration'] = $row['2'];
        array_push($result['meni'],$index1);
        
    }
    $response=new beer();
    $response->success=1;
    $response->proizvod = $result['proizvod'];
    $response->meni = $result['meni'];
    $response->message=" Successfully loaded";
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