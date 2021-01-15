<?php

require_once "connection.php";
$sql = "select * from kategorija";
if(!$con->query($sql)){
    echo "Error!";
}
else{
    $result = $con->query($sql);
    if($result->num_rows > 0){
        $return_arr['kategorija'] = array();
        while($row = $result->fetch_array()){
            array_push($return_arr['kategorija'], array(
                'id_kategorija'=>$row['id_kategorija'],
                'naziv_kategorije'=>$row['naziv_kategorije']
            ));
        }
        echo json_encode($return_arr);
    }
}

?>