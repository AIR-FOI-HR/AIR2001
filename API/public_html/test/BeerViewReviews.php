<?php

include_once('connection.php');
$result = array();
$result['recenzija'] = array();
$id_proizvod=$_POST['id_proizvod'];
$response = mysqli_query($con,"SELECT ocjena, komentar, datum_i_vrijeme_recenzije FROM recenzija WHERE id_proizvod = '$id_proizvod'  order by ocjena Desc LIMIT 2");

if($response){
    while($row = mysqli_fetch_array($response)){
        $index['ocjena'] = $row['0'];
        $index['komentar'] = $row['1'];
        $index['datum_i_vrijeme_recenzije'] = $row['2'];
    
        array_push($result['recenzija'],$index);
    }
    
    $result ["success"]="1";
    die(json_encode($result));;
    mysqli_close($con);

}
?>