<?php

include_once('connection.php');
$result = array();
$result['statistika'] = array();
$id_lokacija=$_POST['id_lokacija'];

$prviStart = date("Y-m-d", strtotime("first day of previous month"));
$prviKraj = date("Y-m-d", strtotime("last day of previous month"));
$drugiStart = date("Y-m-d", strtotime("first day of previous month -1 month"));
$drugiKraj = date("Y-m-d", strtotime("last day of previous month -1 month"));
$treciStart = date("Y-m-d", strtotime("first day of previous month -2 month"));
$treciKraj = date("Y-m-d", strtotime("last day of previous month -2 month"));

$select = "select AVG(r.ocjena) as pros_ocjena from recenzija r where r.id_lokacija = $id_lokacija AND r.datum_i_vrijeme_recenzije BETWEEN '$prviStart' AND '$prviKraj' union ALL select AVG(re.ocjena) as pros_ocjena from recenzija re where re.id_lokacija = $id_lokacija AND re.datum_i_vrijeme_recenzije BETWEEN '$drugiStart' AND '$drugiKraj' union all select AVG(res.ocjena) from recenzija res where res.id_lokacija = $id_lokacija AND res.datum_i_vrijeme_recenzije BETWEEN '$treciStart' AND '$treciKraj'";
$response = mysqli_query($con,$select);
$brojac = 0;

while($row = mysqli_fetch_array($response)){

    if ($brojac == 0){
        $index['prvi_mjesec'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 1){
        $index['drugi_mjesec'] = $row['0'];
        $brojac++;
    }
    else if($brojac == 2){
        $index['treci_mjesec'] = $row['0'];
        array_push($result['statistika'],$index);
    }
    
}

$result ["success"]="1";
echo json_encode($result);
mysqli_close($con);

?>